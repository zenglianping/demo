package util.stream.mock;

/**
 * 模拟实现Stream的filter方法
 * @author zenglp
 * @date 2020/1/21 9:44
 */
public class FilterStream implements Stream {

    private Stream lastStream;

    @Override
    public Stream eval() {
       Stream stream = null ;
        if (lastStream != null) {
            stream =  lastStream.eval();
        }
        System.out.println("filtering");
        return stream;
    }

    public Stream getLastStream() {
        return lastStream;
    }

    public void setLastStream(Stream lastStream) {
        this.lastStream = lastStream;
    }
}
