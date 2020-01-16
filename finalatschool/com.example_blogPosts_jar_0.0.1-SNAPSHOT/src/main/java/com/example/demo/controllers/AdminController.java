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
public class AdminController {

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
    
    @GetMapping("/admin")
    public String displayAdminPage(Model model) {
        model.addAttribute("users", users.getAllUsers());
        model.addAttribute("blogs", blogs.getAllBlogs());
        return "admin";
    }
    
        @PostMapping("/addUser")
    public String addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEnabled(true);
        
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roles.getRoleByRole("ROLE_CONTRIBUTOR"));
        user.setRoles(userRoles);
        
        users.createUser(user);
        
        return "redirect:/admin";
    }
    
//    @PostMapping ("addBlog")
//    public String addBlog (String title, String text, String username, String category, String tag){
//        Blog blog = new Blog();
//        blog.setTitle(title);
//        blog.setText(text);
//        User user = users.getUserByUsername(username);
//        blog.setUser(user);
//        Category cat = categories.getCategoryByCategory(category);
//        blog.setCategory(cat);
//        
//        blog.setIsApproved(false);
//        LocalDate today = LocalDate.now();
//        blog.setDate(today);
//        
//        Tag t = tags.getTagByTag(tag);
//        Set <Tag> tlist = new HashSet<>();
//        tlist.add(t);
//        blog.setTags(tlist);
//        
//        blogs.createBlog(blog);
//        
//        return "redirect:/admin";
//    }
//    
        @PostMapping("/deleteUser")
    public String deleteUser(Integer id) {
        users.deleteUser(id);
        return "redirect:/admin";
    }
    
        @GetMapping("/editUser")
    public String editUserDisplay(Model model, Integer id, Integer error) {
        User user = users.getUserById(id);
        List <Role> roleList = roles.getAllRoles();
        
        model.addAttribute("user", user);
        model.addAttribute("roles", roleList);
        
        if(error != null) {
            if(error == 1) {
                model.addAttribute("error", "Passwords did not match, password was not updated.");
            }
        }
        
        return "editUser";
    }
    
       @PostMapping(value="/editUser")
    public String editUserAction(String[] roleIdList, Boolean enabled, Integer id) {
        User user = users.getUserById(id);
        if(enabled != null) {
            user.setEnabled(enabled);
        } else {
            user.setEnabled(false);
        }
        
        Set<Role> roleList = new HashSet<>();
        for(String roleId : roleIdList) {
            Role role = roles.getRoleById(Integer.parseInt(roleId));
            roleList.add(role);
        }
        user.setRoles(roleList);
        users.updateUser(user);
        
        return "redirect:/admin";
    }
    
  @PostMapping("editPassword") 
    public String editPassword(Integer id, String password, String confirmPassword) {
        User user = users.getUserById(id);
        
        if(password.equals(confirmPassword)) {
            user.setPassword(encoder.encode(password));
            users.updateUser(user);
            return "redirect:/admin";
        } else {
            return "redirect:/editUser?id=" + id + "&error=1";
        }
    }
    
  
    
} 