/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controllers;

import com.example.demo.dao.BlogDao;
import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.RoleDao;
import com.example.demo.dao.TagDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.Category;
import com.example.demo.dto.Tag;
import com.example.demo.dto.User;
import com.example.demo.dto.Blog;
import com.example.demo.dto.Role;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Bill Gates
 */
@Controller
public class PostingController {
    @Autowired
    BlogDao blogs;
    
    @Autowired
    UserDao users;
    
    @Autowired
    RoleDao roles;
    
    @Autowired
    CategoryDao categories;
    
    @Autowired
    TagDao tags;
    
    @Autowired
    PasswordEncoder encoder;
    
    
    @GetMapping("/posting")
    public String displayPostingPage(Model model) {
        model.addAttribute ("blogs", blogs.getAllBlogs());
        model.addAttribute ("categories", categories.getAllCategories());
        model.addAttribute("tags", tags.getAllTags());
        //User user = users.getUserById(id);
        //model.addAttribute("user", user);
        return "posting";
    }
    
    @PostMapping ("/addBlog")
    public String addBlog (String title, String text, String categoryId, String[] tagIdList){
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setText(text);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        User user = users.getUserByUsername(currentPrincipalName);
        
        blog.setUser(user);
        Category cat = categories.getCategoryById(Integer.parseInt(categoryId));
        blog.setCategory(cat);
        
        blog.setIsApproved(false);
        LocalDate today = LocalDate.now();
        blog.setDate(today);
        
       
        Set <Tag> tagList = new HashSet<>();
 
        for(String tagId : tagIdList) {
            Tag tag = tags.getTagById(Integer.parseInt(tagId));
            tagList.add(tag);
        }
        
        blog.setTags(tagList);
        
        blogs.createBlog(blog);
        
        return "redirect:/posting";
    }
    
    @GetMapping ("/editPosting")
    public String editPostingDisplay (Model model, Integer id, Integer error){
        Blog blog = blogs.getBlogById (id);
        List<Tag> tagList = tags.getAllTags();
        List<Category> categoryList = categories.getAllCategories();
        
        model.addAttribute("blog", blog);
        model.addAttribute("tags", tagList);
        model.addAttribute ("categories", categoryList);
        
//        if(error != null) {
//            if(error == 1) {
//                model.addAttribute("error", "Passwords did not match, password was not updated.");
//            }
//        }
//        
        return "editPosting";
        
    }
    
    @PostMapping (value="/editPosting")
    public String editPostingAction (String [] tagIdList, Boolean isApproved, Integer id, String title, String text, String categoryId){
        Blog blog = blogs.getBlogById(id);
        if(isApproved != null) {
            blog.setIsApproved(isApproved);
        } else {
            blog.setIsApproved(false);
        }
        Category cat = categories.getCategoryById(Integer.parseInt(categoryId));
        blog.setTitle(title);
        blog.setText(text);
        blog.setCategory(cat);
        
        Set<Tag> tagList = new HashSet<>();
        for(String tagId : tagIdList) {
            Tag tag = tags.getTagById(Integer.parseInt(tagId));
            tagList.add(tag);
        }
        
        blog.setTags(tagList);
        blogs.updateBlog(blog);
        
        return "redirect:/posting";
    
    }  
    
    @PostMapping ("/deleteBlog")
    public String deleteBlog (Integer id){
        blogs.deleteBlog(id);
        return "redirect:/posting";
    }
}
