package com.clarke.rest.beans;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString 
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private Integer id;
    private String name;
    private LocalDate bday;
    
    public User(User user){
        this.id = user.id;
        this.name = user.name;
        this.bday = user.bday;
    }
}
