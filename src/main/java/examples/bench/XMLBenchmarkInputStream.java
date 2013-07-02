package examples.bench;

import examples.utils.MultiSequenceInputStream;
import examples.utils.RepeatableByteArrayInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class XMLBenchmarkInputStream extends MultiSequenceInputStream {
    private long byteCount;

    public XMLBenchmarkInputStream(int count, String header, String body, String footer) {
        super(new ByteArrayInputStream(header.getBytes()),
                new RepeatableByteArrayInputStream(count, body.getBytes()),
                new ByteArrayInputStream(footer.getBytes()));
    }

    @Override
    public int read() throws IOException {
        ++byteCount;
        return super.read();
    }

    public long getByteCount() {
        return byteCount;
    }
}
