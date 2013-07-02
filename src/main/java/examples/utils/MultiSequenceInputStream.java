package examples.utils;

import java.io.IOException;
import java.io.InputStream;

public class MultiSequenceInputStream extends InputStream {
    final private InputStream[] streams;
    private int streamPointer;


    public MultiSequenceInputStream(InputStream ...streams) {
        this.streams = streams.clone();
    }

    @Override
    public int read() throws IOException {
        while (streamPointer != streams.length) {
            int rvalue = streams[streamPointer].read();
            if(rvalue == -1) { ++streamPointer; continue; }
            return rvalue;
        }
        return -1;
    }
}
