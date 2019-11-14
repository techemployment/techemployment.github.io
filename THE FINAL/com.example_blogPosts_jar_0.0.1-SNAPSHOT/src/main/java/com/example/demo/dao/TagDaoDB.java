/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao;

import com.example.demo.dto.Category;
import com.example.demo.dto.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
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
public class TagDaoDB implements TagDao {
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Tag getTagById(int id) {
        try {
            final String SELECT_TAG_BY_ID = "SELECT * FROM tag WHERE tagID = ?";
            Tag tag = jdbc.queryForObject(SELECT_TAG_BY_ID, new TagDaoDB.TagMapper(), id);
            //category.setBlogs(getBlogsForCategory(category.getCategoryID()));
            return tag;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Tag getTagByTag(String tagName) {
        try {
            final String SELECT_TAG_BY_TAG = "SELECT * FROM tag WHERE `name` = ?";
            Tag tag = jdbc.queryForObject(SELECT_TAG_BY_TAG, new TagDaoDB.TagMapper(), tagName);
            //category.setBlogs(getBlogsForCategory(category.getCategoryID()));
            return tag;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    

    @Override
    @Transactional
    public void deleteTag(int id) {
        final String DELETE_BLOG_TAG = "DELETE FROM blog_tag WHERE tagID= ?";
        final String DELETE_TAG = "DELETE FROM tag WHERE tagID = ?";
        jdbc.update(DELETE_BLOG_TAG, id);
        jdbc.update(DELETE_TAG, id);
    }

    @Override
    public void updateTag(Tag tag) {
        final String UPDATE_TAG = "UPDATE tag SET name = ? WHERE tagID = ?";
        jdbc.update(UPDATE_TAG, tag.getName(), tag.getTagID());
    }

    @Override
    public Tag createTag(Tag tag) {
        final String INSERT_TAG = "INSERT INTO tag(name) VALUES(?)";
        jdbc.update(INSERT_TAG, tag.getName());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        tag.setTagID(newId);
        return tag;
    }

    @Override
    public List<Tag> getAllTags() {
        final String SELECT_ALL_TAGS = "SELECT * FROM tag";
        List <Tag> tags = jdbc.query(SELECT_ALL_TAGS, new TagDaoDB.TagMapper());
//        for (Category category : categories) {
//            category.setBlogs(getBlogsForCategory(category.getCategoryID()));
//        }
        return tags;
    }
    
   
            
    public static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag tag = new Tag();
            tag.setTagID(rs.getInt("tagID"));
            tag.setName(rs.getString("name"));
            
            return tag;
        }
    }
    
}
