package com.anmory.worryreliefdog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午10:55
 */

@Mapper
public interface FullNameMapper {
    @Insert("insert into full_name (user_id, name) values (#{userId},#{name})")
    int addFullName(int userId, String name);

    @Select("select distinct name from full_name where user_id = #{userId}")
    String selectFullName(int userId);
}
