package com.lance.test.hadoop;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author Lance
 * @since 2017/2/26
 */
public class CreateGroup implements Watcher {

    private static final int SESSION_TIMEOUT = 5000;

    private ZooKeeper zk;


    public void connect(String hosts) throws IOException {
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
    }

    @Override
    public void process(WatchedEvent event) {

    }

    public void create(String groupName) {

    }

    public void close() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws Exception {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect("");
        createGroup.create("");
        createGroup.close();
    }
}
