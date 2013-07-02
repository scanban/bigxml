package examples.processing;

import examples.data.DataOne;
import examples.data.DataThree;
import examples.schema.DtypeOne;
import examples.schema.DtypeThree;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

public class JAXBProcessorTest {

    @Test
    public void testDataOne() throws JAXBException, SAXException, FileNotFoundException, XMLStreamException, TransformerException {
        TagEngine tagEngine = new TagEngine();
        DataOne dataOne = spy(new DataOne());

        tagEngine.add(dataOne);
        ArgumentCaptor<DtypeOne> aDtypeOne = ArgumentCaptor.forClass(DtypeOne.class);
        tagEngine.process(this.getClass().getResource("/xml/data.xml").getPath());
        verify(dataOne).doWork(aDtypeOne.capture());

        assertThat(aDtypeOne.getValue().getP1(), is("p1_1_data"));
        assertThat(aDtypeOne.getValue().getP5(), is("p1_5_data"));
    }

    @Test
    public void testDataThree() throws JAXBException, SAXException, FileNotFoundException, XMLStreamException, TransformerException {
        TagEngine tagEngine = new TagEngine();
        DataThree dataThree = spy(new DataThree());

        tagEngine.add(dataThree);
        ArgumentCaptor<DtypeThree> aDtypeThree = ArgumentCaptor.forClass(DtypeThree.class);
        tagEngine.process(this.getClass().getResource("/xml/data.xml").getPath());
        verify(dataThree).doWork(aDtypeThree.capture());

        assertThat(aDtypeThree.getValue().getP1(), is("p3_1_data"));
        assertThat(aDtypeThree.getValue().getP5(), is("p3_5_data"));
    }
}
