package com.example.demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.cloud.context.scope.GenericScope;
import org.springframework.cloud.context.scope.ScopeCache;
import org.springframework.cloud.context.scope.StandardScopeCache;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class TestScope implements Scope , BeanFactoryPostProcessor,
        BeanDefinitionRegistryPostProcessor, DisposableBean  {

    Logger logger = LoggerFactory.getLogger(TestScope.class);

    /**
     * Prefix for the scoped target.
     */
    public static final String SCOPED_TARGET_PREFIX = "scopedTarget.";


    private StandardScopeCache cache = new StandardScopeCache();

    private String name = "testScope";

    private ConfigurableListableBeanFactory beanFactory;

    private StandardEvaluationContext evaluationContext;

    private Object bean ;

    private String id;

    private Map<String, Exception> errors = new ConcurrentHashMap<>();

    private ConcurrentMap<String, ReadWriteLock> locks = new ConcurrentHashMap<>();

    static RuntimeException wrapIfNecessary(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
        return new IllegalStateException(throwable);
    }

    /**
     * Manual override for the serialization ID that will be used to identify the bean
     * factory. The default is a unique key based on the bean names in the bean factory.
     * @param id The ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }



    /**
     * A map of bean name to errors when instantiating the bean.
     * @return The errors accumulated since the latest destroy.
     */
    public Map<String, Exception> getErrors() {
        return this.errors;
    }

    @Override
    public void destroy() {
        this.cache.clear() ;
        this.bean = null ;
    }



    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (this.bean == null) {
            synchronized (this.name) {
                if (this.bean == null) {
                    this.bean = objectFactory.getObject();
                }
            }
        }
        return this.bean;
    }

    @Override
    public String getConversationId() {
        return this.name;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object remove(String name) {
       return null;
    }

    @Override
    public Object resolveContextualObject(String key) {
        Expression expression = parseExpression(key);
        return expression.getValue(this.evaluationContext, this.beanFactory);
    }

    private Expression parseExpression(String input) {
        if (StringUtils.hasText(input)) {
            ExpressionParser parser = new SpelExpressionParser();
            try {
                return parser.parseExpression(input);
            }
            catch (ParseException e) {
                throw new IllegalArgumentException("Cannot parse expression: " + input,
                        e);
            }

        }
        else {
            return null;
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        this.beanFactory = beanFactory;
        beanFactory.registerScope(this.name, this);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
            throws BeansException {

        }



}
