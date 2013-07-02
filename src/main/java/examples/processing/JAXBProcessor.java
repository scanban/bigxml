package examples.processing;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

abstract public class JAXBProcessor<T> implements TagProcessor {
    private String tagPath;
    private JAXBContext jaxbContext;
    private Unmarshaller unmarshaller;

    public JAXBProcessor(Class<T> clazz, String tagPath) throws JAXBException {
        this.tagPath = tagPath;
        jaxbContext = JAXBContext.newInstance(clazz);
        unmarshaller = jaxbContext.createUnmarshaller();
    }

    public JAXBProcessor(Class<T> clazz, String tagPath, String schema) throws JAXBException, SAXException {
        this(clazz, tagPath);
        unmarshaller.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).
                newSchema(new StreamSource(this.getClass().getResourceAsStream("/schemas/" + schema))));
    }

    @Override
    public String getTagPath() {
        return tagPath;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void process(XMLStreamReader xmlStreamReader)  {
        try {
            T element = (T) unmarshaller.unmarshal(xmlStreamReader);
            doWork(element);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    abstract public void doWork(T element);
}
