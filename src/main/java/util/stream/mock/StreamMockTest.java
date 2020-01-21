package util.stream.mock;

/**
 * @author zenglp
 * @date 2020/1/21 10:19
 */
public class StreamMockTest {

    public static void main(String[] args) {

        FilterStream filterStream = new FilterStream();

        MapStream mapStream = new MapStream();

        mapStream.setLastStream(filterStream);

        ReduceStream reduceStream = new ReduceStream();

        reduceStream.setLastStream(mapStream);

        reduceStream.eval();

    }


}
