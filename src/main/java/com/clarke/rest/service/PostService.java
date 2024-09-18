package com.clarke.rest.service;

import com.clarke.rest.beans.Post;
import com.clarke.rest.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostService {
    
    @Autowired
    PostRepository repo;
    
    public List<Post> findAll(){
        return repo.findAll();
    }
    
    public Optional<Post> findById(int id){
        return repo.findById(id);
    }
    
    public Post save(Post post){
        log.debug(post.toString());
        return repo.save(post);
    }
    
    public void deleteUser(int id){
        repo.deleteById(id);
    }    
}
