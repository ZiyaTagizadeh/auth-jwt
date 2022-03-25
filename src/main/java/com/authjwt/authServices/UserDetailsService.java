package com.authjwt.authServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
     private Map<String,String> users = new HashMap<>();

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;


     @PostConstruct
     public void init(){
         users.put("ziko","$2a$12$BX1xGLgE2pT4SHCIeafbe.n3KlhN7jj00v0hFfKlf.CSBvFwUMR16");

     }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         if(users.containsKey(username)){
             return  new User(username,users.get(username),new ArrayList<>());
         }
         throw new UsernameNotFoundException(username);
    }
}
