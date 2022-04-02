//package com.lance.test.hadoop;
//
//
//import com.lance.test.common.entity.IntSumHBaseReducer;
//import com.lance.test.common.entity.IntSumReducer;
//import com.lance.test.common.entity.TokenizerMapper;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//
//import java.io.IOException;
//
///**
// * @author Lance
// * @since 2017/2/23
// */
//public class WordCountHBase {
//
//    public static void main(String[] args)
//            throws ClassNotFoundException, InterruptedException, IOException {
//
//        if (4 != args.length) {
//            System.err.println("ERROR: Wrong number of parameters: " + args.length);
//            throw new IllegalArgumentException(
//                    "Usage: WordCountHBase <INPUT_PATH> <TABLE_NAME> <COLUMN_FAMILY> <COLUMN_NAME>");
//        }
//
//        Configuration conf = getConf();
//        conf.set("hbase.reduce.column.family", args[2]);
//        conf.set("hbase.reduce.column.name", args[3]);
//
//        Job job = createSubmittableJob(conf, args);
//        if (job.waitForCompletion(true)) {
//            System.out.println("------------Completed-------------");
//        }
//        System.out.println("-----------Finished-----------------");
//    }
//
//    /**
//     * Read core-site.xml, hdfs-site.xml, mapred-site.xml, yarn-site.xml, hbase-site.xml.
//     */
//    public static Configuration getConf() {
//        Configuration conf = new Configuration();
//        //hadoop conf
//        conf.addResource("core-site.xml");
//        conf.addResource("hdfs-site.xml");
//        conf.addResource("mapred-site.xml");
//        conf.addResource("yarn-site.xml");
//        //hbase conf
//        conf.addResource("hbase-site.xml");
//
//        return conf;
//    }
//
//    public static Job createSubmittableJob(Configuration conf, String[] args) throws IOException {
//        String inputPath = args[0];
//        String tableName = args[1];
//
//        Job job = Job.getInstance(conf, "WordCountHBase");
//
//        job.setMapperClass(TokenizerMapper.class);
//        job.setCombinerClass(IntSumReducer.class);
//        job.setReducerClass(IntSumHBaseReducer.class);
//
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(IntWritable.class);
//
//        FileInputFormat.addInputPath(job, new Path(inputPath));
//        TableMapReduceUtil.initTableReducerJob(tableName, IntSumHBaseReducer.class, job);
//
//        return job;
//    }
//
//}
