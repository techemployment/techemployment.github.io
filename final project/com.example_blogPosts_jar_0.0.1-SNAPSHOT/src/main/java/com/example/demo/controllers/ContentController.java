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
import com.example.demo.dto.Category;
import com.example.demo.dto.Tag;
import com.example.demo.dto.User;
import com.example.demo.dto.Blog;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContentController {

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
    
    @GetMapping("/content")
    public String displayContentPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        User user = users.getUserByUsername(currentPrincipalName);
                
        model.addAttribute ("blogs", blogs.getBlogsForUser(user));
        
        return "content";
    }
    
    @PostMapping ("/addBlogContent")
    public String addBlog (String title, String text, String category, String tag){
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setText(text);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        User user = users.getUserByUsername(currentPrincipalName);
        
        blog.setUser(user);
        Category cat = categories.getCategoryByCategory(category);
        blog.setCategory(cat);
        
        blog.setIsApproved(false);
        LocalDate today = LocalDate.now();
        blog.setDate(today);
        
       
        Set <Tag> tagList = new HashSet<>();
        Tag t = tags.getTagByTag(tag);
        tagList.add(t);
//        for (String tagID: tagIdList){
//            Tag tag = tags.getTagById(Integer.parseInt(tagID));
//            tagList.add(tag);
//        }
                
                
        blog.setTags(tagList);
        
        blogs.createBlog(blog);
        
        return "redirect:/posting";
    }
    
      
}
