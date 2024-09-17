package com.clarke.rest.repository;

import com.clarke.rest.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer>{

}
