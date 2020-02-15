import java.math.BigDecimal;

public final class Test {

    public static void main(String[] args) {
        // 0.1    0.001100
        System.out.println( Float.floatToIntBits(0.5f));
        System.out.println( Integer.toBinaryString(Float.floatToIntBits(0.5f)));
        BigDecimal b = new BigDecimal("0.1");

    }
}
