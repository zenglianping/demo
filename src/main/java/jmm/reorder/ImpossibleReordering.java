package jmm.reorder;

/**
 * @author zenglp
 * @date 2020/1/20 9:16
 * 避免出现指令重排,可以给变量加上volatile修饰
 * 最终结果(0,1) (1,0) (1,1)
 * 思考怎样才能一直输出(1,1)
 */
public class ImpossibleReordering {

    static  int x = 0, y = 0;
    static volatile int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10000; i++) {

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
