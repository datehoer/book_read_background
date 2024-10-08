package com.datehoer.bookapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datehoer.bookapi.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Select("SELECT book_press, COUNT(*) as count FROM `book_book` GROUP BY book_press")
    List<Map<String, Object>> getBookPress();
}
