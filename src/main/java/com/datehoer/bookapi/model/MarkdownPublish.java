package com.datehoer.bookapi.model;

import lombok.Data;

@Data
public class MarkdownPublish {
    private String title;
    private String tags;
    private String markdown;
}
