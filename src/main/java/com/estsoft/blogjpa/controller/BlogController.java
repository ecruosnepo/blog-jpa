package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.dto.UpdateArticleRequest;
import com.estsoft.blogjpa.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request){
        Article article =  blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(article.toResponse());
    }

    // = @GetMapping("/api/articles")
    @RequestMapping(value = "/api/articles", method = RequestMethod.GET)
    public ResponseEntity<List<ArticleResponse>> showArticles(){
        List<Article> articleList =  blogService.findAll();
        List<ArticleResponse> articleResponseList = articleList.stream().map(ArticleResponse::new).toList();

        return ResponseEntity.ok(articleResponseList);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> showArticle(@PathVariable Long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok(article.toResponse());
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteOneArticle(@PathVariable Long id){
        blogService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        Article article = blogService.update(id, request);

        return ResponseEntity.ok(article.toResponse());
    }

    @PutMapping("api/articles/title/{id}")
    public ResponseEntity<ArticleResponse> updateTitle(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        Article article = blogService.updateTitle(id, request);

        return ResponseEntity.ok(article.toResponse());
    }
}
