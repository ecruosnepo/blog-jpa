package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.controller.BlogController;
import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.dto.UpdateArticleRequest;
import com.estsoft.blogjpa.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public void saveAll(List<AddArticleRequest> request) {
        blogRepository.saveAllAndFlush(request.stream().map(AddArticleRequest::toEntity).toList());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        //예외를 컨트롤러로 던져줘야 한다. 이후 컨트롤러에서 500에러를 404에러로 변환해서 사용자에게 에러 알려주는 방식을 사용한다.
//        return  blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException(id + "을(를) 찾을 수 없습니다."));
        return blogRepository.findById(id).orElse(new Article());
    }

    public void deleteById(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        //begin transaction
        Article article = findById(id);
        article.update(request.getTitle(), request.getContent());

        //commit / rollback
        return article;
    }

    @Transactional
    public Article updateTitle(Long id, UpdateArticleRequest request) {
        Article article = findById(id);
        blogRepository.updateTitle(id, request.getTitle());

        return article;
    }
}
