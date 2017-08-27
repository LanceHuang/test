package com.lance.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.GenericOptionsParser;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Lance
 * @since 2017/2/22
 */
public class GenericOptionsParserTest {

    @Test
    public void test() throws IOException{
        String[] args = {"java", "-jar", "sth"};

        Configuration conf = new Configuration();
        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        System.out.println(Arrays.toString(parser.getRemainingArgs()));
    }
}
