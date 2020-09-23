package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.NoteModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoteMapper {


    @Insert("INSERT into NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyColumn = "noteId")
    public Integer createNote(NoteModel noteModel);

    @Select("SELECT * FROM NOTES WHERE ( userid = #{userId} ) ")
    public List<NoteModel> getNotesByUserID(Integer userId);
}
