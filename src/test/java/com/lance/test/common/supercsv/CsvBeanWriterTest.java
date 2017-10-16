package com.lance.test.common.supercsv;


import org.junit.After;
import org.junit.Before;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * Created by Lance on 2016/8/10.
 */
public class CsvBeanWriterTest {

    private Logger logger = Logger.getLogger(CsvBeanWriterTest.class.toString());

    private Writer writer = null;
    private ICsvBeanWriter csvWriter = null;
    private String outputFilename = "E:/tmp/test.csv";
    private String defaultEncoding = "GBK";

    @Before
    public void before() {
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outputFilename), defaultEncoding);
            csvWriter = new CsvBeanWriter(writer, CsvPreference.EXCEL_PREFERENCE);
        } catch (IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            if (csvWriter != null) {
                csvWriter.flush();
                csvWriter.close();
            }

            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }

//    @Test
//    public void testWrite() {
//        try {
//            Article article = new Article();
//            article.setTitle("Java权威指南");
//            article.setContent("Hello world");
//            csvWriter.write(article, "title", "content");
//        } catch (IOException e) {
//            logger.info(e.getMessage());
//            e.printStackTrace();
//        }
//    }
}
