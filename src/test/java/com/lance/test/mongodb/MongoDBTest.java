package com.lance.test.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Lance
 */
public class MongoDBTest {

    private MongoClient client;

    @Before
    public void before() {
        client = MongoClients.create("mongodb://localhost:27017");
    }

    @After
    public void after() {
        client.close();
    }

    @Test
    public void test() {
        // C
        MongoDatabase db = client.getDatabase("common");
        MongoCollection<Document> coll = db.getCollection("test");
        coll.insertOne(new Document().append("name", "Lance").append("age", 26));
        coll.insertOne(new Document().append("name", "Alice").append("age", 25).append("message", "love"));
        coll.insertOne(new Document().append("name", "Leo"));

        // R
        for (Document doc : coll.find()) {
            System.out.println(doc);
        }
        System.out.println();

        // U
        coll.updateMany(Filters.eq("age", 26), new Document("$set", new Document("age", 24)));
        for (Document doc : coll.find()) {
            System.out.println(doc);
        }
        System.out.println();

        // D
        coll.deleteOne(Filters.eq("age", 24));
        for (Document doc : coll.find()) {
            System.out.println(doc);
        }
        System.out.println();

        coll.drop();
        db.drop();
    }

    @Test
    public void testItem() {
        MongoDatabase db = client.getDatabase("game");
        MongoCollection<Document> coll = db.getCollection("item");

        // todo
    }
}
