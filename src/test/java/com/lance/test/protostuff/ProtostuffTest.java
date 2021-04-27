package com.lance.test.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2021/4/27
 */
public class ProtostuffTest {

    @Test
    public void test() {
        User user = new User();
        user.setName("Lance");
        user.setAge(24);

        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        Schema<User> schema = RuntimeSchema.getSchema(User.class);
        byte[] data = ProtostuffIOUtil.toByteArray(user, schema, buffer);
        for (byte b : data) {
            System.out.print(b);
            System.out.print(' ');
        }
        buffer.clear();

        User newUser = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, newUser, schema);
        System.out.println(newUser);
    }
}
