package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.dto.UpdateArticleRequest;
import com.estsoft.blogjpa.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BlogPageController {
    private final BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    public String articleList(Model model){
        List<ArticleResponse> responseList = blogService.findAll().stream().map(Article::toResponse).toList();
        model.addAttribute("articles",responseList);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String articleContent(@PathVariable Long id, Model model){
//        ArticleResponse response = blogService.findById(id).toResponse();
        ArticleResponse response = new ArticleResponse(blogService.findById(id));        model.addAttribute("article",response);
        return "article";
    }

    @DeleteMapping("/articles/{id}")
    public void articleDelete(@PathVariable Long id){
        blogService.deleteById(id);
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
//        model.addAttribute("article",blogService.findById(id));
//        Optional<ArticleResponse> articleResponse = Optional.of(new ArticleResponse(blogService.findById(id)));
//        model.addAttribute("article",articleResponse.orElse(new ArticleResponse()));
        if(id == null){
            model.addAttribute("article", new ArticleResponse());
        }else{
            model.addAttribute("article", new ArticleResponse(blogService.findById(id)));
        }

        return "newArticle";
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> articleModify(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        return ResponseEntity.ok(new ArticleResponse(blogService.update(id, request)));
    }

    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> articleWrite(@RequestBody AddArticleRequest request){
        return ResponseEntity.ok(new ArticleResponse(blogService.save(request)));
    }
}
