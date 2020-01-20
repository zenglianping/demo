package jmm.reorder;

import java.util.concurrent.CountDownLatch;

/**
 * @author zenglp
 * @date 2020/1/20 9:16
 * 线程同步一直输出(1,1)
 */
public class TestOrdering {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();

    }


    /**
     * 使用 wait notify 同步 , 此方式写起来很复杂
     * 这里要注意wait操作一定要放到while条件内,而while条件一定要放到同步代码块中
     * 另外条件谓词可以不用volatile修饰,因为synchronized关键字可以确保工作内存与主内存的同步
     * Synchronized能够实现原子性和可见性；在Java内存模型中，synchronized规定，线程在加锁时，先清空工作内存→在主内存中拷贝最新变量的副本到工作内存→执行完代码→将更改后的共享变量的值刷新到主内存中→释放互斥锁。
     *
     * @throws InterruptedException
     */
    private static void test1() throws InterruptedException {

        for (int i = 0; i < 10000; i++) {

            Object obj = new Object();
            Object obj1 = new Object();

            final boolean[] condition = {false, false};

            Thread one = new Thread(() -> {
                synchronized (obj) {
                    a = 1;
                    condition[0] = true;
                    obj.notify();

                }
                synchronized (obj1) {
                    while (!condition[1]) {
                        try {
                            obj1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                x = b;


            });

            Thread other = new Thread(() -> {
                synchronized (obj) {
                    while (!condition[0]) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

                synchronized (obj1) {
                    b = 1;
                    condition[1] = true;
                    obj1.notify();
                }
                y = a;
            });

            one.start();
            other.start();
            one.join();
            other.join();
            System.out.println("(" + x + "," + y + ")");
            //重置变量
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            System.out.println("执行次数:"+(i+1));
        }
    }


    /**
     * 使用原子变量CountDownLatch实现
     */
    private static void test2() throws InterruptedException{

        for (int i = 0; i < 10000; i++) {

            CountDownLatch c1 = new CountDownLatch(1);
            CountDownLatch c2 = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                a = 1;
                c2.countDown();
                try {
                    c1.await(); //会一直阻塞,直到调用 countDown 方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x = b;


            });

            Thread other = new Thread(() -> {
                b = 1;
                c1.countDown();
                try {
                    c2.await();//会一直阻塞,直到调用 countDown 方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                y = a;
            });

            one.start();
            other.start();
            one.join();
            other.join();
            System.out.println("(" + x + "," + y + ")");
            //重置变量
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            System.out.println("执行次数:"+(i+1));
        }

    }


}
