package com.clarke.rest.service;

import com.clarke.rest.beans.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private static List<User> users = new ArrayList<>();
    
    static{
        users.add(new User(1,"Clarke",LocalDate.parse("1975-10-07")));
        users.add(new User(2,"Ranga",LocalDate.parse("2001-01-08")));
    }
    
    public List<User> getAllUsers(){
        return users;
    }
    
    public User findUser(int id){
        return users.get(id);
    }
    
    private User findUserLambda(int id){
        
        // Optional<User> findFirst =
        Optional<User> findFirst = users.stream()
                .filter(use -> use.getId().equals(id))
                .findFirst();
               
        return findFirst.get();
    }
}
