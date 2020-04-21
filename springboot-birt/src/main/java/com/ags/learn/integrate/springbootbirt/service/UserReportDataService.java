package com.ags.learn.integrate.springbootbirt.service;

import com.ags.learn.integrate.springbootbirt.dto.User;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserReportDataService {
     public List<User> getUsers(){
         InputStream is=UserReportDataService.class.getClassLoader().getResourceAsStream("mock/user.json");
         try {
             ObjectMapper mapper = new ObjectMapper();
             JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,User.class);
             List<User> users=mapper.readValue(is,javaType);
             return users;
         } catch (IOException e) {
             e.printStackTrace();
         }
         return null;
     }
}
