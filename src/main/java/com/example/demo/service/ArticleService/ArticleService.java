package com.example.demo.service.ArticleService;

import com.example.demo.model.Article;


import java.util.List;

public interface ArticleService  {
    void add(Article article);

    void update(Article article);

    void delete(int id);

    List<Article> findAll();

    List<Article> showByPagination(int page, int limit,int idSearch,String searchText);

    Article getArticle(int id);
}
