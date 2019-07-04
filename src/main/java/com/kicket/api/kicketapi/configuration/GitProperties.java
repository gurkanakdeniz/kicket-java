package com.kicket.api.kicketapi.configuration;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("application")
@PropertySource("application.properties")
@ConfigurationProperties(prefix = "git")
public class GitProperties {

    private String user;
    private String pass;
    private String remote;
    private String branch;
    private String directory;
    private String active;
    
    public boolean active() {
        return "true".equals(active);
    }
    
    @PostConstruct
    private void init() {
        try {
            user = Optional.ofNullable(user).filter(s -> !s.isBlank()).orElseGet(() -> System.getenv("GIT_USER"));
            pass = Optional.ofNullable(pass).filter(s -> !s.isBlank()).orElseGet(() -> System.getenv("GIT_PASS"));
            remote = Optional.ofNullable(remote).filter(s -> !s.isBlank()).orElseGet(() -> System.getenv("GIT_REMOTE"));
            branch = Optional.ofNullable(branch).filter(s -> !s.isBlank()).orElseGet(() -> System.getenv("GIT_BRANCH"));
            directory = Optional.ofNullable(directory).filter(s -> !s.isBlank()).orElseGet(() -> System.getenv("GIT_DIRECTORY"));
            active = Optional.ofNullable(active).filter(s -> !s.isBlank()).orElseGet(() -> System.getenv("GIT_ACTIVE"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}
