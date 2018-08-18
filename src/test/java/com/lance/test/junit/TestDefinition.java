package com.lance.test.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class TestDefinition {

    private List<Method> beforeMethods;
    private List<Method> afterMethods;
    private List<Method> testMethods;

    private Class clazz;
    private Object instance;

    public TestDefinition(Class clazz) {
        beforeMethods = new LinkedList<>();
        afterMethods = new LinkedList<>();
        testMethods = new LinkedList<>();
        this.clazz = clazz;
    }


    public void addTestMethod(Method m) {
        testMethods.add(m);
    }

    public void addBeforeMethod(Method m) {
        beforeMethods.add(m);
    }

    public void addAfterMethod(Method m) {
        afterMethods.add(m);
    }

    public Object getInstance() {
        if (null == instance) {
            synchronized (this) {
                if (null == instance) {
                    try {
                        instance = clazz.newInstance();
                    } catch (Exception e) {
                        //Do nothing
                    }
                }
            }
        }
        return instance;
    }

    public void runTest() {
        runMethods(testMethods);
    }

    public void runBefore() {
        runMethods(beforeMethods);
    }

    public void runAfter() {
        runMethods(afterMethods);
    }

    private void runMethods(List<Method> methodList) {
        for (Method m : methodList) {
            try {
                m.invoke(instance);
            } catch (Exception e) {
                //Do nothing
            }
        }
    }


}
