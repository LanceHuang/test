package com.lance.test.common;

/**
 * @author Lance
 * @date 2016/11/22
 */
public class FinalizeEscapeGCDemo {

    public static void main(String[] args) {
        FinalizeEscapeGC.obj = new FinalizeEscapeGC();
        FinalizeEscapeGC.obj = null;
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (null != FinalizeEscapeGC.obj) {
            FinalizeEscapeGC.obj.isAlive();
        } else {
            System.out.println("null");
        }

        FinalizeEscapeGC.obj = null;
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (null != FinalizeEscapeGC.obj) {
            FinalizeEscapeGC.obj.isAlive();
        } else {
            System.out.println("null");
        }

        System.out.println("finished.");
    }

}