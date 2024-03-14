package com.estsoft.blogjpa.repository;

import com.estsoft.blogjpa.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
// 기본 제공 메서드 말고 사용자가 정의해줄 수도 있다. 서비스에서 구현해줘야 함
//    List<Article> findByTitle(String title);
//
//    void deleteByContent(String content);

    //JPQL = Java Persistence Query Language
    @Modifying // 변경에 대한 쿼리라는 것을 알려줌
    @Query("update Article set title = :title where id = :id")
    void updateTitle(Long id, String title);
}
