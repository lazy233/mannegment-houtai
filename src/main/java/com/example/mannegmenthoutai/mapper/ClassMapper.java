package com.example.mannegmenthoutai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mannegmenthoutai.model.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ClassMapper extends BaseMapper<Clazz> {
    @Update("UPDATE classes SET student_count = #{count} WHERE class_id = #{classId}")
    int updateStudentCount(@Param("classId") Integer classId, @Param("count") Integer count);
} 