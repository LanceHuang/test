package com.lance.test.mybatisplus.model;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes({UserInfo.class})
public class UserInfoTypeHandler extends JacksonTypeHandler {

    public UserInfoTypeHandler(Class<?> type) {
        super(type);
    }
}
