package com.example.springbootPostgresql.controller;

import com.example.springbootPostgresql.entity.DUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MyController {

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private RestTemplate restTemplate;

    private static String apiUrl = "https://debatabl-prod.ml17.live/analysis/rooms/";

    @GetMapping("/urlWithTimestamps")
    public String consumeAllUserData(
        @RequestParam(name = "startTime", required = false) String startTime,
        @RequestParam(name = "endTime", required = false) String endTime,
        Model model) {
        if (startTime == null) {
            startTime = "2023-11-20 18:30:000";
        }
        if (endTime == null) {
            endTime = "2023-11-20 18:30:000";
        }

        String urlWithTimestamps = apiUrl + "?startTime=" + startTime + "&endTime=" + endTime;
        logger.info("Making an API request to: {}", urlWithTimestamps);

        try {
            String response = makeApiCall(urlWithTimestamps);
            logger.info("Received a response from {}: {}", urlWithTimestamps, response);

            ObjectMapper objectMapper = new ObjectMapper();
            DUser[] duser = objectMapper.readValue(response, DUser[].class);

            if (duser != null) {
                model.addAttribute("duserList", duser);

                model.addAttribute("chartData", getChartData(duser));
                logger.info("chartData: {}", getChartData(duser));

                model.addAttribute("chartLabels", getChartLabels(duser));
                logger.info("chartLabels: {}", getChartLabels(duser));

                model.addAttribute("speakingTimeData", getSpeakingTimeData(duser));
                logger.info("speakingTimeData: {}", getSpeakingTimeData(duser));

                model.addAttribute("activeTimeData", getActiveTimeData(duser));
                logger.info("activeTimeData: {}", getActiveTimeData(duser));

                return "index";
            } else {
                logger.warn("API response did not contain valid data.");
                return "error";
            }
        } catch (HttpClientErrorException ex) {
            logger.error("HTTP error occurred: {} - {} - URL: {}", ex.getRawStatusCode(), ex.getStatusText(), urlWithTimestamps, ex);
            return "error";
        } catch (UnknownHttpStatusCodeException ex) {
            logger.error("Unknown HTTP status code: {} - URL: {}", ex.getRawStatusCode(), urlWithTimestamps, ex);
            return "error";
        } catch (Exception ex) {
            logger.error("An unexpected error occurred: {} - URL: {}", ex.getMessage(), urlWithTimestamps, ex);
            return "error";
        }
    }

    private String makeApiCall(String apiUrl) {
        return restTemplate.getForObject(apiUrl, String.class);
    }

    private Set<String> getChartLabels(DUser[] duser) {
        return Arrays.stream(duser)
                .map(DUser::getTitle)
                .collect(Collectors.toSet());
    }

    private Map<String, Long> getChartData(DUser[] duser) {
        return Arrays.stream(duser)
                .collect(Collectors.groupingBy(DUser::getTitle, Collectors.counting()));
    }

    private List<Integer> getSpeakingTimeData(DUser[] duser) {
        return Arrays.stream(duser)
                .collect(Collectors.groupingBy(DUser::getTitle, Collectors.summingInt(DUser::getSpeaking_time_minutes)))
                .values().stream()
                .collect(Collectors.toList());
    }

    private List<Integer> getActiveTimeData(DUser[] duser) {
        return Arrays.stream(duser)
                .collect(Collectors.groupingBy(DUser::getTitle, Collectors.summingInt(DUser::getActive_time_minutes)))
                .values().stream()
                .collect(Collectors.toList());
    }
}
