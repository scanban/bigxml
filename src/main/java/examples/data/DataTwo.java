package examples.data;

import examples.processing.JAXBProcessor;
import examples.schema.DtypeTwo;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;

public class DataTwo extends JAXBProcessor<DtypeTwo> {
    private static final String TAG_NAME = "data/dtype_two";


    public DataTwo() throws JAXBException, SAXException {
        super(DtypeTwo.class, TAG_NAME);
    }

    public DataTwo(String schemaFileName) throws JAXBException, SAXException {
        super(DtypeTwo.class, TAG_NAME, schemaFileName);
    }

    @Override
    public void doWork(DtypeTwo element) {
//        System.out.println(element.getP1());
    }
}
