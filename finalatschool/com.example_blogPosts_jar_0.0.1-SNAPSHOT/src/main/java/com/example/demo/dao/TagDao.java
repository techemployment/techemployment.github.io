/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao;

import com.example.demo.dto.Role;
import com.example.demo.dto.Tag;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Bill Gates
 */
public interface TagDao {
    Tag getTagById(int id);
    Tag getTagByTag(String tag);
    List <Tag> getAllTags();
    void deleteTag(int id);
    void updateTag(Tag tag);
    Tag createTag(Tag tag);
}
