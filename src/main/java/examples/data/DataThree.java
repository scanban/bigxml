package examples.data;

import examples.processing.JAXBProcessor;
import examples.schema.DtypeThree;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;

public class DataThree extends JAXBProcessor<DtypeThree> {
    private static final String TAG_NAME = "data/WS/dtype_three";

    public DataThree() throws JAXBException, SAXException {
        super(DtypeThree.class, TAG_NAME);
    }
    public DataThree(String schemaFileName) throws JAXBException, SAXException {
        super(DtypeThree.class, TAG_NAME, schemaFileName);
    }

    @Override
    public void doWork(DtypeThree element) {
//        System.out.println(element.getP1());
    }
}
