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
public class TagController {
    
    @Autowired
    TagDao tags;
    
     @GetMapping("/tag")
    public String displayTagPage(Model model) {
        model.addAttribute("tags", tags.getAllTags());
        
        return "tag";
    }
    
         @PostMapping("/addTag")
    public String addTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        
        
        tags.createTag(tag);
        
        return "redirect:/tag";
    }
    
      @PostMapping("/deleteTag")
    public String deleteUser(Integer id) {
        tags.deleteTag(id);
        return "redirect:/tag";
    }
    
    
}
