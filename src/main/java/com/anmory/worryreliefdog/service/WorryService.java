package com.anmory.worryreliefdog.service;

import com.anmory.worryreliefdog.mapper.WorryMapper;
import com.anmory.worryreliefdog.model.Worry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午2:07
 */

@Service
public class WorryService {
    @Autowired
    WorryMapper worryMapper;
    public int addWorry(int userId, String content) {
        return worryMapper.addWorry(userId, content);
    }

    public List<Worry> getWorry(int userId) {
        return worryMapper.selectWorry(userId);
    }

    public Worry getWorryByUserIdAndContent(int userId, String content) {
        return worryMapper.selectWorryByUserIdAndContent(userId, content);
    }

    public int getWorryIdByContentAndNotResolved(String content) {
        return worryMapper.selectWorryIdByContentAndNotResolved(content);
    }

    public int resolveWorry(int userId, int worryId) {
        return worryMapper.resolveWorry(userId, worryId);
    }

    public int deleteWorry(int worryId) {
        return worryMapper.deleteWorry(worryId);
    }

    public List<Worry> getWorryByFullName(String fullName) {
        return worryMapper.selectWorryByFullName(fullName);
    }
}
