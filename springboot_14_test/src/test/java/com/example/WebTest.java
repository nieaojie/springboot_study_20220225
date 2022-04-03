package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

//使用web环境启动测试类
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT) //默认端口
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)   //随机端口
//开启虚拟mvc调用
@AutoConfigureMockMvc
public class WebTest {

    @Test
    void test() {

    }

    //    也可以使用这种方式声明
    //    @Autowired
    //    private MockMvc mvc;
    @Test
    void testWeb(@Autowired MockMvc mvc) throws Exception {
        //创建了虚拟的请求，当前访问的是/books
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        //执行了对应的请求
        mvc.perform(builder);
    }

    @Test
    void testStatus(@Autowired MockMvc mvc) throws Exception {
        //true
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        //false
        //MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions action = mvc.perform(builder);
        //设定预期值与真实值进行比较，成功测试通过，失败测试失败
        //定义本次调用的预期值
        StatusResultMatchers status = MockMvcResultMatchers.status();
        //预计本次调用是成功的:状态200
        ResultMatcher ok = status.isOk();
        //添加预期值与本次调用过程进行匹配
        action.andExpect(ok);
    }

    @Test
    void testBody(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions actions = mvc.perform(builder);
        ContentResultMatchers content = MockMvcResultMatchers.content();
        //right
        ResultMatcher resultMatcher = content.string("springboot");
        //false
        //ResultMatcher resultMatcher = content.string("springboot2");
        actions.andExpect(resultMatcher);
    }

    @Test
    void testJson(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/getBook");
        ResultActions actions = mvc.perform(builder);
        ContentResultMatchers content = MockMvcResultMatchers.content();
        //right
        ResultMatcher resultMatcher = content.json(
                "{\"id\":1,\"name\":\"test\",\"type\":\"test\",\"description\":\"test\"}");
        //false
        //ResultMatcher resultMatcher = content.json(
        //        "{\"id\":1,\"name\":\"test222\",\"type\":\"test\",\"description\":\"test\"}");
        actions.andExpect(resultMatcher);
    }

    @Test
    void testContentType(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/getBook");
        ResultActions actions = mvc.perform(builder);
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        //right
        ResultMatcher resultMatcher = header.string("Content-Type", "application/json");
        //false
        //ResultMatcher resultMatcher = header.string("Content-Type", "application/text");
        actions.andExpect(resultMatcher);
    }

    //实际开发中的真实写法
    @Test
    void testGetById(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books/getBook");
        ResultActions actions = mvc.perform(builder);

        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher ok = status.isOk();
        actions.andExpect(ok);

        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher resultMatcher = header.string("Content-Type", "application/json");
        actions.andExpect(resultMatcher);

        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher contentMatcher = content.json(
                "{\"id\":1,\"name\":\"test\",\"type\":\"test\",\"description\":\"test\"}");
        actions.andExpect(contentMatcher);
    }

}
