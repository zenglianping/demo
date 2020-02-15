package com.example.demo.scope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "testScope")
public class ScopeBean {

    @Value("#{appConfig.name}")
   private String scopeName ;

    public String getScopeName() {
        return scopeName;
    }
}
