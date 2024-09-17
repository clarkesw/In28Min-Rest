package com.clarke.rest;

import com.clarke.rest.repository.UserJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JdbcCommandLineRunner implements CommandLineRunner {
    
    @Autowired
    UserJdbcRepository repo;

    @Override
    public void run(String... args) throws Exception {
//        repo.get();
    }
   
}
