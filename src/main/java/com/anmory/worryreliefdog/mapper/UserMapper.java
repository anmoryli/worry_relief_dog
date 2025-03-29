package com.anmory.worryreliefdog.mapper;

import com.anmory.worryreliefdog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午1:08
 */

@Mapper
public interface UserMapper {
    @Select("select * from users where username = #{name}")
    User selectUserByName(String name);

    @Insert("insert into users (username,password,email) values (#{name},#{password},#{email})")
    int addUser(String name,String password, String email);

    @Select("select * from users where username = #{name} and email = #{email}")
    User selectUserByNameEmail(String name, String email);

    @Update("update users set password = #{password},username = #{name},email = #{email} where username = #{name}")
    int changePassword(String name, String password,String email);
}
