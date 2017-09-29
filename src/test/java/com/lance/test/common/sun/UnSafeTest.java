package com.lance.test.common.sun;

import com.lance.test.common.entity.Article;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author Lance
 * @date 2016/10/30
 */
public class UnSafeTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testObjectFieldOffset() {
        Unsafe unsafe = Unsafe.getUnsafe();
        try {
            Field f = Article.class.getDeclaredField("title");
            System.out.println(unsafe.objectFieldOffset(f));
        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void test() {
        int num1 = 10;
        int num2 = -10;

        System.out.println(num1);
        System.out.println(-num1);
        System.out.println(num2);
        System.out.println(-num2);
    }

    @Test
    public void testCompareAndSwapLong() throws Exception {
//        Class<?> clazz = Unsafe.class;
//        Constructor<?> constructor = clazz.getDeclaredConstructor();
//        Unsafe unsafe = (Unsafe) constructor.newInstance();
        Class<Unsafe> clazz = Unsafe.class;
        Constructor<Unsafe> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Unsafe unsafe = constructor.newInstance();

        Article article = new Article();
        Field f = Article.class.getDeclaredField("id");
        long valueOffset = unsafe.objectFieldOffset(f);

        System.out.println("value1:" + article.getId());

        unsafe.compareAndSwapInt(article, valueOffset, 0, 100);
        System.out.println("value2:" + article.getId());
    }
}
