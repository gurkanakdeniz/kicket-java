package com.kicket.api.kicketapi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kicket.api.kicketapi.configuration.GitProperties;
import com.kicket.api.kicketapi.core.FileUtility;
import com.kicket.api.kicketapi.core.GitUtility;

@SpringBootApplication
public class KicketApiApplication {
    
    @Autowired
    GitProperties gitProperties;

    public static void main(String[] args) {
        SpringApplication.run(KicketApiApplication.class, args);
    }
    
    @PostConstruct
    private void init() {
        try {
            FileUtility.createFolder("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            if (gitProperties.active()) {
                new GitUtility(gitProperties, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
