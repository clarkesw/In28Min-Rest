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
        users.add(new User(0,"Nobody",LocalDate.parse("1993-01-03")));
        users.add(new User(1,"Clarke",LocalDate.parse("1975-10-07")));
        users.add(new User(2,"Ranga",LocalDate.parse("2001-01-08")));
    }
    
    public List<User> getAllUsers(){
        return users;
    }
    
    public User findUser(int id){
        Optional<User> user = users.stream()
            .filter(us -> us.getId() == id)
            .findFirst();
        return user.orElse(null);
    }
    
    public User addUser(User user){
        //      int id = users.size() + ;
        users.add(user);
       
        Optional<User> findFirst = users.stream()
            .filter(us -> us.getName().equals(user.getName()))
            .findFirst();
        return findFirst.orElse(null);
    }
    
    public User deleteUser(int id){
        User user = users.remove(id);

        return user;
    }    
}
