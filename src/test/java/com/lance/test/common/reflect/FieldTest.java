package com.lance.test.common.reflect;

import java.lang.reflect.Field;

import com.lance.common.entity.Article;
import org.apache.log4j.Logger;
import org.junit.Test;

public class FieldTest {

    private static Logger logger = Logger.getLogger(FieldTest.class);

    @Test
    public void testGet() {
        try {
            Article art = new Article();
            Field f = Article.class.getDeclaredField("title");
            if (!f.isAccessible()) f.setAccessible(true);
            System.out.println(f.get(art));
        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void test() {
        String authorFieldStr = "author";
        Article article = new Article();

        try {
            Field field = article.getClass().getDeclaredField(authorFieldStr);
            field.setAccessible(true);

            String author = field.get(article).toString();
            System.out.println("Author:" + author);

        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage(), e);
        } catch (SecurityException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
