package com.lance.test.common.supercsv;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Lance on 2016/8/10.
 */
public class CsvMapWriterTest {
    private Logger logger = Logger.getLogger(CsvMapWriterTest.class.toString());

    private Writer writer = null;
    private ICsvMapWriter csvWriter = null;
    private String outputFilename = "E:/tmp/test.csv";
    private String defaultEncoding = "GBK";

    @Before
    public void before() {
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outputFilename), defaultEncoding);
            csvWriter = new CsvMapWriter(writer, CsvPreference.EXCEL_PREFERENCE);
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
            String[] head = {"Lance", "以", "Lanc呵"};
            Map<String, Integer> mapData = new HashMap<String, Integer>();
            mapData.put("Lance", 212);
            mapData.put("以", 88);
            mapData.put("Lanc呵", 33);
//            csvWriter.writeHeader("hi","asa");
            csvWriter.write(mapData, head);
        } catch (IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }
}
