package com.clarke.rest.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Entity
public class Post {
    
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;
}
