package com.lance.test.common;

import com.lance.common.util.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lance on 2016/8/10.
 */
public class MockitoTest {

    @Test
    public void testWhen() {
        List<String> testList = Mockito.mock(List.class);

        Mockito.when(testList.get(0)).thenReturn("Hello mockito");
        Mockito.when(testList.get(1)).thenThrow(new ArrayIndexOutOfBoundsException("越界啦！！！！"));

        Assert.assertEquals(testList.get(0), "Hello mockito");
//        Assert.assertEquals(testList.get(1), "Hello mockito");

        Mockito.verify(testList).get(0);
        Mockito.verify(testList).get(1);
    }

    @Test
    public void testMock() {
        List<String> testList = Mockito.mock(List.class);

        testList.add("asa ");
        System.out.println(testList.get(0));

        LinkedList<String> linkedList = Mockito.mock(LinkedList.class);
        linkedList.add("asaaa");
        System.out.println(linkedList.size() + " " + linkedList.get(0));
    }
}
