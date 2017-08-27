package com.lance.test.common.beans;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * @author Lance
 * @date 2016/10/2
 */
public class IntrospectorTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(IntrospectorTest.class);
            PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor prop : props) {
                System.out.println(prop.getName());
                System.out.println(prop.getPropertyEditorClass());
                System.out.println(prop.getPropertyType());
                System.out.println(prop.getReadMethod());
                System.out.println(prop.getWriteMethod());
                System.out.println("-----------------------------");
            }
        } catch (IntrospectionException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
