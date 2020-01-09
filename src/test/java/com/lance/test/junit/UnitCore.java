package com.lance.test.junit;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @see org.junit.runner.JUnitCore
 */
public class UnitCore {

    public static void runClasses(Class... classes) {
        //Init
        List<TestDefinition> testDefinitionList = new LinkedList<>();
        for (Class clazz : classes) {
            TestDefinition testDefinition = new TestDefinition(clazz);
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent(Test.class)) {
                    testDefinition.addTestMethod(m);
                } else if (m.isAnnotationPresent(Before.class)) {
                    testDefinition.addBeforeMethod(m);
                } else if (m.isAnnotationPresent(After.class)) {
                    testDefinition.addAfterMethod(m);
                }
            }
            testDefinitionList.add(testDefinition);
        }

        //Init
        for (TestDefinition testDefinition : testDefinitionList) {
            testDefinition.getInstance();
        }

        //Run
        for (TestDefinition testDefinition : testDefinitionList) {
            System.out.println("============ Run " + testDefinition.getInstance().getClass() + " =============");
            long t1 = System.currentTimeMillis();

            testDefinition.runBefore();
            testDefinition.runTest();
            testDefinition.runAfter();

            long t2 = System.currentTimeMillis();
            System.out.println("Spend: " + (t2 - t1) + " ms");
        }
    }

}
