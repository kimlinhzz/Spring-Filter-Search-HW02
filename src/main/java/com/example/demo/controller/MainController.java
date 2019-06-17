package com.example.demo.controller;
import com.example.demo.model.Article;
import com.example.demo.model.Catagory;
import com.example.demo.service.ArticleService.ArticleService;
import com.example.demo.service.ArticleServiceImp;
import com.example.demo.service.category_service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class MainController {


    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String getIndex(ModelMap modelMap) {
        viewPage(modelMap, 1, 10,0,null);
        return "index";
    }

    @GetMapping("/viewAll")
    public String getIndex(ModelMap modelMap, @RequestParam("page") int page, @RequestParam("limit") int limit,Catagory catagory) {
        System.out.println(articleService.findAll() + "hello");
        viewPage(modelMap, page, limit,0,null);
        return "index";
    }

    @GetMapping("/get")
    public String getBySearch(ModelMap modelMap,@ModelAttribute Article article){
//        System.out.println(categoryService.findAll() + "hello");

        modelMap.addAttribute("tempSearch",article.getTitle());
        if(article.getCatagory().getIdCatagory()>0) {
            modelMap.addAttribute("tempFilter", categoryService.getCatagory(article.getCatagory().getIdCatagory()).getNameCatagory());
        }
        viewPage(modelMap, 1, 10,article.getCatagory().getIdCatagory(),article.getTitle());
//        System.out.println("Search"+categoryService.getCatagory(article.getCatagory().getIdCatagory()).getNameCatagory());
//        System.out.println("bello"+article.getTitle());
        return "index";
    }



    @GetMapping("/formAdd")
    public String getFormAdd(ModelMap modelMap) {
        Article article = new Article();
            article.setId(articleService.findAll().size() + 1);
//            modelMap.addAttribute("idCount" ,)
        modelMap.addAttribute("categories",categoryService.findAll());
        modelMap.addAttribute("article", article);
        return "form-add";
    }

    @PostMapping("/add")
    public String addData(@Valid @ModelAttribute Article article, BindingResult result, ModelMap modelMap, @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            System.out.println("error");
            modelMap.addAttribute("categories", categoryService.findAll());
            return "form-add";
        } else {
            System.out.println(article.getCatagory());
            String fileName = configImage(file);
            System.out.println(fileName + "form method add");
            article.setCatagory(categoryService.getCatagory(article.getCatagory().getIdCatagory()));
            article.setThumnail(fileName);
            articleService.add(article);
            return "redirect:/";
        }
    }

    @GetMapping("/delete")
    public String deleteData(@RequestParam("id") int articleID) {
        System.out.println(articleID);
        articleService.delete(articleID);
        return "redirect:/";
    }

    @GetMapping("/formUpdate")
    public String getFormUpdate(ModelMap modelMap, @RequestParam("id") int articleID) {
        modelMap.addAttribute("article", articleService.getArticle(articleID));
        System.out.println(articleService.getArticle(articleID));
        modelMap.addAttribute("category",categoryService.getCatagory(articleService.getArticle(articleID).getCatagory().getIdCatagory()));
        System.out.println();
        modelMap.addAttribute("categories", categoryService.findAll());
        return "form-update";
    }

    @PostMapping("/update")
    public String updateData(@Valid @ModelAttribute Article article, BindingResult result, @RequestParam("id") int articleId, @RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        if (result.hasErrors()) {
            System.out.println("Has Error");
            return "form-update";
        } else {
            System.out.println("processing");
            System.out.println(file.getOriginalFilename() + "update");
            article.setThumnail(configImage(file));
            articleService.update(article);
            System.out.println("processing");
            return "redirect:/";

        }
    }

    @GetMapping("/view")
    public String viewData(@RequestParam("id") int articleId, ModelMap modelMap) {
        modelMap.addAttribute("article", articleService.getArticle(articleId));
        return "view";
    }


    private String configImage(MultipartFile file) {
        UUID random = UUID.randomUUID();
        String fileName = null;
        if (file.isEmpty()) {
            fileName = "default.jpg";
        } else {
            String f = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            System.out.println(random + f + "From config");
            System.out.println("processing1");
            fileName = random + f;
        }
        System.out.println("processing2");
        try {
            Files.copy(file.getInputStream(), Paths.get("src/main/resources/thumnail/", fileName));
            System.out.println("processing3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    private void viewPage(ModelMap modelMap, int page, int limit,int idSearch,String searchText) {

        if (page == 0) {
            page = ArticleServiceImp.lastPage;
        }

        if (page > ArticleServiceImp.lastPage) {
            page = 1;
        }
        Article article = new Article();
        article.setTitle(searchText);
        modelMap.addAttribute("articles", articleService.findAll());
        //TODO Must Check Category Input Again And Imp Search Dynamic Also Filter;
        modelMap.addAttribute("categories",categoryService.findAll());
        modelMap.addAttribute("article",new Article());

        modelMap.addAttribute("articlePage", articleService.showByPagination(page, limit,idSearch,searchText));
        modelMap.addAttribute("currentPage", ArticleServiceImp.currentPage);
        modelMap.addAttribute("totalPage", ArticleServiceImp.lastPage);
//        modelMap.addAttribute("foundRecord",articleService.)
    }
    //Fragment

    @GetMapping("/frag1")
    public String getFrag() {
        return "fragment/ajax-fragment::header1";
    }
}

