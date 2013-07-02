package examples.data;

import examples.processing.JAXBProcessor;
import examples.schema.DtypeOne;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;

public class DataOne extends JAXBProcessor<DtypeOne> {
    private static final String TAG_NAME = "data/dtype_one";


    public DataOne() throws JAXBException, SAXException {
        super(DtypeOne.class, TAG_NAME);
    }

    public DataOne(String schemaFileName) throws JAXBException, SAXException {
        super(DtypeOne.class, TAG_NAME, schemaFileName);
    }

    @Override
    public void doWork(DtypeOne element) {
//        System.out.println(element.getP1());
    }
}
