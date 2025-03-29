package com.anmory.worryreliefdog.mapper;

import com.anmory.worryreliefdog.model.Advice;
import org.apache.ibatis.annotations.*;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午2:18
 */

@Mapper
public interface AdviceMapper {

    @Insert("insert into advice (user_id, worry_id, advice) values (#{userId},#{worryId},#{content})")
    int addAdvice(int userId, int worryId, String content);

    @Select("select * from advice where worry_id = #{worryId} ORDER BY RAND() limit 1")
    Advice selectAdviceRandomly(int worryId);

    @Update("update worries set advice = #{advice} where worry_id = #{worryId}")
    int updateWorry(int worryId, String advice);

    @Delete("delete from advice where worry_id = #{worryId}")
    int deleteAdvice(int worryId);
}
