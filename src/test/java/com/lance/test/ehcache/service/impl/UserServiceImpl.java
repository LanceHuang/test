package com.lance.test.ehcache.service.impl;

import com.lance.test.common.entity.User;
import com.lance.test.ehcache.service.IUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Cacheable(cacheNames = "cacheTest", key = "#id")
    @Override
    public User getUserById(int id) {
        User user = new User();
        user.setId(id * 10);
        user.setUsername("lance:" + id);
        System.out.println("====== getUserById =======");

        return user;
    }
}
