package com.lance.test.hadoop;


import com.lance.test.common.entity.TokenizerMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Lance
 * @since 2017/2/5
 */
public class MRTest {

    @Test
    public void testMapper() throws IOException {
        Text value = new Text("What What nice");

        new MapDriver<LongWritable, Text, Text, IntWritable>()
                .withMapper(new TokenizerMapper())
                .withInput(new LongWritable(533), value)
                .withOutput(new Text("What"), new IntWritable(1))
                .withOutput(new Text("What"), new IntWritable(1))
                .withOutput(new Text("nice"), new IntWritable(1))
                .runTest();
    }

}
