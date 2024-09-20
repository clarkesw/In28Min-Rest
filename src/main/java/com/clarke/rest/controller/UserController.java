package com.clarke.rest.controller;

 import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.clarke.rest.beans.User;
import com.clarke.rest.exception.UserNotFoundException;
import com.clarke.rest.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public List<User> findAll(){
        return userServ.findAll();
    }
    
    @GetMapping("/users/{id}")
    public EntityModel<User> findUser(@PathVariable("id") int id){
        log.debug("GET \"/users/" + id);
        Optional<User> userOp = userServ.findById(id);
        log.debug("User: " + userOp);
        if(userOp.isEmpty())
            throw new UserNotFoundException("User ID: " + id + " Not Found");
        
        User user = userOp.get();
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).findAll());
        model.add(link.withRel("all-users"));
        return model;
    }  
    
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        log.debug("POST \"/users/" + user);
        
        user = userServ.save(user);        
        String url = "/users/" + user.getId();
        log.debug("User: " + user);
        URI toUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(toUri).build();
    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") int id){
        log.debug("DELETE \"/users/" + id);
        userServ.deleteUser(id);
    }      
}
