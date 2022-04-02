//package com.lance.test.common.entity;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
//import org.apache.hadoop.hbase.mapreduce.TableReducer;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//
//import java.io.IOException;
//
///**
// * Reduce key/value by adding same key to hbase.
// *
// * @author Lance
// * @since 2017/2/24
// */
//public class IntSumHBaseReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable> {
//
//    @Override
//    public void reduce(Text key, Iterable<IntWritable> values, Context context)
//            throws IOException, InterruptedException {
//        int sum = 0;
//        for (IntWritable value : values) {
//            sum += value.get();
//        }
//
//        Configuration conf = context.getConfiguration();
//
//        Put put = new Put(key.copyBytes());
//        put.addColumn(conf.get("hbase.reduce.column.family").getBytes(),
//                conf.get("hbase.reduce.column.name").getBytes(),
//                Bytes.toBytes(String.valueOf(sum)));
//        context.write(new ImmutableBytesWritable(key.copyBytes()), put);
//    }
//}