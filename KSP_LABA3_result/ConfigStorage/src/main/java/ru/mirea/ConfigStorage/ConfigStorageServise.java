package ru.mirea.ConfigStorage;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Service
public class ConfigStorageServise {

    private Configuration config;

    @PostConstruct
    public void init() {
        config = new Configuration("http://localhost:8081/", "http://localhost:8082/", "http://localhost:8083/", "http://localhost:8084/");
    }

    public Configuration getConfig() {
        return config;
    }
}
