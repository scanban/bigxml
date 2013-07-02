package examples.processing;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.TransformerException;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;


public class TagEngineTest {

    @Test
    public void testEngine() throws FileNotFoundException, XMLStreamException, TransformerException {
        TagProcessor tagProcessor = mock(TagProcessor.class);
        when(tagProcessor.getTagPath()).thenReturn("data");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                XMLStreamReader reader = (XMLStreamReader) invocationOnMock.getArguments()[0];
                while (reader.hasNext()) {
                  if(reader.next() == XMLStreamConstants.END_ELEMENT &&
                          reader.getName().toString().equals("data")) { break; }
                }
                return null;
            }
        }).when(tagProcessor).process(any(XMLStreamReader.class));


        TagEngine tagEngine = new TagEngine();
        tagEngine.add(tagProcessor);
        tagEngine.process(this.getClass().getResource("/xml/data.xml").getPath());

        verify(tagProcessor).process(notNull(XMLStreamReader.class));
    }

}
