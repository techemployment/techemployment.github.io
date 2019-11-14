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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    BlogDao blogs;
    
    
    @GetMapping({"/", "/home"})
    public String displayHomePage(Model model) {
        model.addAttribute ("blogs", blogs.getApprovedBlogs());
        return "home";
    }
    
    
    
    
}
