package com.clarke.rest.controller;

import com.clarke.rest.beans.User;
import com.clarke.rest.exception.UserNotFoundException;
import com.clarke.rest.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Slf4j
@RestController
public class UserController {
    
    @Autowired
    private UserService userServ;
    
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userServ.getAllUsers();
    }
    
    @GetMapping("/users/{idi}")
    public User findUser(@PathVariable("idi") int id){
        log.debug("GET \"/users/" + id);
        User user = userServ.findUser(id);
        if(user == null)
            throw new UserNotFoundException("User ID: " + id + " Not Found");
        return user;
    }  
    
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        log.debug("POST \"/users/" + user);
        user = userServ.addUser(user);
        
        String url = "/users/" + user.getId();
        
        URI toUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(toUri).build();
    }
    
    @DeleteMapping("/users/{idi}")
    public User deleteUser(@PathVariable("idi") int id){
        log.debug("DELETE \"/users/" + id);
        User user = userServ.deleteUser(id);
        if(user == null)
            throw new UserNotFoundException("User ID: " + id + " Not Found");
        return user;
    }      
}
