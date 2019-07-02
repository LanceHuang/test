package com.lance.test.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.junit.Test;

/**
 *
 * @author Lance
 * @since 2018/12/3 21:31
 */
public class AnalyzerTest {



    @Test
    public void test() {
        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String fieldName) {
                return null;
            }
        };



    }


}
