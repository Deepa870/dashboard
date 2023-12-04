package com.example.springbootPostgresql.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DUser {

    private String title;
    private String user_name;
    private String created_at;
    private String owner_name;
    private String user_entry;
    private String room_id;
    private String user_id;
    private int message_count;
    private int reaction_count;
    private int speaking_time_minutes;
    private int active_time_minutes;

    // Default constructor
    public DUser() {
    }

    // Constructor with parameters
    @JsonCreator
    public DUser(
            @JsonProperty("title") String title,
            @JsonProperty("user_name") String user_name,
            @JsonProperty("created_at") String created_at,
            @JsonProperty("owner_name") String owner_name,
            @JsonProperty("user_entry") String user_entry,
            @JsonProperty("room_id") String room_id,
            @JsonProperty("user_id") String user_id,
            @JsonProperty("message_count") int message_count,
            @JsonProperty("reaction_count") int reaction_count,
            @JsonProperty("speaking_time_minutes") int speaking_time_minutes,
            @JsonProperty("active_time_minutes") int active_time_minutes) {
        this.title = title;
        this.user_name = user_name;
        this.created_at = created_at;
        this.owner_name = owner_name;
        this.user_entry = user_entry;
        this.room_id = room_id;
        this.user_id = user_id;
        this.message_count = message_count;
        this.reaction_count = reaction_count;
        this.speaking_time_minutes = speaking_time_minutes;
        this.active_time_minutes = active_time_minutes;
    }

    // Getters and setters (generated or written manually)

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getUser_entry() {
        return user_entry;
    }

    public void setUser_entry(String user_entry) {
        this.user_entry = user_entry;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getMessage_count() {
        return message_count;
    }

    public void setMessage_count(int message_count) {
        this.message_count = message_count;
    }

    public int getReaction_count() {
        return reaction_count;
    }

    public void setReaction_count(int reaction_count) {
        this.reaction_count = reaction_count;
    }

    public int getSpeaking_time_minutes() {
        return speaking_time_minutes;
    }

    public void setSpeaking_time_minutes(int speaking_time_minutes) {
        this.speaking_time_minutes = speaking_time_minutes;
    }

    public int getActive_time_minutes() {
        return active_time_minutes;
    }

    public void setActive_time_minutes(int active_time_minutes) {
        this.active_time_minutes = active_time_minutes;
    }

    // toString method (generated or written manually)
    
    @Override
    public String toString() {
        return "DUser{" +
                "title='" + title + '\'' +
                ", user_name='" + user_name + '\'' +
                ", created_at='" + created_at + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", user_entry='" + user_entry + '\'' +
                ", room_id='" + room_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", message_count=" + message_count +
                ", reaction_count=" + reaction_count +
                ", speaking_time_minutes=" + speaking_time_minutes +
                ", active_time_minutes=" + active_time_minutes +
                '}';
    }
}
