package com.lance.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.NullOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * @author Lance
 * @since 2017/2/22
 */
public class RowCounter {

    static final String NAME = "rowCounter";

    public static class RowCounterMapper extends TableMapper<ImmutableBytesWritable, Result> {

        public static enum Counters {ROWS}

        @Override
        protected void map(ImmutableBytesWritable key, Result values, Context context)
                throws IOException, InterruptedException {
            for (KeyValue value : values.list()) {
                if (value.getValue().length > 0) {
                    context.getCounter(Counters.ROWS).increment(1);
                    break;
                }
            }
        }
    }

    public static Job createSubmittableJob(Configuration conf, String[] args)
            throws IOException {
        String tableName = args[0];

        Job job = Job.getInstance(conf, NAME + "_" + tableName);
        job.setJarByClass(RowCounter.class);

        //拼接参数
        StringBuilder sb = new StringBuilder();
        final int columnOffset = 1;
        for (int i = columnOffset; i < args.length; i++) {
            if (i > columnOffset) {
                sb.append(" ");
            }
            sb.append(args[i]);
        }

        //设置遍历
        Scan scan = new Scan();
        scan.setFilter(new FirstKeyOnlyFilter());
        if (sb.length() > 0) {
            for (String columnName : sb.toString().split(" ")) {
                String[] fields = columnName.split(":");
                if (fields.length == 1) {
                    scan.addFamily(Bytes.toBytes(fields[0]));
                } else {
                    scan.addColumn(Bytes.toBytes(fields[0]), Bytes.toBytes(fields[1]));
                }
            }
        }

        //设置job
        job.setOutputFormatClass(NullOutputFormat.class);
        TableMapReduceUtil.initTableMapperJob(tableName,
                scan,
                RowCounterMapper.class,
                ImmutableBytesWritable.class,
                Result.class,
                job);

        job.setNumReduceTasks(0);

        return job;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = HBaseConfiguration.create();
        conf.addResource("core-site.xml");
        conf.addResource("hdfs-site.xml");
        conf.addResource("mapred-site.xml");
        conf.addResource("yarn-site.xml");
        conf.addResource("hbase-site.xml");

        String[] arguments = {"test"};
        String[] otherArgs = new GenericOptionsParser(conf, arguments).getRemainingArgs();

        Job job = createSubmittableJob(conf, otherArgs);
        if (job.waitForCompletion(true)) {
            System.out.println("---------Completed------------");
        }
        System.out.println("---------Finished------------");
    }
}
