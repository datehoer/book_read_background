package com.datehoer.bookapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.datehoer.bookapi.mapper.BookMapper;
import com.datehoer.bookapi.model.Book;
import com.datehoer.bookapi.service.IBookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {
    @Override
    public IPage<Book> selectPage(int pageNum, int pageSize, QueryWrapper<Book> queryWrapper) {
        Page<Book> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }
}
