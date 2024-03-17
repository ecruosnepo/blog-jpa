package com.estsoft.blogjpa.repository;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select c from Article a join Comment c on a.id = c.article.id where a = :article and c.id = :commentId")
    Comment findOneComment(Article article, Long commentId);

//    Comment findByArticleIdAndCommentId(Long articleId, Long commentId);

//    @Query(value = "select c.id, c.body, c.createdAt from Article a join Comment c on a.id = c.article.id where a.id = :articleId")
//    List<Comment> findAllComment(Long articleId);

    List<Comment> findAllByArticle(Article article);
}
