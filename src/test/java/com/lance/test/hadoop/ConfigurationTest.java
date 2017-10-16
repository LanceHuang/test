package com.lance.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.junit.Test;

/**
 * @author Lance
 * @since 2017/2/17
 */
public class ConfigurationTest {

    @Test
    public void test() {
        Configuration conf = new Configuration();
        System.out.println(conf.get("fs.default.name"));
        System.out.println(conf.get("mapred.jar"));
        System.out.println(conf.get("mapreduce.framework.name"));
        System.out.println(conf.get("mapreduce.app-submission.cross-platform"));
        System.out.println(conf.get("yarn.resourcemanager.hostname"));
    }

    @Test
    public void testAddResource() {
        Configuration conf = new Configuration();
        conf.addResource("hdfs-site.xml");
        System.out.println(conf.size());
    }

//    @Test
//    public void testSetClass() {
//        Configuration conf = new Configuration(false);
//        conf.setClass("mapper", TokenizerMapper.class, Mapper.class);
//        System.out.println(conf.getClass("mapper", null));
//    }
}
