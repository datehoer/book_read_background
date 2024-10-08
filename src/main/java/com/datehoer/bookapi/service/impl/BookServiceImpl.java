package com.datehoer.bookapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.datehoer.bookapi.mapper.BookMapper;
import com.datehoer.bookapi.model.Book;
import com.datehoer.bookapi.model.BookStatistics;
import com.datehoer.bookapi.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public IPage<Book> selectPage(int pageNum, int pageSize, QueryWrapper<Book> queryWrapper) {
        Page<Book> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public BookStatistics getBookStatistics() {
        BookStatistics statistics = new BookStatistics();

        // 查询书籍总数
        int totalBooks = baseMapper.selectCount(new QueryWrapper<Book>().eq("is_deleted", 0));
        statistics.setTotalBooks(totalBooks);

        // 查询喜欢的书籍总数
        int likedBooks = baseMapper.selectCount(new QueryWrapper<Book>().eq("is_like", 1).eq("is_deleted", 0));
        statistics.setLikedBooks(likedBooks);

        // 查询平台总数（假设平台是 book_press，统计不同平台的数量）
        int totalPlatforms = baseMapper.selectCount(new QueryWrapper<Book>().select("DISTINCT book_press").eq("is_deleted", 0));
        statistics.setTotalPlatforms(totalPlatforms);

        // 查询完结的书籍总数（假设 book_status 表示完结状态，完结为 'completed'）
        int completedBooks = baseMapper.selectCount(new QueryWrapper<Book>().eq("book_status", "完结").eq("is_deleted", 0));
        statistics.setCompletedBooks(completedBooks);

        return statistics;
    }

    @Override
    public List<Book> getBookAddTimeList() {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_time");
        queryWrapper.select("book_name", "book_press", "update_time");
        queryWrapper.last("LIMIT 5");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Book> getRandomBook(){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "book_name", "book_description", "book_cover");
        queryWrapper.last("ORDER BY RAND() LIMIT 5");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> getBookPress() {
        return bookMapper.getBookPress();
    }
}
