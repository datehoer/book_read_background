package com.datehoer.bookapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datehoer.bookapi.model.Chapter;
import com.datehoer.bookapi.model.ChapterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {
    @Select("select chapter_name, chapter_id, chapter_order from book_chapter where book_id = (select book_id from book_chapter where chapter_id = #{chapterId}) ORDER BY chapter_order")
    List<ChapterVo> getChapterNeighbors(String chapterId);
}
