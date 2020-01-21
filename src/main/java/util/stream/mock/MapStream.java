package util.stream.mock;

/**
 * @author zenglp
 * @date 2020/1/21 9:43
 */
public class MapStream implements Stream {

    private Stream lastStream ;

    @Override
    public Stream eval() {
        Stream stream = null ;
        if (lastStream != null) {
            stream =  lastStream.eval();
        }
        System.out.println("mapping");
        return stream;
    }

    public Stream getLastStream() {
        return lastStream;
    }

    public void setLastStream(Stream lastStream) {
        this.lastStream = lastStream;
    }
}
