package examples.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class RepeatableByteArrayInputStreamTest {
    private static final String TEST_STRING = "TEST";

    @Test
    public void testRead() throws Exception {
        RepeatableByteArrayInputStream rs = new RepeatableByteArrayInputStream(1, TEST_STRING);
        byte[] buf = new byte[TEST_STRING.length()];
        assertEquals(rs.read(buf), TEST_STRING.length());
        assertArrayEquals(buf, TEST_STRING.getBytes());

        assertEquals(rs.read(), -1);
    }

    @Test
    public void testMultiRead() throws Exception {
        RepeatableByteArrayInputStream rs = new RepeatableByteArrayInputStream(3, TEST_STRING);
        byte[] buf = new byte[TEST_STRING.length()];

        for(int i = 0; i < 3; ++i) {
            assertEquals(rs.read(buf), TEST_STRING.length());
            assertArrayEquals(buf, TEST_STRING.getBytes());
        }

        assertEquals(rs.read(), -1);
        assertEquals(rs.read(buf), -1);
    }


    @Test(expected=IllegalArgumentException.class)
    public void testInvalidCount() throws Exception {
        new RepeatableByteArrayInputStream(-1, TEST_STRING);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidString() throws Exception {
        new RepeatableByteArrayInputStream(1, (String) null);
    }
}
