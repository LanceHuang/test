package com.lance.test.common.entity;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * @author Lance
 * @since 2017/2/25
 */
public class SimpleMapper extends TableMapper<Text, IntWritable> {
    @Override
    protected void map(ImmutableBytesWritable key, Result values, Context context)
            throws IOException, InterruptedException {

        Configuration conf = context.getConfiguration();
        Cell cell = values.getColumnLatestCell(
                conf.get("hbase.reduce.column.family").getBytes(),
                conf.get("hbase.reduce.column.name").getBytes());

        context.write(new Text(Bytes.toString(key.get())),
                new IntWritable(
                        Integer.parseInt(Bytes.toString(
                                CellUtil.cloneValue(cell)))));
    }
}
