package com.clarke.rest.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcRepository {
    
    JdbcTemplate jdbcTemplate;
  
    public void get(){
        try{
            Connection con = DriverManager.getConnection("org.h2.Driver");
            String query = "Select * from user_details where id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(0, 1);
            
            ResultSet results = ps.executeQuery();
            
            while(results.next()){
                System.out.println("Results: " + results.getString("name"));
            }
            
        }catch(SQLException se){
        }
    }
}
