package examples.processing;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.TransformerException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class TagEngine {
    private Map<List<String>, TagProcessor> processorMap = new HashMap<List<String>, TagProcessor>();


    public TagEngine add(TagProcessor processor) {
        processorMap.put(new ArrayList<String>(Arrays.asList(processor.getTagPath().split("/"))),
                processor);
        return this;
    }

    public void process(String fileName) throws FileNotFoundException,
            TransformerException, XMLStreamException {
        process(new FileInputStream(fileName));
    }

    public void process(InputStream inputStream) throws FileNotFoundException,
            XMLStreamException, TransformerException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader streamReader = factory.createXMLStreamReader(inputStream);
        Stack<String> tagStack = new Stack<String>();

        while (streamReader.hasNext()) {
            int eventType = streamReader.next();
            if(eventType == XMLStreamConstants.START_ELEMENT) {
                tagStack.push(streamReader.getName().toString());
                TagProcessor t = processorMap.get(tagStack);

                if(t != null) {
                    t.process(streamReader);
                    tagStack.pop();
                }
            } else if(eventType == XMLStreamConstants.END_ELEMENT) {
                tagStack.pop();
            }
        }
    }

}
