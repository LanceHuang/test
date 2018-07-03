package com.lance.test.ehcache.service.impl;

import com.lance.test.ehcache.service.IMsgService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MsgServiceImpl implements IMsgService {

    @Cacheable(value = "cacheTest", key = "#name")
    @Override
    public String greet(String name) {
        return "Hello: " + name + " , At " + (new Date());
    }
}
