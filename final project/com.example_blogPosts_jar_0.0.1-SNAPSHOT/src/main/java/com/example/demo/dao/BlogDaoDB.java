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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bill Gates
 */
@Repository
public class BlogDaoDB implements BlogDao{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Blog getBlogById(int id) {
        try {
            final String SELECT_BLOG_BY_ID = "SELECT * FROM blog WHERE blogID = ?";
            Blog blog = jdbc.queryForObject(SELECT_BLOG_BY_ID, new BlogDaoDB.BlogMapper(), id);
            blog.setCategory (getCategoryForBlog(blog));
            blog.setUser (getUserForBlog(blog));
            blog.setTags(getTagsForBlog(blog));
            return blog;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    

    @Override
    public Blog getBlogByTitle(String title) {
        try {
            final String SELECT_BLOG_BY_TITLE = "SELECT * FROM blog WHERE title = ?";
            Blog blog = jdbc.queryForObject(SELECT_BLOG_BY_TITLE, new BlogDaoDB.BlogMapper(), title);
            blog.setCategory (getCategoryForBlog(blog));
            blog.setUser (getUserForBlog(blog));
            blog.setTags(getTagsForBlog(blog));
            return blog;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    private Category getCategoryForBlog (Blog blog){
        final String SELECT_CATEGORY_FOR_BLOG = "SELECT c.* from category c inner join blog b on c.categoryID = b.categoryID where blogID=?;";
        return jdbc.queryForObject(SELECT_CATEGORY_FOR_BLOG, new CategoryDaoDB.CategoryMapper(), blog.getBlogID());
    }

     private User getUserForBlog (Blog blog){
        final String SELECT_USER_FOR_BLOG = "SELECT u.* from `user` u inner join blog b on u.id = b.userID where blogID=?;";
        return jdbc.queryForObject(SELECT_USER_FOR_BLOG, new UserDaoDB.UserMapper(), blog.getBlogID());
    }
     
    private Set <Tag> getTagsForBlog (Blog blog){
        final String SELECT_TAGS_FOR_BLOGS = "SELECT t.* from tag t inner join blog_tag bt on t.tagID = bt.tagID where blogID=?;";
        Set<Tag> tags = new HashSet(jdbc.query (SELECT_TAGS_FOR_BLOGS, new TagDaoDB.TagMapper(), blog.getBlogID()));
        return tags; 
    }
    
    @Override
    public List<Blog> getAllBlogs() {
       final String SELECT_ALL_BLOGS = "SELECT * FROM blog";
        List<Blog> blogs = jdbc.query(SELECT_ALL_BLOGS, new BlogDaoDB.BlogMapper());
        addRelationshipsToBlogs (blogs);        
        return blogs;
    }
    
    @Override
    public List<Blog> getApprovedBlogs (){
        final String SELECT_ALL_BLOGS = "SELECT * FROM blog where approval=1";
        List<Blog> blogs = jdbc.query(SELECT_ALL_BLOGS, new BlogDaoDB.BlogMapper());
        addRelationshipsToBlogs (blogs);        
        return blogs;
    }
    
    private void addRelationshipsToBlogs (List<Blog> blogs){
        for (Blog blog: blogs){
            blog.setCategory (getCategoryForBlog (blog));
            blog.setUser (getUserForBlog(blog));
            blog.setTags(getTagsForBlog(blog));
        }
    }

    @Override
    public void deleteBlog(int id) {
        final String DELETE_BLOG_TAG = "DELETE FROM blog_tag WHERE blogID= ?";
        jdbc.update (DELETE_BLOG_TAG, id);
        
        final String DELETE_BLOG = "DELETE FROM blog WHERE blogID= ?";
        jdbc.update (DELETE_BLOG, id);
    }

    @Override
    public void updateBlog(Blog blog) {
        final String UPDATE_BLOG = "UPDATE blog SET title=?, paragraph=?, userID=?, categoryID=?, approval=?, `date`=? WHERE blogID = ?";
        jdbc.update (UPDATE_BLOG, 
                blog.getTitle(), 
                blog.getText(), 
                blog.getUser().getId(), 
                blog.getCategory().getCategoryID(), 
                blog.getIsApproved(), 
                blog.getDate(), 
                blog.getBlogID());
        
        final String DELETE_BLOG_TAG = "DELETE FROM blog_tag WHERE blogID= ?";
        jdbc.update (DELETE_BLOG_TAG, blog.getBlogID());
        insertBlogTag(blog);
    }

    @Override
    public Blog createBlog(Blog blog) {
        final String INSERT_BLOG = "INSERT INTO blog (title, paragraph, userID, categoryID, approval, date) values (?,?,?,?,?,?);";
        
        jdbc.update(INSERT_BLOG, blog.getTitle(), blog.getText(), blog.getUser().getId(), blog.getCategory().getCategoryID(), blog.getIsApproved(), blog.getDate());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        blog.setBlogID(newId);
        
        insertBlogTag(blog);
        
        return blog;
    }
    
    private void insertBlogTag (Blog blog){
        final String INSERT_BLOG_TAG = "INSERT INTO blog_tag (blogID, tagID) values (?,?);";
        for (Tag tag: blog.getTags()){
            jdbc.update (INSERT_BLOG_TAG, blog.getBlogID(), tag.getTagID());
        }
    }

    @Override
    public List<Blog> getBlogsForCategory(Category category) {
        final String SELECT_BLOGS_FOR_CATEGORY = "SELECT * FROM blog where categoryID =?";
        List<Blog> blogs = jdbc.query(SELECT_BLOGS_FOR_CATEGORY, new BlogDaoDB.BlogMapper(),category.getCategoryID());
        addRelationshipsToBlogs (blogs);
        
        return blogs;
    }

    @Override
    public List<Blog> getBlogsForUser(User user) {
        final String SELECT_BLOGS_FOR_USER = "SELECT * FROM blog where userID =?";
        List<Blog> blogs = jdbc.query(SELECT_BLOGS_FOR_USER, new BlogDaoDB.BlogMapper(),user.getId());
        addRelationshipsToBlogs (blogs);
        
        return blogs;
    }

    @Override
    public List<Blog> getBlogsForTag(Tag tag) {
        final String SELECT_BLOGS_FOR_TAG= "SELECT * FROM blog b inner join blog_tag bt on b.blogID = bt.blogID where bt.tagID=?";
        List<Blog> blogs = jdbc.query(SELECT_BLOGS_FOR_TAG, new BlogDaoDB.BlogMapper(),tag.getTagID());
        addRelationshipsToBlogs (blogs);
        
        return blogs;
    }

    
    public static final class BlogMapper implements RowMapper<Blog> {

        @Override
        public Blog mapRow(ResultSet rs, int i) throws SQLException {
            Blog blog = new Blog();
            blog.setBlogID(rs.getInt("blogID"));
            blog.setTitle(rs.getString("title"));
            blog.setText(rs.getString("paragraph"));
            blog.setIsApproved(rs.getBoolean("approval"));
            blog.setDate(rs.getDate("date").toLocalDate());
            return blog;
        }
    }
}
