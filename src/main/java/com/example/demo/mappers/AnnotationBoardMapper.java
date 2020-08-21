package com.example.demo.mappers;

import com.example.demo.entity.Board;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AnnotationBoardMapper {

    @Select("SELECT * FROM BOARD WHERE ID = #{id}")
    Board findById(@Param("id") int id);
}
