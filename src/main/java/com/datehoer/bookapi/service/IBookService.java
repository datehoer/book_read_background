package com.datehoer.bookapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.datehoer.bookapi.model.Book;
import com.datehoer.bookapi.model.BookStatistics;

import java.util.List;

public interface IBookService extends IService<Book> {
    IPage<Book> selectPage(int pageNum, int pageSize, QueryWrapper<Book> queryWrapper);

    BookStatistics getBookStatistics();

    List<Book> getBookAddTimeList();
}
