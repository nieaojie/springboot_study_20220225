package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.domain.Book;

@SpringBootTest
class Springboot1503SqlH2ApplicationTests {

    @Test
    void contextLoads(@Autowired JdbcTemplate jdbcTemplate) {
        String sql = "select  * from tbl_book";
        //queryForObject适用于单行单列，不然会报错
        //Book book = jdbcTemplate.queryForObject(sql, Book.class);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println(list);

        //直接取list
        RowMapper<Book> rm = new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setType(rs.getString("type"));
                book.setName(rs.getString("name"));
                book.setDescription(rs.getString("description"));
                return book;
            }
        };

        List<Book> books = jdbcTemplate.query(sql, rm);
        System.out.println(books);
        //写成lambda表达式的形式
        List<Book> bookList = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setType(rs.getString("type"));
            book.setName(rs.getString("name"));
            book.setDescription(rs.getString("description"));
            return book;
        });
        System.out.println("Lambda表达式的写法：" + bookList);
    }

    @Test
    void testSave(@Autowired JdbcTemplate jdbcTemplate) {
        String sql =
                "insert into tbl_book values(3,'jdbcTemplate insert1','jdbcTemplate insert1','jdbcTemplate insert1')";
        int update = jdbcTemplate.update(sql);
        System.out.println(update);
    }

}
