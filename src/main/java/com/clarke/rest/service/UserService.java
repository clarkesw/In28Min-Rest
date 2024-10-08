package com.clarke.rest.service;

import com.clarke.rest.beans.User;
import com.clarke.rest.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    
    @Autowired
    UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }
    
    public Optional<User> findById(int id){
        return repo.findById(id);
    }
    
    public User save(User user){
        log.debug("User: " + user);
        return repo.save(user);
    }
    
    public void deleteUser(int id){
        repo.deleteById(id);
    }    
}
