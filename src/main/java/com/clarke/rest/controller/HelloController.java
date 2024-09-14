package com.clarke.rest.controller;

import com.clarke.rest.beans.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public String getWorld(){
        return "Hello Cool Clarkie";
    }
    
    @GetMapping("/hello-bean")
    public Hello getWorldBean(){
        return new Hello("Gogo Gadget");
    }    
}
