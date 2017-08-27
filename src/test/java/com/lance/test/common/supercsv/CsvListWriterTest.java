package com.lance.test.common.supercsv;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.logging.Logger;

/**
 * Created by Lance on 2016/8/10.
 */
public class CsvListWriterTest {

    private Logger logger = Logger.getLogger(CsvListWriterTest.class.toString());

    private Writer writer = null;
    private ICsvListWriter csvWriter = null;
    private String outputFilename = "E:/tmp/test.csv";
    private String defaultEncoding = "GBK";

    @Before
    public void before() {
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outputFilename), defaultEncoding);
            csvWriter = new CsvListWriter(writer, CsvPreference.EXCEL_PREFERENCE);
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

    @Test
    public void testWrite() {
        try {
            String[] data = new String[]{"Lance", "LE"};
            csvWriter.write(data);
        } catch (IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }


}
