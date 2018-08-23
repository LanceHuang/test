package com.lance.test.common.instrument;

import java.lang.instrument.Instrumentation;

/**
 *
 * <ol>
 * <li>javac -d . ObjectSize.java</li>
 * <li>echo "Premain-Class: com.lance.common.tool.util.ObjectSize\n" > 1.txt</li>
 * <li>jar cvfm ObjectHandler.jar 1.txt com</li>
 * <li>java -javaagent:ObjectHandler.jar XX</li>
 * </ol>
 * <p>
 * Demo:
 * <pre>
 * public class Demo {
 *     public static void main(String[] args) {
 *         System.out.println(ObjectSize.getObjectSize('1'));
 *         System.out.println(ObjectSize.getObjectSize((byte) 1));
 *         System.out.println(ObjectSize.getObjectSize((short) 1));
 *         System.out.println(ObjectSize.getObjectSize(1));
 *         System.out.println(ObjectSize.getObjectSize(1L));
 *         System.out.println(ObjectSize.getObjectSize(1F));
 *         System.out.println(ObjectSize.getObjectSize(1D));
 *     }
 * }
 * </pre>
 *
 * @author Lance
 * @since 2018-8-22 22:43:08
 */
public class ObjectSize {

    private static Instrumentation instrumentation;

    public static void premain(String arg, Instrumentation instru) {
        instrumentation = instru;
    }

    /**
     * Calculate object size
     */
    public static Long getObjectSize(Object obj) {
        if (null == instrumentation) {
            throw new IllegalStateException("instrumentation cannot be null");
        }

        return instrumentation.getObjectSize(obj);
    }
}
