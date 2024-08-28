package com.datehoer.bookapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.datehoer.bookapi.common.PublicResponse;
import com.datehoer.bookapi.model.CnBlog;
import com.datehoer.bookapi.model.CnBlogs;

public interface CnBlogsService {
    IPage<CnBlogs> selectPage(Integer pageNum, Integer pageSize, QueryWrapper<CnBlogs> queryWrapper);

    CnBlogs selectById(String cnblogId);
}
