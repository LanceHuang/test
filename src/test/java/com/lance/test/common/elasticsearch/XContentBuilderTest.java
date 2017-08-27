package com.lance.test.common.elasticsearch;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lance on 2016/8/11.
 */
public class XContentBuilderTest {

    @Test
    public void testField() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", 233);
        map.put("string", "hello");
        map.put("name", "Lance");

        try {
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();

            jsonBuild.startObject();
            for (Map.Entry<String, Object> e : map.entrySet()) {
                jsonBuild.field(e.getKey(), e.getValue());
            }
            jsonBuild.endObject();

            System.out.println(jsonBuild.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
