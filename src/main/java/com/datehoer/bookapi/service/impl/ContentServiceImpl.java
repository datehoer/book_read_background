package com.datehoer.bookapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.datehoer.bookapi.mapper.ContentMapper;
import com.datehoer.bookapi.model.Content;
import com.datehoer.bookapi.service.IContentService;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {
    @Override
    public Content getContentByChapterId(String chapterId) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        return baseMapper.selectOne(queryWrapper);
    }
}
