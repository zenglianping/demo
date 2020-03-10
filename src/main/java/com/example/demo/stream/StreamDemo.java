package com.example.demo.stream;

import util.stream.mock.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;

/**
 * @author zenglp
 * @date 2020/3/10 10:04
 */
public class StreamDemo {

    public static void main(String[] args) throws Exception {

        new StreamDemo().testParallelStream2();
        Thread.currentThread().join();
    }


    private void testParallelStream(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        long t = System.currentTimeMillis();

        Optional<Integer> reduce1 = list.stream().reduce((a, b) -> a + b);

        System.out.println("reduce1.get():" + reduce1.get() + " 耗时：" + (System.currentTimeMillis() - t) + " ms");

        t = System.currentTimeMillis();

        //默认线程数为 Runtime.getRuntime().availableProcessors()-1 四核机器则为4-1=3
        Optional<Integer> reduce2 = list.parallelStream().reduce((a, b) -> a + b);

        System.out.println("reduce2.get():" + reduce2.get() + " 耗时：" + (System.currentTimeMillis() - t) + " ms");

    }

    private void testParallelStream2(){

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        long t = System.currentTimeMillis();

        //指定线程数
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);

        forkJoinPool.submit(() -> {

            Optional<Integer> reduce = list.parallelStream().reduce((a, b) -> a + b);

            System.out.println("reduce.get():" + reduce.get() + " 耗时：" + (System.currentTimeMillis() - t) + " ms");

        });

    }

}
