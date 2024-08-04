package com.datehoer.bookapi.controller;

import com.datehoer.bookapi.common.PublicResponse;
import com.datehoer.bookapi.model.Content;
import com.datehoer.bookapi.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class contentController {
    @Autowired
    private IContentService contentService;
    @GetMapping("/getContent")
    public PublicResponse<Content> getContent(Content content) {
        return PublicResponse.success(contentService.getContentByChapterId(content.getChapterId()));
    }
}
