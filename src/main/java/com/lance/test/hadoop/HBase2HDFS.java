package com.lance.test.hadoop;

import com.lance.common.entity.SimpleMapper;
import com.lance.common.entity.TokenizerMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Export wordCount data from hbase to hdfs.
 *
 * @author Lance
 * @since 2017/2/25
 */
public class HBase2HDFS {
    public static void main(String[] args)
            throws ClassNotFoundException, InterruptedException, IOException {

        if (4 != args.length) {
            System.err.println("ERROR: Wrong number of parameters: " + args.length);
            throw new IllegalArgumentException(
                    "Usage: HBase2HDFS <TABLE_NAME> <COLUMN_FAMILY> <COLUMN_NAME> <OUTPUT_PATH>");
        }

        Configuration conf = getConf();
        conf.set("hbase.reduce.column.family", args[1]);
        conf.set("hbase.reduce.column.name", args[2]);

        Job job = createSubmittableJob(conf, args);
        if (job.waitForCompletion(true)) {
            System.out.println("------------Completed-------------");
        }
        System.out.println("-----------Finished-----------------");
    }

    public static Configuration getConf() {
        Configuration conf = new Configuration();
        conf.addResource("core-site.xml");
        conf.addResource("hdfs-site.xml");
        conf.addResource("mapred-site.xml");
        conf.addResource("yarn-site.xml");
        conf.addResource("hbase-site.xml");
        return conf;
    }

    public static Job createSubmittableJob(Configuration conf, String[] args) throws IOException {
        String tableName = args[0];
        String outputPath = args[3];

        Job job = Job.getInstance(conf, "HBase2HDFS");

        job.setMapperClass(TokenizerMapper.class);
        job.setNumReduceTasks(0);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        Scan scan = new Scan();
        scan.addColumn(conf.get("hbase.reduce.column.family").getBytes(),
                conf.get("hbase.reduce.column.name").getBytes());

        TableMapReduceUtil.initTableMapperJob(tableName,
                scan,
                SimpleMapper.class,
                Text.class, IntWritable.class,
                job);

        return job;
    }
}
