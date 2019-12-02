/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao;

import com.example.demo.dto.Category;
import com.example.demo.dto.Role;
import com.example.demo.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bill Gates
 */
@Repository
public class CategoryDaoDB implements CategoryDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Category getCategoryById(int id) {
        try {
            final String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE categoryID = ?";
            Category category = jdbc.queryForObject(SELECT_CATEGORY_BY_ID, new CategoryDaoDB.CategoryMapper(), id);
            //category.setBlogs(getBlogsForCategory(category.getCategoryID()));
            return category;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Category getCategoryByCategory(String categoryName) {
        try {
            final String SELECT_CATEGORY_BY_CATEGORY = "SELECT * FROM category WHERE `name` = ?";
            Category category = jdbc.queryForObject(SELECT_CATEGORY_BY_CATEGORY, new CategoryDaoDB.CategoryMapper(), categoryName);
            //category.setBlogs(getBlogsForCategory(category.getCategoryID()));
            return category;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        final String SELECT_ALL_CATEGORIES = "SELECT * FROM category";
        List<Category> categories = jdbc.query(SELECT_ALL_CATEGORIES, new CategoryDaoDB.CategoryMapper());
//        for (Category category : categories) {
//            category.setBlogs(getBlogsForCategory(category.getCategoryID()));
//        }
        return categories;
    }

    @Override
    public void updateCategory(Category category) {
        final String UPDATE_CATEGORY = "UPDATE category SET name = ? WHERE categoryID = ?";
        jdbc.update(UPDATE_CATEGORY, category.getName(), category.getCategoryID());

    }

    @Override
    @Transactional
    public void deleteCategory(int id) {
        final String DELETE_BLOG_TAG = "delete bt.* from blog_tag bt inner join blog b on bt.blogID = b.blogID where b.categoryID = ?;";
        final String DELETE_BLOG = "delete from blog where categoryID=?;";
        final String DELETE_CATEGORY= "delete from category where categoryID=?;";
        jdbc.update(DELETE_BLOG_TAG, id);
        jdbc.update(DELETE_BLOG, id);
        jdbc.update(DELETE_CATEGORY, id);
    }

    @Override
    public Category createCategory(Category category) {
       final String INSERT_CATEGORY = "INSERT INTO category(name) VALUES(?)";
        jdbc.update(INSERT_CATEGORY, category.getName());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        category.setCategoryID(newId);
        return category;
    }
    
    
     public static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category category = new Category();
            category.setCategoryID(rs.getInt("categoryID"));
            category.setName(rs.getString("name"));
            
            return category;
        }
    }
    
}
