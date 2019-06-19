package com.example.demo.repository.categoryRepository;

import com.example.demo.model.Catagory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo {
    @Insert("INSERT INTO tb_category(name_category) VALUES(#{nameCatagory})")
    void add(Catagory catagory);
    @Update("UPDATE tb_category SET name_category = #{nameCatagory} WHERE id_category = #{idCatagory}")
    void update(Catagory catagory);
    @Delete("DELETE FROM tb_category WHERE id_category = #{id}")
    void delete(int id);
    @Select("SELECT * FROM tb_category WHERE id_category = #{id}")
    @Results({
            @Result(property = "idCatagory" , column = "id_category")
            ,@Result(property = "nameCatagory",column = "name_category")
    })
    Catagory getCatagory(int id);

    @Select("SELECT DISTINCT name_category , id_category FROM tb_category  ORDER BY id_category ASC")
    @Results({
            @Result(property = "idCatagory" , column = "id_category")
            ,@Result(property = "nameCatagory",column = "name_category")
    })
    List<Catagory> findAll();
}
