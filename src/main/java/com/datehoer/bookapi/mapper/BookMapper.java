package com.datehoer.bookapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datehoer.bookapi.model.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
