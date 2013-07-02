package examples;


import examples.bench.XMLBenchmarkInputStream;
import examples.data.DataOne;
import examples.data.DataThree;
import examples.data.DataThreeXSLT;
import examples.processing.TagEngine;


public class App {

    public static final String XML_TEST_HEADER = "<data>";
    public static final String XML_TEST_FOOTER = "</data>";
    public static final String XML_TEST_BODY = "    <dtype_one>\n" +
            "        <p1>p1_data_1</p1>\n" +
            "        <p2>p1_data_1</p2>\n" +
            "        <p3>p1_data_1</p3>\n" +
            "        <p4>p1_data_1</p4>\n" +
            "        <p5>p1_data_1</p5>\n" +
            "    </dtype_one>\n" +
            "    <dtype_two>\n" +
            "        <p1>p1_data_2</p1>\n" +
            "        <p2>p1_data_2</p2>\n" +
            "        <p3>p1_data_2</p3>\n" +
            "        <p4>p1_data_2</p4>\n" +
            "        <p5>p1_data_2</p5>\n" +
            "    </dtype_two>\n" +
            "    <WS>\n" +
            "        <dtype_three>\n" +
            "            <p1>p1_data_3</p1>\n" +
            "            <p2>p1_data_3</p2>\n" +
            "            <p3>p1_data_3</p3>\n" +
            "            <p4>p1_data_3</p4>\n" +
            "            <p5>p1_data_3</p5>\n" +
            "        </dtype_three>\n" +
            "    </WS>";

    private static final String XML_SCHEMA_FILE_NAME = "data.xsd";

    private static void test_noverify(int repeatCount) throws Throwable {
        TagEngine tagEngine = new TagEngine();
        DataOne dataOne = new DataOne();
        DataThree dataThree = new DataThree();
        XMLBenchmarkInputStream xstream =
                new XMLBenchmarkInputStream(repeatCount, XML_TEST_HEADER, XML_TEST_BODY,
                        XML_TEST_FOOTER);

        tagEngine.add(dataOne);
        tagEngine.add(dataThree);

        long millis = System.currentTimeMillis();
        tagEngine.process(xstream);
        long endMillis = System.currentTimeMillis();
        System.out.println("Runtime: " + (endMillis - millis) + "ms, " +
                xstream.getByteCount() + " bytes processed");
    }

    private static void test_verify(int repeatCount) throws Throwable {
        TagEngine tagEngine = new TagEngine();
        DataOne dataOne = new DataOne(XML_SCHEMA_FILE_NAME);
        DataThree dataThree = new DataThree(XML_SCHEMA_FILE_NAME);
        XMLBenchmarkInputStream xstream =
                new XMLBenchmarkInputStream(repeatCount, XML_TEST_HEADER, XML_TEST_BODY,
                        XML_TEST_FOOTER);

        tagEngine.add(dataOne);
        tagEngine.add(dataThree);

        long millis = System.currentTimeMillis();
        tagEngine.process(xstream);
        long endMillis = System.currentTimeMillis();
        System.out.println("Runtime: " + (endMillis - millis) + "ms, " +
                xstream.getByteCount() + " bytes processed");
    }

    private static void test_XSLT(int repeatCount) throws Throwable {
        TagEngine tagEngine = new TagEngine();
        DataThreeXSLT dataThree = new DataThreeXSLT();
        XMLBenchmarkInputStream xstream =
                new XMLBenchmarkInputStream(repeatCount, XML_TEST_HEADER, XML_TEST_BODY,
                        XML_TEST_FOOTER);

        tagEngine.add(dataThree);

        long millis = System.currentTimeMillis();
        tagEngine.process(xstream);
        long endMillis = System.currentTimeMillis();
        System.out.println("Runtime: " + (endMillis - millis) + "ms, " +
                xstream.getByteCount() + " bytes processed");
    }



    public static void main(String[] args) throws Throwable {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("JAXB unmarshall without schema validation");
        test_noverify(500000);
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 + "MB");
        System.gc();
        System.out.println("JAXB unmarshall with schema validation");
        test_verify(500000);
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 + "MB");

        System.gc();
        System.out.println("XSLT processing");
        test_XSLT(500000);
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 + "MB");

    }
}
