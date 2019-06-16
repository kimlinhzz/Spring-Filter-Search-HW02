package com.example.demo.controller;

import com.example.demo.model.Catagory;
import com.example.demo.service.category_service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public String getCate(ModelMap modelMap) {
        System.out.println(categoryService.findAll());
        modelMap.addAttribute("categories", categoryService.findAll());
        return "category-form";
    }

    @GetMapping("/category/add")
    public String getFormAdd(ModelMap modelMap) {
        Catagory catagory = new Catagory();
        catagory.setIdCatagory(categoryService.findAll().size() + 1);
        modelMap.addAttribute("category", catagory);
        return "category-add-form";
    }

    @PostMapping("/category/add")
    public String addData(@Valid @ModelAttribute Catagory catagory, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Something error");
            return "category-add-form";
        }
        System.out.println("Good to go..");
        categoryService.add(catagory);
        return "redirect:/category";
    }

    @GetMapping("/category/update")
    public String getFormUpdate(@RequestParam("categoryID") int id, ModelMap modelMap) {
        modelMap.addAttribute("category", categoryService.getCatagory(id));
        return "category-update-form";
    }

    @PostMapping("/category/update")
    public String updateData(@Valid @ModelAttribute Catagory catagory, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Something error");
            return "category-update-form";
        }
        System.out.println("good to good");
        categoryService.update(catagory);

        return "redirect:/category";
    }


    @GetMapping("/category/delete")
    public String deleteData(@RequestParam("id") int id) {
        categoryService.delete(id);
        return "redirect:/category";
    }
}
