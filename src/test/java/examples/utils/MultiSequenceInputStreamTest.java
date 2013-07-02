package examples.utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MultiSequenceInputStreamTest {

    @Test
    public void testMulti() throws Exception {
        byte[][] testData = new byte[][] {
                "AA".getBytes(),"BB".getBytes(),"CC".getBytes()
        };
        InputStream is[] = new InputStream[testData.length];
        for(int i = 0; i < testData.length; ++i) { is[i] = new ByteArrayInputStream(testData[i]); }

        MultiSequenceInputStream ms = new MultiSequenceInputStream(is);

        for (byte[] data : testData) {
            byte b[] = new byte[data.length];
            assertEquals(ms.read(b), b.length);
            assertArrayEquals(b, data);
        }
        assertEquals(ms.read(), -1);
    }

}
