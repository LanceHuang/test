package com.lance.test.spark;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.junit.Test;
import scala.Tuple2;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * @author Lance
 * @since 2017/3/3
 */
public class SparkTest implements Serializable {

    @Test
    public void test() {
        //Start
        SparkConf conf = new SparkConf()
                .setMaster("local")
                .setAppName("yo");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //Action
        List<String> list = new ArrayList<String>();
        list.add("lance");
        list.add("leo");
        list.add("hi");

        JavaRDD<String> rdd = sc.parallelize(list);

        rdd.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });

        //Stop
        sc.stop();
    }

    @Test
    public void testReadHBase() {
        JavaSparkContext sc = new JavaSparkContext(
                new SparkConf().setMaster("local").setAppName("hbase reader"));

        try {
            String tableName = "words";
            Scan scan = new Scan();
            ClientProtos.Scan proto = ProtobufUtil.toScan(scan);
            String scanStr = Base64.encodeBytes(proto.toByteArray());

            Configuration conf = HBaseConfiguration.create();
            conf.set(TableInputFormat.INPUT_TABLE, tableName);
            conf.set(TableInputFormat.SCAN, scanStr);

            JavaPairRDD<ImmutableBytesWritable, Result> myRDD = sc.newAPIHadoopRDD(conf,
                    TableInputFormat.class,
                    ImmutableBytesWritable.class,
                    Result.class);

            myRDD.foreach(new VoidFunction<Tuple2<ImmutableBytesWritable, Result>>() {
                @Override
                public void call(Tuple2<ImmutableBytesWritable, Result> value) throws Exception {
                    System.out.println(CellUtil.toString(value._2().getColumnLatestCell(
                            "data".getBytes(),
                            "count".getBytes()),
                            true));
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.stop();
    }

    @Test
    public void testReadES() {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("hbase reader");
        //在spark中自动创建es中的索引
        conf.set("es.index.auto.create", "true");
        conf.set("es.nodes", "name01");
        conf.set("es.port", "9200");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaPairRDD<String, String> rdd = JavaEsSpark.esJsonRDD(sc, "trader/word");
        rdd.foreach(new VoidFunction<Tuple2<String, String>>() {
            @Override
            public void call(Tuple2<String, String> value) throws Exception {
                System.out.println(value._1 + ": " + value._2);
            }
        });

        sc.stop();
    }

    @Test
    public void testWrite2ES() {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("hbase reader");
        conf.set("es.index.auto.create", "true");
        conf.set("es.nodes", "name01");
        conf.set("es.port", "9200");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lineRDD = sc.textFile("hdfs://name01:9000/user/hadoop/input");

        JavaRDD<String> wordRDD = lineRDD.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String s) throws Exception {
                List<String> list = new LinkedList<String>();
                StringTokenizer tokenizer = new StringTokenizer(s);
                while (tokenizer.hasMoreTokens()) {
                    list.add(tokenizer.nextToken());
                }
                return list;
            }
        });


        JavaPairRDD<String, Integer> wordPairRDD = wordRDD.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        });

        JavaPairRDD<String, Integer> wordCountRDD = wordPairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        JavaRDD<Map<String, Object>> dataRDD = wordCountRDD.map(new Function<Tuple2<String, Integer>, Map<String, Object>>() {
            @Override
            public Map<String, Object> call(Tuple2<String, Integer> v1) throws Exception {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", v1._1);
                map.put("count", v1._2);
                map.put("length", v1._1.length());
                return map;
            }
        });

        JavaEsSpark.saveToEs(dataRDD, "trader/word");

        sc.stop();
    }

}
