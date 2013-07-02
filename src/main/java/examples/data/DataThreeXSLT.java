package examples.data;

import examples.processing.XSLTProcessor;

import javax.xml.transform.TransformerConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class DataThreeXSLT extends XSLTProcessor {
    private static final String TRAIL_TRANSFORM = "<?xml version=\"1.0\"?>\n" +
            "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" +
            "<xsl:output method=\"text\"/>" +
            "\n" +
            "<xsl:template match=\"/\">\n" +
            "P1:<xsl:value-of select=\"dtype_three/p1\"/>" +
            " P3: <xsl:value-of select=\"dtype_three/p2\"/>" +
            "</xsl:template>" +
            "</xsl:stylesheet>";

    private static final String TAG_PATH = "data/WS/dtype_three";
    private static final ByteArrayOutputStream ostream = new ByteArrayOutputStream();

    public DataThreeXSLT() throws TransformerConfigurationException {
        super(TAG_PATH, fromString(TRAIL_TRANSFORM));
    }

    @Override
    protected OutputStream getStream() {
        ostream.reset();
        return ostream;
    }

    @Override
    protected void postTransform(OutputStream ostream) {
//        System.out.print(ostream.toString());
    }

    @Override
    public String getTagPath() {
        return TAG_PATH;
    }
}
