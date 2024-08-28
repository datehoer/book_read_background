package com.datehoer.bookapi.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.datehoer.bookapi.common.HttpRequestUtils;
import com.datehoer.bookapi.common.PublicResponse;
import com.datehoer.bookapi.common.QueryWrapperUtil;
import com.datehoer.bookapi.common.TableSupport;
import com.datehoer.bookapi.model.*;
import com.datehoer.bookapi.service.CnBlogsContentService;
import com.datehoer.bookapi.service.CnBlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cnblogs")
public class CnBlogsController {
    @Autowired
    private CnBlogsService cnBlogsService;
    @Autowired
    private CnBlogsContentService cnBlogsContentService;
    @Value("${my-background.api-url}")
    private String backgroundApiUrl;
    @Value("${my-background.directory-path}")
    private String directoryPath;
    @GetMapping("/list")
    public IPage<CnBlogs> list(CnBlogs cnBlogs){
        PageModel pageModel = TableSupport.buildPageRequest();
        QueryWrapper<CnBlogs> queryWrapper = QueryWrapperUtil.buildQueryWrapper(cnBlogs);
        queryWrapper.orderBy(true, pageModel.getIsAsc().equals("asc"), pageModel.getOrderByColumn());
        return cnBlogsService.selectPage(pageModel.getPageNum(), pageModel.getPageSize(), queryWrapper);
    }
    @GetMapping("/{cnblog_id}")
    public PublicResponse<CnBlog> getCnBlogsById(@PathVariable String cnblog_id){
        CnBlogs cnBlogs = cnBlogsService.selectById(cnblog_id);
        QueryWrapper<CnBlogsContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cnblog_id", cnblog_id);
        CnBlogsContent cnBlogsContent = cnBlogsContentService.getOne(queryWrapper);
        CnBlog cnBlog = new CnBlog(cnBlogs, cnBlogsContent);
        return PublicResponse.success(cnBlog);
    }

    @PostMapping("/enhanceMarkdown")
    public PublicResponse<JSONObject> enhanceMarkdown(@RequestBody EnhanceMarkdown enhanceMarkdown){
        String url = backgroundApiUrl + "/process-markdown";
        Map<String, Object> data = new HashMap<>();
        data.put("id", enhanceMarkdown.getId());
        data.put("title", enhanceMarkdown.getTitle());
        data.put("model", enhanceMarkdown.getModel());
        String jsonBody = JSON.toJSONString(data);
        String res = HttpRequestUtils.post(url, jsonBody);
        JSONObject jsonResponse = JSON.parseObject(res);
        return PublicResponse.success(jsonResponse);
    }

    @PostMapping("/publish")
    public PublicResponse<String> publishMarkdown(@RequestBody MarkdownPublish markdownPublish){

        String[] tagsList = markdownPublish.getTags().split(",");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDateTime.format(dateFormatter);
        StringBuilder formattedText = new StringBuilder();
        formattedText.append("---\n");
        formattedText.append("title: ").append(markdownPublish.getTitle()).append("\n");
        formattedText.append("tags:\n");
        for (String tag : tagsList) {
            formattedText.append("  - ").append(StrUtil.trim(tag)).append("\n");
        }
        formattedText.append("date: ").append(formattedDate).append("\n");
        formattedText.append("---\n");
        formattedText.append(markdownPublish.getMarkdown());
        String sanitizedTitle = markdownPublish.getTitle().replaceAll("[\\\\/:*?\"<>|]", "_");
        String fileName = sanitizedTitle + ".md";
        FileUtil.mkdir(directoryPath);
        String filePath = Paths.get(directoryPath, fileName).toString();
        FileUtil.writeString(formattedText.toString(), filePath, "UTF-8");
        return PublicResponse.success("success");
    }
}
