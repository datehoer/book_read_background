package com.datehoer.bookapi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("book_content")
public class Content {
    @TableId
    private Long id;
    @TableField("book_id")
    private String bookId;
    @TableField("chapter_id")
    private String chapterId;
    @TableField("chapter_content")
    private String chapterContent;
    @TableField("content_order")
    private String contentOrder;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("insert_time")
    private LocalDateTime insertTime;
    @TableField("insert_by")
    private String insertBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_time")
    private LocalDateTime updateTime;
    @TableField("update_by")
    private String updateBy;
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
