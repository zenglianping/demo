package jmm.reorder;

/**
 * @author zenglp
 * @date 2020/1/20 9:16
 * 可能出现指令重排
 * 在未满足happens-before原则前提下,内存级别的重排序会使程序行为变得不可预测
 * 最终可能结果 (0,0) (0,1) (1,0) (1,1)
 */
public class PossibleReordering {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10000; i++) { //启动10000个线程组

            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                b = 1;
                y = a;
            });

            one.start();
            other.start();
            one.join();
            other.join();
            System.out.println("(" + x + "," + y + ")");
            //重置变量
            x =0 ; y=0; a=0; b=0;
        }

    }


}
