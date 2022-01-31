package com.blogme.post.controller;

import com.blogme.post.dto.CategoryDto;
import com.blogme.post.dto.CategoryUpdateDto;
import com.blogme.post.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id){
        return categoryService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody  CategoryDto categoryDto){
         categoryService.add(categoryDto);
    }

    @PutMapping
    public void update(@RequestBody CategoryUpdateDto categoryDto){
        categoryService.update(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }

}
