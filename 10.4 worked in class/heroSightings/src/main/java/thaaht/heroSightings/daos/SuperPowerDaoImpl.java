/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaaht.heroSightings.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import thaaht.heroSightings.daos.CharacterDaoJdbcImpl.CharacterJDBCMapper;
import thaaht.heroSightings.dtos.SuperPower;
import thaaht.heroSightings.dtos.Character;

/**
 *
 * @author Bill Gates
 */
@Repository
public class SuperPowerDaoImpl implements SuperPowerDao {
    private final JdbcTemplate jdbcTemplate;
    
    
@Autowired
    public SuperPowerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public static final class SuperPowerJDBCMapper implements RowMapper<SuperPower>{

        @Override
        public SuperPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPower superpower = new SuperPower ();
            superpower.setSuperPowerID(rs.getInt("superPowerID"));
            superpower.setDescription(rs.getString("description"));
            return superpower;
        }
        
    }
    
    
    @Override
    public SuperPower getSuperPowerByID(int id) {
         try{
            final String selectSuperPowerByID= "select superPowerID,`description` from `superPower` where superPowerID = ?;";
            SuperPower superpower= jdbcTemplate.queryForObject (selectSuperPowerByID, new SuperPowerJDBCMapper(), id );
            superpower.setCharacters(getCharactersForSuperPower (superpower));
           
            return superpower;
        }catch (DataAccessException ex){
            return null;
        } 
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        final String selectAllSuperPowers= "select superPowerID,`description` from `superPower`;";
        List<SuperPower> superpowers = jdbcTemplate.query (selectAllSuperPowers, new SuperPowerJDBCMapper());
        addCharacterToSuperPowers (superpowers);
       
        return superpowers;
    }

    @Override
    public SuperPower addSuperPower(SuperPower superpower) {
        final String INSERT_SUPERPOWER = "insert into superPower (description) values (?)";
        jdbcTemplate.update (INSERT_SUPERPOWER, superpower.getDescription());
        int newID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superpower.setSuperPowerID(newID);
        
        insertSuperPowerCharacter (superpower);
        
        return superpower;
    
    }

    @Override
    public void updateSuperPower(SuperPower superpower) {
        final String UPDATE_SUPERPOWER = "update superPower SET `description`=? where superPowerID=?;";
        jdbcTemplate.update(UPDATE_SUPERPOWER, superpower.getSuperPowerID(), superpower.getDescription());
        
        final String DELETE_CHARACTER_SUPERPOWER = "DELETE from character_superpower where superPowerID= ?";
        jdbcTemplate.update (DELETE_CHARACTER_SUPERPOWER, superpower.getSuperPowerID());
        insertSuperPowerCharacter (superpower);
    }

    @Override
    public void deleteSuperPowerByID(int id) {
        final String DELETE_CHARACTER_SUPERPOWER = "DELETE from character_superpower where characterID= ?";
        jdbcTemplate.update (DELETE_CHARACTER_SUPERPOWER, id);
        
        final String DELETE_SUPERPOWER = "DELETE from superPower where superPowerID=?;";
        jdbcTemplate.update(DELETE_SUPERPOWER, id);
    
    }
    
    private void addCharacterToSuperPowers (List <SuperPower> superpowers){
        for (SuperPower superpower: superpowers){
            superpower.setCharacters (getCharactersForSuperPower (superpower));
        }
    }
    
    private void insertSuperPowerCharacter (SuperPower superpower){
        final String INSERT_CHARACTER_SUPERPOWER= "insert into character_superPower (characterID, superPowerID) values (?,?);";
        for (Character character: superpower.getCharacters()){
            jdbcTemplate.update(INSERT_CHARACTER_SUPERPOWER, character.getCharacterID(), superpower.getSuperPowerID());
        }
        
    }
    
    private List <Character> getCharactersForSuperPower (SuperPower superpower){
        final String SELECT_CHARACTERS_FOR_SUPERPOWER = "select c.characterID, `name`, `description`" +
            "from `character` c" +
            "inner join character_superpower cs on c.characterID = cs.characterID where superPowerID = ?";
        List<Character> characters = jdbcTemplate.query (SELECT_CHARACTERS_FOR_SUPERPOWER, new CharacterJDBCMapper(), superpower.getSuperPowerID());
        
        return characters;
    }
    
}
