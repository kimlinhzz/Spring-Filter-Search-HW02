package com.example.demo.repository.articleRepository;

import com.example.demo.model.Article;
import com.example.demo.repository.provider.ArtcleProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ArticleRepository {

    @Insert({"INSERT INTO tb_article(aurthor,title,description,thumnail,category_id) VALUES(#{aurthor},#{title},#{description},#{thumnail},#{catagory.idCatagory})"})
    void add(Article article);

    @Update("UPDATE tb_article SET title = #{title} ,aurthor =#{aurthor},description =#{description},thumnail =#{thumnail},category_id =#{catagory.idCatagory} WHERE id = #{id}")
    void update(Article article);

    @Delete("DELETE FROM tb_article WHERE id = #{id}")
    void delete (int id);


    @Select("SELECT  a.id , a.title , a.aurthor , a.description ,a.thumnail , a.category_id , c.name_category" +
            " FROM tb_article a INNER JOIN tb_category c ON a.category_id = c.id_category")
    @Results({
            @Result(property = "catagory.idCatagory" , column = "id_catagory"),
            @Result(property =  "catagory.nameCatagory", column = "name_category")
    })
        List<Article> findAll();
    @SelectProvider(type = ArtcleProvider.class,method = "getData")
    @Results({
            @Result(property = "catagory.idCatagory" , column = "id_catagory"),
            @Result(property =  "catagory.nameCatagory", column = "name_category")
    })
    List<Article> findFiller(@Param("id") int id ,@Param("searchText") String searchText);
    @Select("SELECT a.id , a.title , a.aurthor , a.description ,a.thumnail , a.category_id , c.name_category" +
            " FROM tb_article a INNER JOIN tb_category c ON a.category_id = c.id_category WHERE a.id =#{id}")
    @Results({
            @Result(property="catagory.idCatagory" , column = "id_category"),
            @Result(property =  "catagory.nameCatagory", column = "name_category")
    })
    Article getArticle(int id);
}
