package com.exec.api.execapi;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exec.api.execapi.core.FileUtility;

@SpringBootApplication
public class ExecApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecApiApplication.class, args);
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
