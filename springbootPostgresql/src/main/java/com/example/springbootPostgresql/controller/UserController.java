package com.example.springbootPostgresql.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootPostgresql.entity.UserEntity;
import com.example.springbootPostgresql.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    
    
    
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<UserEntity> users = userService.getAllUser();
        model.addAttribute("users", users);
        
       
        for (UserEntity user : users) {
            System.out.println("User ID: " + user.getId());
            System.out.println("User Name: " + user.getName());
            System.out.println("User Password: " + user.getPassword());
            System.out.println("User City: " + user.getCity());
        }
            
         return "dashboard";
    }
    

    @GetMapping
    public List<UserEntity> getAllUser(){
        List<UserEntity> users = userService.getAllUser();
        System.out.println("users : "+users);
        return users;
    }
    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable Long userId){
        UserEntity user = userService.getUserById(userId);
        System.out.println("userId : "+userId+" : user : "+user);
        return user;
    }

    @PostMapping
    public String saveUser(@RequestBody UserEntity user){
        userService.saveUser(user);
        return "user saved successfuly.";
    }

    @PutMapping("/{userId}")
    public String updateUser(@RequestBody UserEntity user, @PathVariable Long userId){
        userService.updateUser(user, userId);
        return "user updated successfully.";
    }

    @DeleteMapping("/{userId}")
    public String deleteUseryId(@PathVariable Long userId){
        userService.deleteUseryId(userId);
        return "user deleted successfully.";
    }

}