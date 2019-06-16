package com.example.demo.service.category_service;

import com.example.demo.model.Catagory;

import java.util.List;

public interface CategoryService {
    void add(Catagory catagory);

    void update(Catagory catagory);

    void delete(int id);

    Catagory getCatagory(int id);

    List<Catagory> findAll();
}
