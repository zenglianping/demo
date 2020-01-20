package util.stream.reduce;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zenglp
 * @date 2020/1/20 15:57
 */
public class FlatMapTest {

    // flatMap 合并流
    @Test
    public void testFlatMap1() {
        String[] arr  = {"hello","world"};
//        List<String[]> list = Stream.of(arr).map(s -> s.split(""))
//                .collect(Collectors.toList());

        List<String> list = Stream.of(arr).map(s -> s.split("")).flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println(list);
    }


    @Test
    public void testFlatMap2() {
        Integer[][] arr  = new Integer[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}

        };
        List<Integer> list = Stream.of(arr).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(list);

    }

    @Test
    public void testFlatMap3() {
        int[][] arr  = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}

        };
        IntStream intStream = Stream.of(arr).flatMapToInt(Arrays::stream);
        intStream.forEach(i -> {
            System.out.print(i+",");
        });

    }


}
