package com.datehoer.bookapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.datehoer.bookapi.mapper.ChapterMapper;
import com.datehoer.bookapi.model.Chapter;
import com.datehoer.bookapi.model.ChapterVo;
import com.datehoer.bookapi.service.IChapterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements IChapterService {
    @Override
    public IPage<Chapter> selectPage(int pageNum, int pageSize, QueryWrapper<Chapter> queryWrapper) {
        Page<Chapter> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<ChapterVo> getChapterNeighbors(Chapter chapter) {

        return baseMapper.getChapterNeighbors(chapter.getChapterId());
    }
}
