package com.lance.test.common.pool2;

import lombok.Getter;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Lance
 * @since 2021/4/12
 */
@Getter
public class TConnection implements Closeable {

    private int id;

    private boolean active;

    public TConnection(int id) {
        this.id = id;
        System.out.println("Create connection: " + this.id);
    }

    public void init() {
        System.out.println("Init connection: " + this.id);
        this.active = true;
    }

    public void use() {
        System.out.println("Use connection: " + this.id);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Release connection: " + this.id);
        this.active = false;
    }
}
