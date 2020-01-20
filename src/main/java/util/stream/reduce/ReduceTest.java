package util.stream.reduce;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import  static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author zenglp
 * @date 2020/1/20 14:14
 */
public class ReduceTest {

    @Test
    public void testReduce1() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> optional = numList.stream().reduce((x, y) -> x + y);
        Integer result = optional.get();
        assertEquals(55, result.intValue());
    }

    @Test
    public void testReduce2() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer result = numList.stream().reduce(10,(x, y) -> x + y);
        assertEquals(65, result.intValue());
    }


    @Test
    public void testReduce3() {
        List<Integer> numList = Arrays.asList(1, 2, 3);
        Integer result = numList.parallelStream().reduce(5,(x, y) -> x + y,(x,y) -> x*y);
        assertEquals(6*7*8, result.intValue());

        result = numList.stream().reduce(5,(x, y) -> x + y,(x,y) -> x*y);
        assertEquals(5+1+2+3, result.intValue());


    }
}
