package com.example.demo.classloader;

/**
 * @author Administrator
 * @date 2020/8/6 9:13
 */
public class ClassloaderTest {

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class c = Class.forName("com.example.demo.classloader.Person", true, myClassLoader);
        Object obj = c.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }
}
