package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.dto.CommentRequest;
import com.estsoft.blogjpa.dto.CommentResponse;
import com.estsoft.blogjpa.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

//    @GetMapping("/comment/{articleId}")
//    public ResponseEntity<List<CommentResponse>> showAllComment(@PathVariable Long articleId){
//        List<CommentResponse> commentList = commentService.findAllByArticle(articleId);
//
//        return ResponseEntity.ok(commentList);
//    }

    @GetMapping("/comment/{articleId}")
    public ResponseEntity<ArticleResponse> showAllComment2(@PathVariable Long articleId){
        ArticleResponse article = commentService.findAllByArticle2(articleId);

        return ResponseEntity.ok(article);
    }

    @GetMapping("/comment/{articleId}/{commentId}")
    public ResponseEntity<CommentResponse> showOneComment(@PathVariable Long articleId,
                                                          @PathVariable Long commentId){

        CommentResponse commentResponse = commentService.findOneByArticleAndCommentId(articleId, commentId);
        return ResponseEntity.ok(commentResponse);
    }

    @PostMapping("/comment/{articleId}")
    public ResponseEntity<CommentResponse> saveComment(@PathVariable Long articleId,
                                                       @RequestBody CommentRequest request){
        CommentResponse commentResponse = commentService.save(articleId, request);
        return ResponseEntity.ok(commentResponse);
    }
}
