package com.example.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Book;
import com.example.domain.BookMapper;

@SpringBootTest
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    //单个查询
    @Test
    void testGetById() {
        System.out.println(bookMapper.selectById(3));
    }

    //添加
    @Test
    void testInsert() {
        Book book = new Book();
        //使用数据库的自增策略
        //book.setId(123);
        book.setType("计算机技术");
        book.setName("计算机网络");
        book.setDescription("计算机网络相关");
        bookMapper.insert(book);
    }

    //删除
    @Test
    void testDelete() {
        bookMapper.deleteById("123");
        System.out.println();
    }

    //更新
    @Test
    void testUpdate() {
        Book book = new Book();
        book.setId(124);
        book.setType("计算机技术vyjfvyt");
        book.setName("计算机网络");
        book.setDescription("计算机网络相关");
        bookMapper.updateById(book);
    }

    //查询所有
    @Test
    void testGetAll() {
        bookMapper.selectList(null).stream().forEach(book -> System.out.println(book));
    }

    //分页查询
    @Test
    void testPage() {

        Page<Book> page = bookMapper.selectPage(new Page<Book>(1, 3), null);
        page.getRecords().stream().forEach(book -> System.out.println(book));
    }

    //条件查询1
    @Test
    void testGetBy() {
        Book book = bookMapper.selectOne(new QueryWrapper<Book>().eq("id", 1));
        System.out.println(book);
    }

    //条件查询2---优化
    @Test
    void testGetBy2() {
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        //需要注意的一个地方：eq中有个条件condition,判断后面的数据是否为空，为空就不拼接到sql语句中。
        //wrapper.eq(Book::getId, null);
        Integer id = null;
        wrapper.eq(id != null, Book::getId, id);    //此时不会拼接
        bookMapper.selectList(wrapper).stream().forEach(book -> System.out.println(book));
    }

}
