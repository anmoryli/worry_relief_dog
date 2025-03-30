package com.anmory.worryreliefdog.service;

import com.anmory.worryreliefdog.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-30 上午9:59
 */

@Service
public class HistoryService {
    @Autowired
    HistoryMapper historyMapper;
    public int addHistory(int userId, String fullName, String content, String advice) {
        return historyMapper.addHistory(userId, fullName, content, advice);
    }
}
