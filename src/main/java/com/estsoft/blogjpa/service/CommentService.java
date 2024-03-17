package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.domain.Comment;
import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.dto.CommentRequest;
import com.estsoft.blogjpa.dto.CommentResponse;
import com.estsoft.blogjpa.repository.BlogRepository;
import com.estsoft.blogjpa.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public List<CommentResponse> findAllByArticle(Long articleId){
        Article article = blogRepository.findById(articleId).get();
        List<Comment> commentList = commentRepository.findAllByArticle(article);
        return commentList.stream().map(CommentResponse::new).toList();
    }

    public ArticleResponse findAllByArticle2(Long articleId){
        ArticleResponse article = new ArticleResponse(blogRepository.findById(articleId).get());
        return article;
    }

    public CommentResponse findOneByArticleAndCommentId(Long articleId, Long commentId){
        Article article = blogRepository.findById(articleId).get();
        Comment comment = commentRepository.findOneComment(article, commentId);
        return new CommentResponse(comment);
    }

    public CommentResponse save(Long articleId, CommentRequest request){
        Article article = blogRepository.findById(articleId).orElse(new Article());
        Comment comment = commentRepository.save(request.toEntity(article));
        return new CommentResponse(comment);
    }
}
