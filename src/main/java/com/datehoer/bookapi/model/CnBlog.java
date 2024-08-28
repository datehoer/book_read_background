package com.datehoer.bookapi.model;

import lombok.Data;

@Data
public class CnBlog {
    private CnBlogs cnBlog;
    private CnBlogsContent cnBlogsContent;

    public CnBlog(CnBlogs cnBlogs, CnBlogsContent content) {
        this.cnBlog = cnBlogs;
        this.cnBlogsContent = content;
    }
}
