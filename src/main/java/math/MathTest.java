package math;

public class MathTest {

    public static void main(String[] args) {

    }

    //经测试用整数除以10或100，结果是准确的
    void testDiv10() {
        for (int i = 0; i < 10000; i++) {
            if (((i / 10.0) + "").length() > 5 || (((i / 100.0) + "").length()) > 5) {
                System.out.println("==================");
            }
            System.out.println("i="+i+" i/10:"+i/10.0+" \t i/100:"+i/100.0);
        }
    }
}
