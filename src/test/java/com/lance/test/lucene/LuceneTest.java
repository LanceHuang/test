package com.lance.test.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

/**
 * Index local files, just for learning source code
 *
 * @author Lance
 * @since 2018/11/29 17:20
 */
public class LuceneTest {

    /** Path that save index files */
    private String      indexPath = "C:\\software\\code\\test\\index";
    private String      docsPath  = "C:\\software\\project\\opensource\\openjdk";
    private String[]    suffixArr = {
            "java", "cpp", "hpp"
    };
    private Set<String> suffixSet;

    private static final String FIELD_NAME_PATH    = "path";
    private static final String FIELD_NAME_CONTENT = "content";

    @Before
    public void before() {
        suffixSet = new HashSet<>();
        for (String s : suffixArr) {
            suffixSet.add(s);
        }
    }

    @Test
    public void testWrite() throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexPath));
        IndexWriterConfig config = new IndexWriterConfig();
        IndexWriter indexWriter = new IndexWriter(dir, config);


        Files.walkFileTree(Paths.get(docsPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String absolutePath = file.toString();
                int index = absolutePath.lastIndexOf('.');
                if (index < 0) {
                    return FileVisitResult.CONTINUE;
                }
                if (index + 1 >= absolutePath.length()) {
                    return FileVisitResult.CONTINUE;
                }

                String suffix = absolutePath.substring(index + 1);
                if (suffixSet.contains(suffix)) {
                    indexFile(indexWriter, file);
                }

                return FileVisitResult.CONTINUE;
            }
        });

        indexWriter.commit();
        indexWriter.close();
        dir.close();
    }

    private void indexFile(IndexWriter indexWriter, Path file) {
        InputStream ins = null;
        try {
            Document doc = new Document();
            String absolutePath = file.toString();
            doc.add(new StringField(FIELD_NAME_PATH, absolutePath, Field.Store.YES));
            ins = Files.newInputStream(file);
            doc.add(new TextField(FIELD_NAME_CONTENT, new BufferedReader(new InputStreamReader(ins))));

            indexWriter.addDocument(doc);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != ins) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testRead() throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexPath));
        IndexReader indexReader = DirectoryReader.open(dir);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        String keyWord = "parkBlocker";

        Term term = new Term(FIELD_NAME_CONTENT, keyWord.toLowerCase());
        Query query = new TermQuery(term);
        TopDocs topDocs = indexSearcher.search(query, 10);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            System.out.print(scoreDoc + "\t");
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.println(doc.getField(FIELD_NAME_PATH).stringValue());
        }

        indexReader.close();
        dir.close();
    }
}



