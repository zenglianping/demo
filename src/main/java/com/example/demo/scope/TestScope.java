package com.example.demo.scope;

import org.aspectj.weaver.ast.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.cloud.context.scope.GenericScope;
import org.springframework.stereotype.Component;

@Component
public class TestScope extends GenericScope {

    Logger logger = LoggerFactory.getLogger(TestScope.class.getName());

    public TestScope(){
        super();
        super.setName("testScope");
    }

    @Override
    public String getConversationId() {
        return "testScope";
    }
}
