package com.lance.test.common;

/**
 * @author Lance
 * @date 2016/11/22
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC obj = null;

    public void isAlive() {
        System.out.println("Object is alive.");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        obj = this;
        System.out.println("finalize");
    }
}
