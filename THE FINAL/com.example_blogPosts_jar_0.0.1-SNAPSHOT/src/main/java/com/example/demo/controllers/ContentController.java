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
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute ("categories", categories.getAllCategories());
        model.addAttribute("tags", tags.getAllTags());
        
        return "content";
    }
    
    @PostMapping ("/addBlogContent")
    public String addBlog (String title, String text, String categoryId, String[] tagIdList, MultipartFile imageFile, RedirectAttributes redirectAttributes){
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
        
         String imagePath;
        
        try {
            imagePath= blogs.saveImage (imageFile);
            blog.setImagePath (imagePath);
        } catch (Exception ex) {
            Logger.getLogger(PostingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        blogs.createBlog(blog);
        
        return "redirect:/content";
    }
    
     @GetMapping ("/editContent")
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
        return "editContent";
        
    }
    
    @PostMapping (value="/editContent")
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
        
        return "redirect:/content";
    
    }  
    
    @PostMapping ("/deleteContent")
    public String deleteBlog (Integer id){
        blogs.deleteBlog(id);
        return "redirect:/content";
    }
    
      
}
