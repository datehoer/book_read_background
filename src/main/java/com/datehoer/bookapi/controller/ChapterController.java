package com.datehoer.bookapi.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.datehoer.bookapi.common.PublicResponse;
import com.datehoer.bookapi.common.QueryWrapperUtil;
import com.datehoer.bookapi.common.TableSupport;
import com.datehoer.bookapi.model.Chapter;
import com.datehoer.bookapi.model.ChapterVo;
import com.datehoer.bookapi.model.PageModel;
import com.datehoer.bookapi.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private IChapterService chapterService;
    @SaCheckLogin
    @GetMapping("/list")
    public IPage<Chapter> list(Chapter chapter){
        PageModel pageModel = TableSupport.buildPageRequest();
        QueryWrapper<Chapter> chapterQueryWrapper = QueryWrapperUtil.buildQueryWrapper(chapter);
        chapterQueryWrapper.orderBy(true, pageModel.getIsAsc().equals("asc"), pageModel.getOrderByColumn());
        return chapterService.selectPage(pageModel.getPageNum(), pageModel.getPageSize(),chapterQueryWrapper);
    }
    @SaCheckLogin
    @GetMapping("/getChapterNeighbors")
    public PublicResponse<List<ChapterVo>> getChapterNeighbors(Chapter chapter){
        return PublicResponse.success(chapterService.getChapterNeighbors(chapter));
    }
}
