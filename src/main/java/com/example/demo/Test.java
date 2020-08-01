package com.example.demo;

import com.example.demo.springboot.anno.contional.ContionalA;
import com.example.demo.springboot.anno.contional.ContionalB;

public class Test {

    public static void main(String[] args) {
        Class<?> aClass = null ;
        try {
             aClass = Class.forName("com.example.demo.springboot.anno.contional.ContionalA");
             ContionalA contionalA = (ContionalA) aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (aClass == null) {
            try {
                aClass = Class.forName("com.example.demo.springboot.anno.contional.ContionalB");
                ContionalB contionalB = (ContionalB) aClass.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
