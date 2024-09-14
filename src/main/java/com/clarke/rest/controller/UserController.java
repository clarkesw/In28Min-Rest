package com.clarke.rest.controller;

import com.clarke.rest.beans.User;
import com.clarke.rest.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class UserController {
    
    @Autowired
    private UserService userServ;
    
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userServ.getAllUsers();
    }
    
    @GetMapping("/users/{id}")
    public User findUser(@PathVariable("id") int id){
        log.debug("GET \"/users/" + id);
        return userServ.findUser(id);
    }    
}
