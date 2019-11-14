/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao;

import com.example.demo.dto.Blog;
import com.example.demo.dto.Category;
import com.example.demo.dto.Tag;
import com.example.demo.dto.User;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bill Gates
 */
public interface BlogDao {
    Blog getBlogById(int id);
    Blog getBlogByTitle(String title);
    List<Blog> getAllBlogs();
    void deleteBlog(int id);
    void updateBlog(Blog blog);
    Blog createBlog(Blog blog);
    List<Blog> getBlogsForCategory (Category category);
    List<Blog> getBlogsForUser (User user);
    List<Blog> getBlogsForTag (Tag tag);
    List<Blog> getApprovedBlogs ();
    String saveImage (MultipartFile imageFile) throws Exception;
   
}
