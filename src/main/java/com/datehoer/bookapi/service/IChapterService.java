package com.datehoer.bookapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.datehoer.bookapi.model.Chapter;
import com.datehoer.bookapi.model.ChapterVo;

import java.util.List;

public interface IChapterService extends IService<Chapter> {
    IPage<Chapter> selectPage(int pageNum, int pageSize, QueryWrapper<Chapter> queryWrapper);

    List<ChapterVo> getChapterNeighbors(Chapter chapter);
}
