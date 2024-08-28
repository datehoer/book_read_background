package com.datehoer.bookapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.datehoer.bookapi.common.PublicResponse;
import com.datehoer.bookapi.mapper.CnBlogsContentMapper;
import com.datehoer.bookapi.mapper.CnBlogsMapper;
import com.datehoer.bookapi.model.CnBlog;
import com.datehoer.bookapi.model.CnBlogs;
import com.datehoer.bookapi.model.CnBlogsContent;
import com.datehoer.bookapi.service.CnBlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CnBlogsServiceImpl extends ServiceImpl<CnBlogsMapper, CnBlogs> implements CnBlogsService
{
    @Override
    public IPage<CnBlogs> selectPage(Integer pageNum, Integer pageSize, QueryWrapper<CnBlogs> queryWrapper) {
        Page<CnBlogs> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public CnBlogs selectById(String cnblogId) {
        return baseMapper.selectById(cnblogId);
    }
}
