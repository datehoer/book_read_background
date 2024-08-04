package com.datehoer.bookapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.datehoer.bookapi.model.Book;

public interface IBookService extends IService<Book> {
    IPage<Book> selectPage(int pageNum, int pageSize, QueryWrapper<Book> queryWrapper);
}
