package com.example.demo.service;

import com.example.demo.model.Catagory;
import com.example.demo.repository.categoryRepository.CategoryRepo;
import com.example.demo.service.category_service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CategoryServiceImp implements CategoryService {

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
}
