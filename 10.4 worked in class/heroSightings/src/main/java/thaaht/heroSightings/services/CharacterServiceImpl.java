/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaaht.heroSightings.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaaht.heroSightings.dtos.Character;
import thaaht.heroSightings.daos.CharacterDao;

/**
 *
 * @author Bill Gates
 */
    @Service
public class CharacterServiceImpl implements CharacterService {
    private CharacterDao characterDao;
    
    @Autowired
    public CharacterServiceImpl(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }
    
    @Override
    public List<Character> getAllCharacters() {
        List<Character> list = characterDao.getAllCharacters();
               
        return list;
    }
    
}
