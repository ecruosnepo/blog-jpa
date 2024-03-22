package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.external.ExampleAPIClient;
import com.estsoft.blogjpa.external.ExternalAPIParser;
import com.estsoft.blogjpa.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TestController {
    private final ExternalAPIParser externalAPIParser;
    private final BlogService blogService;

    public TestController(ExternalAPIParser externalAPIParser, BlogService blogService) {
        this.externalAPIParser = externalAPIParser;
        this.blogService = blogService;
    }

    @GetMapping("/test")
    public void parse(){
        String API_URL = "https://jsonplaceholder.typicode.com/posts";
        blogService.saveAll(externalAPIParser.parser(API_URL));
    }

    @GetMapping("/view")
    public void viewTest(){
        System.out.println("view html");
    }
}
