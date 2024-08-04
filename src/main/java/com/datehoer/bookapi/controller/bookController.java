package com.datehoer.bookapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.datehoer.bookapi.common.PublicResponse;
import com.datehoer.bookapi.common.QueryWrapperUtil;
import com.datehoer.bookapi.common.TableSupport;
import com.datehoer.bookapi.model.Book;
import com.datehoer.bookapi.model.PageModel;
import com.datehoer.bookapi.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/book")
@RestController
public class bookController {
    @Autowired
    private IBookService bookService;

    @GetMapping("/list")
    public IPage<Book> list(Book book) {
        PageModel pageModel = TableSupport.buildPageRequest();
        QueryWrapper<Book> queryWrapper = QueryWrapperUtil.buildQueryWrapper(book);
        queryWrapper.orderBy(true, pageModel.getIsAsc().equals("asc"), pageModel.getOrderByColumn());
        return bookService.selectPage(pageModel.getPageNum(), pageModel.getPageSize(), queryWrapper);
    }
    @GetMapping("/{bookId}")
    public PublicResponse<Book> getBookById(@PathVariable String bookId) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id", bookId);
        return PublicResponse.success(bookService.getOne(queryWrapper));
    }
}
