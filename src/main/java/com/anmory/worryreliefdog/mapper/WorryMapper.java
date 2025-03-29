package com.anmory.worryreliefdog.mapper;

import com.anmory.worryreliefdog.model.Worry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午2:03
 */

@Mapper
public interface WorryMapper {
    @Insert("insert into worries (user_id, content) values (#{userId},#{content})")
    int addWorry(int userId, String content);

    @Select("select * from worries where user_id = #{userId};")
    List<Worry> selectWorry(int userId);

    @Select("select * from worries where user_id = #{userId} and content = #{content} and resolved = 0")
    Worry selectWorryByUserIdAndContent(int userId, String content);

    @Select("select worries.worry_id from worries where content = #{content} and resolved = 0")
    int selectWorryIdByContentAndNotResolved(String content);

    @Update("update worries set resolved = 1 where worry_id = #{worryId} and user_id = #{userId}")
    int resolveWorry(int userId, int worryId);

    @Delete("delete from worries where worry_id = #{worryId}")
    int deleteWorry(int worryId);

    @Select("select * from worries where worries.user_id = (select distinct full_name.user_id from full_name where full_name.name = #{fullName})")
    List<Worry> selectWorryByFullName(String fullName);
}
