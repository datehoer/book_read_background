package com.datehoer.bookapi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("book_book")
public class Book{
    @TableId
    private Long id;
    @TableField("book_id")
    private String bookId;
    @TableField("book_name")
    private String bookName;

    @TableField("book_author")
    private String bookAuthor;

    @TableField("book_status")
    private String bookStatus;

    @TableField("book_description")
    private String bookDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("insert_time")
    private LocalDateTime insertTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("insert_by")
    private String insertBy;

    @TableField("update_by")
    private String updateBy;

    @TableField("book_press")
    private String bookPress;

    @TableField("book_tags")
    private String bookTags;

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField("is_like")
    private Integer isLike;

    @TableField("book_cover")
    private String bookCover;
}
