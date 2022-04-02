//package com.lance.test.common.entity;
//
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Mapper;
//
//import java.io.IOException;
//import java.util.StringTokenizer;
//
///**
// * Split per-line into (word, 1) by " ".
// *
// * @author Lance
// * @since 2017/2/23
// */
//public class TokenizerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
//
//    private static IntWritable one = new IntWritable(1);//All adaptable
//    private Text word = new Text(); //Don't need to new a Text' object in method
//
//    @Override
//    public void map(LongWritable key, Text value, Context context)
//            throws IOException, InterruptedException {
//        StringTokenizer tokenizer = new StringTokenizer(value.toString());
//        while (tokenizer.hasMoreTokens()) {
//            word.set(tokenizer.nextToken());
//            context.write(word, one);
//        }
//    }
//}
