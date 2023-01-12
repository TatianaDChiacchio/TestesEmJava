package com.robo.api.Config;


import com.robo.api.Domain.Users;
import com.robo.api.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB(){

        Users u1 = new Users(null,"Tatiana","tatachiacchio@gmail.com","123");
        Users u2 = new Users(null,"Rick","rick@gmail.com","123");


        userRepository.saveAll(List.of(u1,u2));
    }
}
