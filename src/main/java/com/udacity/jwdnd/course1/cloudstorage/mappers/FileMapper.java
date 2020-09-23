package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE (userid = #{userid} )")
    public List<FileModel> getFilesByUserID(Integer userid);

    @Insert("INSERT into FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(keyColumn = "fileId",useGeneratedKeys = true)
    public Integer addFile(String filename, String contenttype, String filesize, Integer userid, byte[] filedata);

    @Delete("DELETE from FILES where ( fileid = ${fileId} )")
    int deleteFileById(Integer fileId);
}
