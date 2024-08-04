package com.datehoer.bookapi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("book_chapter")
public class ChapterVo {
    @TableField("chapter_id")
    private String chapterId;
    @TableField("chapter_order")
    private String chapterOrder;
    @TableField("chapter_name")
    private String chapterName;
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
