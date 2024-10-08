package com.datehoer.bookapi.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.datehoer.bookapi.common.PublicResponse;
import com.datehoer.bookapi.common.QueryWrapperUtil;
import com.datehoer.bookapi.common.TableSupport;
import com.datehoer.bookapi.model.Book;
import com.datehoer.bookapi.model.BookStatistics;
import com.datehoer.bookapi.model.PageModel;
import com.datehoer.bookapi.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    private IBookService bookService;
    @SaCheckLogin
    @GetMapping("/list")
    public IPage<Book> list(Book book) {
        PageModel pageModel = TableSupport.buildPageRequest();
        QueryWrapper<Book> queryWrapper = QueryWrapperUtil.buildQueryWrapper(book);
        queryWrapper.orderBy(true, pageModel.getIsAsc().equals("asc"), pageModel.getOrderByColumn());
        return bookService.selectPage(pageModel.getPageNum(), pageModel.getPageSize(), queryWrapper);
    }
    @SaCheckLogin
    @GetMapping("/{bookId}")
    public PublicResponse<Book> getBookById(@PathVariable String bookId) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id", bookId);
        return PublicResponse.success(bookService.getOne(queryWrapper));
    }
    @SaCheckLogin
    @PutMapping("/unlike/{id}")
    public PublicResponse<Boolean> unlike(@PathVariable Long id) {
        Book book = new Book();
        book.setId(id);
        book.setIsLike(0);
        return PublicResponse.success(bookService.updateById(book));
    }
    @SaCheckLogin
    @PutMapping("/like/{id}")
    public PublicResponse<Boolean> like(@PathVariable Long id) {
        Book book = new Book();
        book.setId(id);
        book.setIsLike(1);
        return PublicResponse.success(bookService.updateById(book));
    }
    @SaCheckLogin
    @GetMapping("/bookStatistics")
    public PublicResponse<BookStatistics> getBookStatistics(){
        return PublicResponse.success(bookService.getBookStatistics());
    }
    @SaCheckLogin
    @GetMapping("/bookAddTimeList")
    public PublicResponse<List<Book>> getBookAddTimeList(){
        return PublicResponse.success(bookService.getBookAddTimeList());
    }
    @SaCheckLogin
    @GetMapping("/randomBook")
    public PublicResponse<List<Book>> getRandomBook(){
        return PublicResponse.success(bookService.getRandomBook());
    }

    @SaCheckLogin
    @GetMapping("/bookPress")
    public PublicResponse<List<Map<String, Object>>> getBookPress(){
        return PublicResponse.success(bookService.getBookPress());
    }
}
