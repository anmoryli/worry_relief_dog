package com.anmory.worryreliefdog.service;

import com.anmory.worryreliefdog.mapper.FullNameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午10:56
 */

@Service
public class FullNameService {
    @Autowired
    FullNameMapper fullNameMapper;
    public int addFullName(int userId, String name) {
        return fullNameMapper.addFullName(userId, name);
    }
}
