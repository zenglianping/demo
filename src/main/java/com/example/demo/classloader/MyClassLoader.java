package com.example.demo.classloader;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Administrator
 * 自定义classloader用于从其他地方如网络、数据库等获取字节流并生成Class对象
 * @date 2020/8/6 8:59
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "C:/workspace/gitdemo/src/main/java/com/example/demo/classloader/";
        //Person.class文件的位置可以随便放
        path = "D:/";
        File classFile = new File(path + "Person.class");
        try {
            byte[] classBytes = IOUtils.toByteArray(new FileInputStream(classFile));
            Class<?> c = defineClass(name, classBytes, 0, classBytes.length);
            return c ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}
