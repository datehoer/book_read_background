package com.datehoer.bookapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datehoer.bookapi.model.Content;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper extends BaseMapper<Content> {
}
