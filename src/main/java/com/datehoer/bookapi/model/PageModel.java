package com.datehoer.bookapi.model;

import lombok.Data;

@Data
public class PageModel {
    private Integer pageNum;
    private Integer pageSize;
    private String orderByColumn;
    private String isAsc = "asc";
}
