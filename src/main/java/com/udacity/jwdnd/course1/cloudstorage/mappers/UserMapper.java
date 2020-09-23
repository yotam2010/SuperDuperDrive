package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE (username = #{username} )")
    public User getUserByName(String username);

    @Insert("INSERT into USERS (username, password, salt, firstname, lastname) VALUES (#{username},#{password},#{salt},#{firstname},#{lastname})")
    @Options(keyColumn = "userid",useGeneratedKeys = true)
    public Integer createUser(String username, String password, String salt, String firstname, String lastname);
}
