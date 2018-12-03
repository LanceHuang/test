package com.lance.test.lucene.mock;

import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lance
 * @since 2018/12/1 14:29
 */
public class MockLuceneTest {

    private static final String PREFIX_TITLE       = "title_";
    private static final String FIELD_NAME_TITLE   = "title";
    private static final String FIELD_NAME_CONTENT = "content";

    private String[] contents = {
            "Hello world",
            "Hello lucene",
            "hi",
            "hello",
            "ca"
    };

    @Test
    public void test() throws IOException {
        Directory dir = new Directory();

        //Index
        IndexReader indexReader = new IndexReader(dir);
        for (String cnt : contents) {
            Document doc = new Document();
            doc.add(new StringField(FIELD_NAME_TITLE, PREFIX_TITLE + cnt));
            doc.add(new TextField(FIELD_NAME_CONTENT, cnt));
            indexReader.add(doc);
        }

        indexReader.commit();
        indexReader.close();

        //Search
        String keyword = "lucene";
        IndexSearcher indexSearcher = new IndexSearcher(dir);
        List<DocumentInfo> docInfos = indexSearcher.search(keyword);
        for (DocumentInfo info : docInfos) {
            System.out.println(info.getId() + " " + info.getIndex());
            Document doc = indexSearcher.doc(info.getId());

            Field field = doc.getField(FIELD_NAME_TITLE);
            if (field instanceof StringField) {
                System.out.println(((StringField) field).getValue());
            }
        }
    }
}


class Document {

    private int                id;
    private Map<String, Field> fields;

    private static final AtomicInteger nextId = new AtomicInteger(0);

    public Document() {
        this.id = nextId.getAndIncrement();
        this.fields = new HashMap<>();
    }

    public void add(Field f) {
        if (null == f || null == f.getName()) {
            throw new IllegalArgumentException();
        }

        fields.put(f.getName(), f);
    }

    public int getId() {
        return id;
    }

    Map<String, Field> getFields() {
        return Collections.unmodifiableMap(fields);
    }

    public Field getField(String name) {
        return fields.get(name);
    }
}

abstract class Field {
    protected String name;

    public String getName() {
        return name;
    }
}

class StringField extends Field {
    private String value;

    public StringField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

class TextField extends Field {
    private String value;

    public TextField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


class Directory {
    private Map<Integer, Document>          documentMap;
    private Map<String, List<DocumentInfo>> indexes;

    public Directory() {
        documentMap = new HashMap<>();
        indexes = new HashMap<>();
    }

    public Map<Integer, Document> getDocumentMap() {
        return documentMap;
    }

    public Map<String, List<DocumentInfo>> getIndexes() {
        return indexes;
    }
}

class DocumentInfo {
    private int id;
    private int index;

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    void setIndex(int index) {
        this.index = index;
    }
}

class IndexReader implements Closeable {

    private Directory      dir;
    private List<Document> docs;

    public IndexReader(Directory dir) {
        this.dir = dir;
        this.docs = new LinkedList<>();
    }


    public void add(Document doc) {
        if (null == doc) {
            throw new IllegalArgumentException();
        }

        docs.add(doc);
    }


    @Override
    public void close() throws IOException {
        //todo
    }

    /**
     * Commit after all documents are ready..。
     */
    public void commit() {

        Map<Integer, Document> documentMap = dir.getDocumentMap();
        Map<String, List<DocumentInfo>> indexes = dir.getIndexes();

        for (Document doc : docs) {
            documentMap.put(doc.getId(), doc);

            Map<String, Field> fields = doc.getFields();
            for (Map.Entry<String, Field> entry : fields.entrySet()) {
                Field field = entry.getValue();

                if (field instanceof TextField) {
                    TextField textField = (TextField) field;
                    String txt = textField.getValue();

                    for (String token : txt.split(" ")) {
                        token = token.toLowerCase();
                        if (!indexes.containsKey(token)) {
                            indexes.put(token, new LinkedList<>());
                        }


                        DocumentInfo documentInfo = new DocumentInfo();
                        documentInfo.setId(doc.getId());
                        indexes.get(token).add(documentInfo);
                    }
                }
            }
        }
    }
}

class IndexSearcher {

    private Directory dir;

    public IndexSearcher(Directory dir) {
        this.dir = dir;
    }

    /**
     * Filtering those documents, contains keyword
     */
    public List<DocumentInfo> search(String keyword) {
        return dir.getIndexes().get(keyword);
    }

    public Document doc(int id) {
        return dir.getDocumentMap().get(id);
    }
}

