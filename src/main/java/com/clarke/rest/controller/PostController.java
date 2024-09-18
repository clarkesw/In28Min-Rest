package com.clarke.rest.controller;

import com.clarke.rest.beans.Post;
import com.clarke.rest.service.PostService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PostController {
    
    @Autowired
    PostService service;
    
    @GetMapping("/posts")
    public List<Post> findAll(){
        log.debug("GET \"/users");
        return service.findAll();
    }
    
}
