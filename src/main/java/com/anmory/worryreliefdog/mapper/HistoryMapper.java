package com.anmory.worryreliefdog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-30 上午9:57
 */

@Mapper
public interface HistoryMapper {
    @Insert("insert into history (user_id,full_name,content,advice) values (#{userId},#{fullName},#{content},#{advice})")
    int addHistory(int userId, String fullName, String content, String advice);
}
