package com.example.demo.service;

import com.example.demo.model.Catagory;
import com.example.demo.repository.categoryRepository.CategoryRepo;
import com.example.demo.service.category_service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CategoryServiceImp implements CategoryService {
    static private int page;
    static public int lastPage;
    static public int currentPage = 1;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public void add(Catagory catagory) {
    categoryRepo.add(catagory);
    }

    @Override
    public void update(Catagory catagory) {
    categoryRepo.update(catagory);
    }

    @Override
    public void delete(int id) {
        categoryRepo.delete(id);
    }

    @Override
    public Catagory getCatagory(int id) {
        return categoryRepo.getCatagory(id);
    }

    @Override
    public List<Catagory> findAll() {

        return categoryRepo.findAll();
    }


    @Override
    public List<Catagory> showByPagination(int page, int limit) {
        List temp;

        CategoryServiceImp.currentPage = page;
        CategoryServiceImp.page = page;
        CategoryServiceImp.lastPage = (int) (Math.ceil(categoryRepo.findAll().size() / (double) limit));
        int startPage = (page - 1) * limit;
        int endPage = startPage + limit;

        if (endPage >= categoryRepo.findAll().size()) {
            endPage = categoryRepo.findAll().size();
        }
        temp = categoryRepo.findAll().subList(startPage, endPage);

        return temp;
    }
}
