package com.example.controller;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Book;
import com.example.service.IBookService;
import com.example.utils.Result;

@RestController
@RequestMapping("/books")
public class BookController2 {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public Result getAll() {
        return new Result(true, bookService.list());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        return new Result(true, bookService.getById(id));
    }

    @PostMapping
    public Result save(@RequestBody Book book) throws Exception {
        if ("123".equals(book.getName())) {throw new Exception("出错了");}
        boolean save = bookService.save(book);
        //后端统一处理前台显示的msg信息，便于国际化处理
        return new Result(save, null, save ? "添加成功^_^" : "添加失败");
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        return new Result(bookService.updateById(book), null);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return new Result(bookService.removeById(id), null);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    //这里的book接收的是get请求上的路径参数，会自动把相应的属性封装到book中的
    public Result page(@PathVariable Integer currentPage, @PathVariable Integer pageSize, Book book) {
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(book.getType()), Book::getType, book.getType());
        lqw.like(Strings.isNotEmpty(book.getName()), Book::getName, book.getName());
        lqw.like(Strings.isNotEmpty(book.getDescription()), Book::getDescription, book.getDescription());
        Page<Book> page = bookService.page(new Page<>(currentPage, pageSize), lqw);
        //处理最后一页数据被删除时显示的数据为空的情况
        if (currentPage > page.getPages()) {
            page = bookService.page(new Page<>(page.getPages(), pageSize), lqw);
        }
        return new Result(true, page);
    }
}


