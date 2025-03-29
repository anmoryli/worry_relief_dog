package com.anmory.worryreliefdog.service;

import com.anmory.worryreliefdog.mapper.AdviceMapper;
import com.anmory.worryreliefdog.model.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午2:21
 */

@Service
public class AdviceService {
    @Autowired
    AdviceMapper adviceMapper;

    public int addAdvice(int userId,int worryId,String content) {
        return adviceMapper.addAdvice(userId, worryId, content);
    }

    public Advice getAdviceRandomly(int worryId) {
        return adviceMapper.selectAdviceRandomly(worryId);
    }

    public int updateWorry(int worryId,String advice) {
        return adviceMapper.updateWorry(worryId, advice);
    }

    public int deleteAdvice(int worryId) {
        return adviceMapper.deleteAdvice(worryId);
    }
}
