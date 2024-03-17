package com.estsoft.blogjpa.dto;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String body;
    private LocalDateTime createdAt;
//    private Article article;

    public CommentResponse(Comment comment){
        this.id = comment.getId();
//        this.article = comment.getArticle();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();
    }
}
