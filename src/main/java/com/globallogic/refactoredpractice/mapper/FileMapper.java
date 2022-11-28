package com.globallogic.refactoredpractice.mapper;

import com.globallogic.refactoredpractice.model.File;
import org.apache.ibatis.annotations.*;

import java.sql.PreparedStatement;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM files WHERE file_name=#{fileName}")
    File findByName(@Param("fileName") String fileName);

    @Select("SELECT * FROM files WHERE id=#{id}")
    File findById(@Param("id") String id);

    @Insert("INSERT INTO files(file_name, file_type, data) values (#{id}, #{fileName}, #{fileType}, #{data}")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before=false, resultType = Integer.class)
    File saveFile(@Param("fileName") String fileName, @Param("fileType") String fileType, @Param("data") byte[] data);
}
