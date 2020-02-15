package com.example.demo;

import com.example.demo.anno.PushAnno;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Component
public class PushFactoryBean implements FactoryBean {


    @Override
    public Object getObject() throws Exception {
        //用动态代理去实现
        Object proxy = Proxy.newProxyInstance(PushService.class.getClassLoader(), new Class[]{PushService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

              PushAnno pushAnno =   method.getAnnotation(PushAnno.class);

              String type = pushAnno.type() ;

              String who = pushAnno.who();

              System.out.println( who + "好，你有新的"+ type+"消息"+",消息内容为："+ Arrays.toString(args));

              return null;

            }
        });
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return PushService.class;
    }
}
