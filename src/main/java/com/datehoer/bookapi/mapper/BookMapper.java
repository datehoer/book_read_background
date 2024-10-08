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
    @Select("SELECT\n" +
            "  tag,\n" +
            "  COUNT(*) AS count\n" +
            "FROM (\n" +
            "  SELECT TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(book_tags, ',', numbers.n), ',', -1)) AS tag\n" +
            "  FROM book_book\n" +
            "  JOIN (\n" +
            "    SELECT 1 n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL \n" +
            "    SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL \n" +
            "    SELECT 9 UNION ALL SELECT 10\n" +
            "  ) numbers ON CHAR_LENGTH(book_tags) - CHAR_LENGTH(REPLACE(book_tags, ',', '')) >= numbers.n - 1\n" +
            ") tags\n" +
            "GROUP BY tag\n" +
            "ORDER BY count DESC\n" +
            "LIMIT 100;\n")
    List<Map<String, Object>> getBookTags();

    @Select("SELECT YEAR(update_time) AS year, COUNT(*) AS count\n" +
            "FROM book_book\n" +
            "GROUP BY YEAR(update_time)\n" +
            "ORDER BY year;")
    List<Map<String, Object>> getBookPublishYear();
}
