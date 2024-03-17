package com.estsoft.blogjpa.dto;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentRequest {
    private Long articleId;
    private String body;

    public CommentRequest toRequest(Comment comment){
        return new CommentRequest(comment.getId(), comment.getBody());
    }

    public Comment toEntity(Article article){
        return Comment.builder().article(article).body(body).build();
    }
}
