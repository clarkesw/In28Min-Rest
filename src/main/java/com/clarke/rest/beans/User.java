package com.clarke.rest.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;
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
@Entity(name="user_details")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "usr_post")
    @SequenceGenerator(name = "usr_post", initialValue=5)
    private Integer id;
    @NotBlank(message = "Name is mandatory.")
    private String name;
    @PastOrPresent(message = "Birthday should be in the Past.")
    private LocalDate bday;
    
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Post> posts;
    
    public User(User user){
        this.id = user.id;
        this.name = user.name;
        this.bday = user.bday;
    }
}
