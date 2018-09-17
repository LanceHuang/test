package com.lance.test.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

public class ProtobufTest {

    @Test
    public void test() {
        User.UserInfo userInfo = User.UserInfo.newBuilder()
                .setName("Lance")
                .setAge(24)
                .build();

        System.out.println(userInfo);

        for (byte b : userInfo.toByteArray()) {
            System.out.print(b);
            System.out.print(' ');
        }

    }

    @Test
    public void testDefault() throws InvalidProtocolBufferException {
        User.UserInfo userInfo = User.UserInfo.newBuilder()
                .setName("Lance")
                .build();
        System.out.println(userInfo);

        User.UserInfo newUserInfo = User.UserInfo.parseFrom(userInfo.toByteArray());
        System.out.println(newUserInfo);
        System.out.println(newUserInfo.getAge());
    }
}
