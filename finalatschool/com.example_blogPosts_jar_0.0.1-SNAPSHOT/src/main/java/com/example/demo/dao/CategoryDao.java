/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao;

import com.example.demo.dto.Category;
import com.example.demo.dto.User;
import java.util.List;

/**
 *
 * @author Bill Gates
 */
public interface CategoryDao {
    Category getCategoryById(int id);
    Category getCategoryByCategory(String category);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategory(int id);
    Category createCategory(Category category);
}
