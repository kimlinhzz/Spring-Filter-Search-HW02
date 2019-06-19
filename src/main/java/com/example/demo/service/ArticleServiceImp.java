package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.repository.articleRepository.ArticleRepository;
import com.example.demo.service.ArticleService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleServiceImp implements ArticleService {

    static private int page;
    static public int lastPage;
    static public int currentPage = 1;
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public void add(Article article) {
        articleRepository.add(article);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public void update(Article article) {
        articleRepository.update(article);
    }

    @Override
    public Article getArticle(int id) {
        return articleRepository.getArticle(id);
    }

    @Override
    public void delete(int id) {
        articleRepository.delete(id);
    }


    public List<Article> showByPagination(int page, int limit,int idSearch,String searchText) {
        List temp;
//      ActicleRepositoryImp.lastPage=(articleList.size()%limit==0)?articleList.size()/limit:(articleList.size()/limit)+1;
        ArticleServiceImp.currentPage = page;
        ArticleServiceImp.page = page;
        ArticleServiceImp.lastPage = (int) (Math.ceil(articleRepository.findFiller(idSearch,searchText).size() / (double) limit));
        int startPage = (page - 1) * limit;
        int endPage = startPage + limit;

        if (endPage >= articleRepository.findFiller(idSearch,searchText).size()) {
            endPage = articleRepository.findFiller(idSearch,searchText).size();
        }
        temp = articleRepository.findFiller(idSearch,searchText).subList(startPage, endPage);

        return temp;
    }
}

