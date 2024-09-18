package com.clarke.rest.controller;

import com.clarke.rest.beans.Post;
 import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.clarke.rest.beans.User;
import com.clarke.rest.exception.UserNotFoundException;
import com.clarke.rest.service.PostService;
import com.clarke.rest.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    
    @Autowired
    private PostService postServ;
        
    @Value("${spring.datasource.url}") 
    private String sUrl;
    
    @GetMapping("/users")
    public List<User> findAll(){
        log.debug("GET \"/users");
        
        System.out.println("URL from application.properties: " + sUrl);
        return userServ.findAll();
    }
    
    @GetMapping("/users/{id}")
    public EntityModel<User> findUser(@PathVariable("id") int id){
        log.debug("GET \"/users/" + id);
        
        Optional<User> userOp = userServ.findById(id);
        if(userOp.isEmpty())
            throw new UserNotFoundException("User ID: " + id + " Not Found");
        
        return getHateoasLink(userOp.get());
    }  

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        log.debug("POST \"/users/" + user);
        user = userServ.save(user);        
        
        URI toUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(toUri).build();
    }
    
    @GetMapping("/users/{id}/posts")
    public List<Post> findPostsByUserId(@PathVariable("id") int id){
        log.debug("GET \"/users/" + id + "/posts");
        
        Optional<User> userOp = userServ.findById(id);
        if(userOp.isEmpty())
            throw new UserNotFoundException("User ID: " + id + " Not Found");
        
        return userOp.get().getPosts();
    }  
    
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostsByUserId(@PathVariable("id") int id,@Valid @RequestBody Post post){
        log.debug("POST \"/users/" + id + "/posts");
        
        Optional<User> userOp = userServ.findById(id);
        if(userOp.isEmpty())
            throw new UserNotFoundException("User ID: " + id + " Not Found");
        
        User usr = userOp.get();
        post.setUser(usr);
        Post savedPost = postServ.save(post);
        URI toUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(toUri).build();
    }      
    
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") int id){
        log.debug("DELETE \"/users/" + id);
        userServ.deleteUser(id);
    }      
    
    private <T> EntityModel<T> getHateoasLink(T t){
        EntityModel<T> model = EntityModel.of(t);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).findAll());
        return model.add(link.withRel("all-users"));    
    }
}
