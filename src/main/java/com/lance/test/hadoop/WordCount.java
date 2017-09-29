package com.lance.test.hadoop;


import com.lance.test.common.entity.IntSumReducer;
import com.lance.test.common.entity.TokenizerMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Word count
 *
 * @author Lance
 * @since 2017/2/5
 */
public class WordCount {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException, InterruptedException {
        if (null == args || 2 != args.length) {
            throw new IllegalArgumentException("Lack of input/output path.");
        }
        String inputPath = args[0];
        String outputPath = args[1];

        Configuration conf = new Configuration();
        conf.addResource("core-site.xml");
        conf.addResource("mapred-site.xml");
        conf.addResource("yarn-site.xml");

        Job job = Job.getInstance(conf, "WordCount");

        //Set Mapper/Reducer
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);

        //Set output key/value format
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //Delete output path if exists
        Path path = new Path(outputPath);
        path.getFileSystem(conf).delete(path, true);

        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        //Submit job
        if (job.waitForCompletion(true)) {
            System.out.println("-----------------Completed-------------------");
        }
        System.out.println("-----------------Finished-----------------");
    }
}
