package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.repository.BlogRepository;
import com.estsoft.blogjpa.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void mockMvcSetUP(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blogRepository.deleteAllInBatch();
    }

    @AfterEach
    public void cleanUp(){
        blogRepository.deleteAll();
    }

    @Test
    public void showArticle() throws Exception {
        //given - blogRepository.save
        List<Article> articleList = new ArrayList<>();
        Article article1 = new Article("title1", "content1");
        Article article2 = new Article("title2", "content2");

        articleList.add(article1);
        articleList.add(article2);
        blogRepository.saveAll(articleList);

        //when
        ResultActions response = mockMvc.perform(get("/api/articles"));
        //then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(articleList.get(0).getTitle()))
                .andExpect(jsonPath("$[0].content").value(articleList.get(0).getContent()))
                .andExpect(jsonPath("$[1].title").value(articleList.get(1).getTitle()))
                .andExpect(jsonPath("$[1].content").value(articleList.get(1).getContent()));
    }

    @Test
    public void deleteOneArticle() throws Exception{
        //given
        Article article = blogRepository.save(new Article("title", "content"));
//        Article articleSave = blogRepository.save(article);
//        Long id = articleSave.getId();
        Long id = article.getId();

        //when
        ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}",id));

        //then
        resultActions.andExpect(status().isOk());
        Optional<Article> deleteArticle = blogRepository.findById(id);
        assertFalse(deleteArticle.isPresent());
    }
}