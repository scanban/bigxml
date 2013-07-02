package examples.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class RepeatableByteArrayInputStream extends InputStream {
    private int repeatCount;
    final private byte[] buffer;
    private int bufferPointer;

    public RepeatableByteArrayInputStream(int count, byte[] data) {
        if(count <= 0) { throw new IllegalArgumentException("count"); }
        if(data == null) { throw new IllegalArgumentException("data"); }
        this.repeatCount = count;
        this.buffer = data;
    }

    public RepeatableByteArrayInputStream(int count, String data) {
        this(count, (data == null) ? null : data.getBytes());
    }

    public RepeatableByteArrayInputStream(int count, String data, Charset charset) {
        this(count, (data == null) ? null : data.getBytes(charset));
    }

    @Override
    public int read() throws IOException {
        while (repeatCount > 0) {
            if(bufferPointer == buffer.length) {
                --repeatCount;
                bufferPointer = 0;
                continue;
            }
            return buffer[bufferPointer++];
        }
        return -1;
    }
}
