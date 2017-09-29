package com.lance.test.hadoop;


import com.lance.common.tool.util.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Lance
 * @since 2017/2/14
 */
public class HBaseTest {

    private Configuration conf = null;
    private Connection conn = null;
    private Admin admin = null;

    private String tableName = "words";
    private String columnFamilyName = "data";

    @Test
    public void testCreateTable() throws IOException {
        //建表
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor columnFamilyDescriptor = new HColumnDescriptor(columnFamilyName);
        tableDescriptor.addFamily(columnFamilyDescriptor);
        admin.createTable(tableDescriptor);
    }

    @Test
    public void testDropTable() throws IOException {
        //删表
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
    }

    @Test
    public void testPut() {
        Table table = null;
        try {
            //添加数据
            table = conn.getTable(TableName.valueOf(tableName));
            for (int i = 0; i < 132; i++) {
                Put put = new Put(Bytes.toBytes("row" + i));
                put.addColumn(Bytes.toBytes(columnFamilyName),
                        Bytes.toBytes("col22"),
                        Bytes.toBytes("value" + (i * i)));
                table.put(put);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeQuietly(table);
        }
    }

    @Test
    public void testGet() {
        Table table = null;
        try {
            //查询数据
            table = conn.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes("row5"));

            //遍历行单元格
            Result result = table.get(get);
            CellScanner scan = result.cellScanner();
            while (scan.advance()) {
                Cell cell = scan.current();
                System.out.println(CellUtil.toString(cell, true));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeQuietly(table);
        }
    }

    @Test
    public void testDelete() {
        Table table = null;
        try {
            //查询数据
            table = conn.getTable(TableName.valueOf(tableName));
            Delete delete = new Delete(Bytes.toBytes("row5"));
            delete.addColumn(Bytes.toBytes(columnFamilyName), Bytes.toBytes("col1"));
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeQuietly(table);
        }
    }

    @Test
    public void testScan() {
        Table table = null;
        ResultScanner resultScanner = null;
        try {
            //遍历数据
            table = conn.getTable(TableName.valueOf(tableName));
            resultScanner = table.getScanner(new Scan());
            for (Result result : resultScanner) {
                CellScanner cellScanner = result.cellScanner();
                while (cellScanner.advance()) {
                    System.out.print(CellUtil.toString(cellScanner.current(), true) + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeQuietly(resultScanner);
            FileUtils.closeQuietly(table);
        }
    }

    @Before
    public void before() throws IOException {
        conf = HBaseConfiguration.create();
        conf.addResource("hbase-site.xml");

        conn = ConnectionFactory.createConnection(conf);
        admin = conn.getAdmin();
    }

    @After
    public void after() {
        FileUtils.closeQuietly(admin);
        FileUtils.closeQuietly(conn);
    }
}
