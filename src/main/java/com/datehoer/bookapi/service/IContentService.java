package com.datehoer.bookapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.datehoer.bookapi.model.Content;

public interface IContentService extends IService<Content> {
    Content getContentByChapterId(String chapterId);
}
