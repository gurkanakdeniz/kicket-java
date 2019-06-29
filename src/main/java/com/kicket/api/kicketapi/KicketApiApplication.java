package com.kicket.api.kicketapi;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kicket.api.kicketapi.core.FileUtility;

@SpringBootApplication
public class KicketApiApplication {

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
    }

}
