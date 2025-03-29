package com.anmory.worryreliefdog.service;

import com.anmory.worryreliefdog.mapper.UserMapper;
import com.anmory.worryreliefdog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午1:17
 */

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User getUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    public int addUser(String name, String password, String email) {
        return userMapper.addUser(name,password,email);
    }

    public User getUserByNameEmail(String name, String email) {
        return userMapper.selectUserByNameEmail(name, email);
    }

    public int changePassword(String name, String password,String email) {
        return userMapper.changePassword(name, password,email);
    }
}
