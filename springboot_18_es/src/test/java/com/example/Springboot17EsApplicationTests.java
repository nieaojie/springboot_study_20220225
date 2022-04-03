package com.example;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHost;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RequestOptions.Builder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSON;
import com.example.domain.Book;
import com.example.mapper.BookMapper;

@SpringBootTest
class Springboot17EsApplicationTests {

    @Autowired
    private BookMapper bookMapper;

    private RestHighLevelClient client;

    //每个方法前都会执行
    @BeforeEach
    void setUp() {
        HttpHost host = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);
    }

    //每个方法后都会执行
    @AfterEach
    void tearDown() throws IOException {
        client.close();
    }

    //测试环境
    @Test
    void testEnvironment() {
        Book book = bookMapper.selectById(1);
        System.out.println(book);
    }

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("books2");
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 通过ik分词器创建索引
     * @throws IOException
     */
    @Test
    void createIndexByIK() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("books2");
        String source = "{\n"
                        + "    \"mappings\":{\n"
                        + "        \"properties\":{\n"
                        + "            \"id\":{\n"
                        + "                \"type\":\"keyword\"\n"
                        + "            },\n"
                        + "            \"name\":{\n"
                        + "                \"type\":\"text\",\n"
                        + "                \"analyzer\":\"ik_max_word\",\n"
                        + "                \"copy_to\":\"all\"\n"
                        + "            },\n"
                        + "            \"type\":{\n"
                        + "                \"type\":\"keyword\"\n"
                        + "            },\n"
                        + "            \"description\":{\n"
                        + "                \"type\":\"text\",\n"
                        + "                \"analyzer\":\"ik_max_word\",\n"
                        + "                \"copy_to\":\"all\"\n"
                        + "            },\n"
                        + "            \"all\":{\n"
                        + "                \"type\":\"text\",\n"
                        + "                \"analyzer\":\"ik_max_word\"\n"
                        + "            }\n"
                        + "        }\n"
                        + "    }\n"
                        + "}";
        //设置请求的参数
        request.source(source, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 创建文档
     */
    @Test
    void testCreateDoc() throws IOException {
        Book book = bookMapper.selectById(1);
        IndexRequest request = new IndexRequest("books2");
        request.id(book.getId().toString());
        String json = JSON.toJSONString(book);
        request.source(json, XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    /**
     * 创建所有文档
     */
    @Test
    void testCreateDocAll() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();

        List<Book> bookList = bookMapper.selectList(null);
        bookList.forEach(book -> {
            IndexRequest request = new IndexRequest("books2");
            request.id(book.getId().toString());
            String json = JSON.toJSONString(book);
            request.source(json, XContentType.JSON);
            bulkRequest.add(request);
        });
        client.bulk(bulkRequest, RequestOptions.DEFAULT);   //bulk大批的；主体，大部分
    }

    /**
     * 根据id查询文档
     */
    @Test
    void testGetDoc() throws IOException {
        GetRequest getRequest = new GetRequest("books2", "1");
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        String json = response.getSourceAsString();
        System.out.println(json);
    }

    /**
     * 根据条件查询文档
     */
    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("books2");
        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.termQuery("name", "good"));
        builder.query(QueryBuilders.termQuery("all", "good"));
        searchRequest.source(builder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits searchHits = response.getHits();
        searchHits.forEach(searchHit -> {
            String json = searchHit.getSourceAsString();
            Book book = JSON.parseObject(json, Book.class);
            System.out.println(book);
        });
    }

}
