package com.example.mannegmenthoutai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mannegmenthoutai.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import com.example.mannegmenthoutai.dto.UserQueryRequest;
import com.example.mannegmenthoutai.dto.UserPageResponse;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    
    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(@Param("email") String email);
    
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(@Param("username") String username);
    
    @Select("SELECT COUNT(*) FROM users WHERE username = #{username}")
    int countByUsername(@Param("username") String username);
    
    @Select("SELECT COUNT(*) FROM users WHERE email = #{email}")
    int countByEmail(@Param("email") String email);

    @Insert("INSERT INTO users(username, password, email, class_id) VALUES(#{username}, #{password}, #{email}, #{classId})")
    int insertUser(User user);

    @Select("SELECT class_id, COUNT(*) as count FROM users WHERE class_id IS NOT NULL GROUP BY class_id")
    List<Map<String, Object>> countUsersByClassId();

} 