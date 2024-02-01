package com.manage.service.impl;


import com.manage.entity.User;
import com.manage.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class IUserServiceTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findById() {
        User user = userMapper.selectById(21);
        System.out.println(user);
    }
}