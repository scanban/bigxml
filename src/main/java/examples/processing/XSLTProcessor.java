package examples.processing;

import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class XSLTProcessor implements TagProcessor {
    private final String tag;
    private final Transformer transformer;

    public XSLTProcessor(String tag, InputStream inputStream)
            throws TransformerConfigurationException {
        this.tag = tag;
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.
                newTransformer(new StreamSource(inputStream));
    }

    public static InputStream fromString(String xslt)
            throws TransformerConfigurationException {
        return new ByteArrayInputStream(xslt.getBytes());
    }

    public static InputStream fromResource(String xsltResourceName)
            throws TransformerConfigurationException {
        return  XSLTProcessor.class.getResourceAsStream("/xslt/" + xsltResourceName);
    }

    protected  OutputStream getStream() { return System.out; }
    protected  void postTransform(OutputStream ostream) {}
    protected  void exceptionListener(Throwable t) { throw new IllegalStateException(t); }

    @Override
    public String getTagPath() {
        return tag;
    }

    @Override
    public void process(XMLStreamReader xmlStreamReader)  {
        StAXSource stAXSource = new StAXSource(xmlStreamReader);
        try {
            OutputStream outputStream = getStream();
            transformer.transform(stAXSource, new StreamResult(outputStream));
            postTransform(outputStream);
        } catch (TransformerException e) {
            exceptionListener(e);
        }
    }
}
