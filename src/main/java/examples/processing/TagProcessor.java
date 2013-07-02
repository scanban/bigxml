package examples.processing;

import javax.xml.stream.XMLStreamReader;

public interface TagProcessor {
    public String getTagPath();
    public void process(XMLStreamReader xmlStreamReader);
}
