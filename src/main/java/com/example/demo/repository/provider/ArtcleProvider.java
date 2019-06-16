package com.example.demo.repository.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ArtcleProvider {


    public String getData(@Param("id") int id, @Param("searchText") String searchText) {
        return new SQL() {{
            SELECT("*");
            FROM("tb_article a INNER JOIN tb_category c ON a.category_id = c.id_category");

            if (id >0) {
                WHERE("a.category_id = #{id}");
            }
            if (searchText != null) {
                WHERE("a.title ilike '%'||#{searchText}||'%' ");
            }

        }}.toString();
    }
//    public String getDataCount(@Param("id") int id, @Param("searchText") String searchText) {
//        return new SQL() {{
//            SELECT("COUNT(*)");
//            FROM("tb_article a INNER JOIN tb_category c ON a.category_id = c.id_category");
//
//            if (id >0) {
//                WHERE("a.category_id = #{id}");
//            }
//            if (searchText != null) {
//                WHERE("a.title ilike '%'||#{searchText}||'%' ");
//            }
//
//        }}.toString();
//    }
}
