package com.datehoer.bookapi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("cnblogs")
public class CnBlogs {
    @TableId
    private Long id;
    @TableField("title")
    private String title;
    @TableField("url")
    private String url;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("insert_time")
    private LocalDateTime insertTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_time")
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("publish_time")
    private LocalDateTime publishTime;
    @TableField("is_published")
    private Integer isPublished;
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("md5_value")
    private String md5Value;
}
