package com.example.demo.scope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration("appConfig")
public class AppConfig {

    @Value("${scope.name}")
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
