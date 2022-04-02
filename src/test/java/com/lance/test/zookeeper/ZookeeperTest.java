//package com.lance.test.zookeeper;
//
//import org.apache.zookeeper.CreateMode;
//import org.apache.zookeeper.ZooDefs;
//import org.apache.zookeeper.ZooKeeper;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//
///**
// * @author Lance
// * @date 2018/1/24 16:29
// */
//public class ZookeeperTest {
//
//    private static final int TIME_OUT = 3000;
//    private static final String HOST = "192.168.138.129:2181";
//    private ZooKeeper zookeeper;
//
//    @Test
//    public void lifecycle() throws Exception {
//        System.out.println("=========创建节点===========");
//        create();
//
//        System.out.println("=============查看节点是否安装成功===============");
//        getData();
//
//        System.out.println("=========修改节点的数据==========");
//        setData();
//
//        System.out.println("========查看修改的节点是否成功=========");
//        getData();
//
//        System.out.println("=======删除节点==========");
//        delete();
//
//        System.out.println("==========查看节点是否被删除============");
//        exists();
//    }
//
//    @Test
//    public void create() throws Exception {
//        if (zookeeper.exists("/test", false) == null) {
//            zookeeper.create("/test", "znode1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        }
//    }
//
//    @Test
//    public void getData() throws Exception {
//        System.out.println(new String(zookeeper.getData("/test", false, null)));
//    }
//
//
//    @Test
//    public void setData() throws Exception {
//        String data = "zNode2";
//        zookeeper.setData("/test", data.getBytes(), -1);
//    }
//
//
//    @Test
//    public void delete() throws Exception {
//        zookeeper.delete("/test", -1);
//    }
//
//
//    @Test
//    public void exists() throws Exception {
//        System.out.println("节点状态：" + zookeeper.exists("/test", false));
//    }
//
//    @Before
//    public void before() throws IOException {
//        zookeeper = new ZooKeeper(HOST, TIME_OUT, null);
//    }
//
//    @After
//    public void after() throws Exception {
//        zookeeper.close();
//    }
//}