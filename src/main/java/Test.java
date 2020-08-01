import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public final class Test {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 5, 432);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("item:"+list.get(i));
        }

    }
}
