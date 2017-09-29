package com.lance.test.common.lang;

import com.lance.test.common.entity.Article;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Lance
 * @date 2016/11/21 9:57
 */
public class ClassLoaderTest {

    @Test
    public void test() throws Exception {
        ClassLoader loader = this.getClass().getClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
    }

    @Test
    public void testGetClassLoader() throws Exception {
        //Current loader
        System.out.println(ClassLoaderTest.class.getClassLoader());
        ClassLoaderTest article = new ClassLoaderTest();
        System.out.println(article.getClass().getClassLoader());

        //New class's loader
        System.out.println(Class.forName("com.lance.common.entity.Article")
                .getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader()
                .getParent()
                .loadClass("com.lance.common.entity.Article")
                .getClassLoader());
    }

    @Test
    public void testMyLoader() throws Exception {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                System.out.println("Name: " + name);
                String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";

                try {
                    InputStream is = getClass().getResourceAsStream(filename);
                    if (null == is) {
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);

                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = loader.loadClass("com.lance.common.entity.Article");
        System.out.println(obj.getClass());
        System.out.println(obj instanceof Article);
    }
}
