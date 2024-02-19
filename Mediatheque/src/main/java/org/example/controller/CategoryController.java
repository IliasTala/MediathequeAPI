package org.example.controller;

import org.example.model.Category;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public List<Category> list() {
        return categoryService.findAllCategories();
    }

    @PostMapping("/save")
    public Category save(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<Category> getById(@PathVariable(name = "id") Long categoryId) {
        return categoryService.getCategoryById(categoryId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}