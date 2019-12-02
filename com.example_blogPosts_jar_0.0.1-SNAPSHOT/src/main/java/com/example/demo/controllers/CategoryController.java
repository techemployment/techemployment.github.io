/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controllers;

/**
 *
 * @author Bill Gates
 */

import com.example.demo.dao.BlogDao;
import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.RoleDao;
import com.example.demo.dao.TagDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.Role;
import com.example.demo.dto.User;
import com.example.demo.dto.Blog;
import com.example.demo.dto.Category;
import com.example.demo.dto.Tag;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
     @Autowired
     CategoryDao categories;
     
      @GetMapping("/category")
    public String displayCategoryPage(Model model) {
        model.addAttribute("categories", categories.getAllCategories());
        
        return "category";
    }
    
         @PostMapping("/addCategory")
    public String addCategory(String name) {
        Category cat = new Category();
        cat.setName(name);
        
        
        categories.createCategory(cat);
        
        return "redirect:/category";
    }
    
      @PostMapping("/deleteCategory")
    public String deleteCategory(Integer id) {
        categories.deleteCategory(id);
        return "redirect:/category";
    }
}
