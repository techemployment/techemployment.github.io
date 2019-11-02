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
import org.springframework.transaction.annotation.Transactional;
import thaaht.heroSightings.dtos.Character;
import thaaht.heroSightings.dtos.Location;
import thaaht.heroSightings.dtos.Organization;
import thaaht.heroSightings.dtos.Sighting;
import thaaht.heroSightings.dtos.SuperPower;
import thaaht.heroSightings.daos.locationDAOjdbcImpl.LocationMapper;
import thaaht.heroSightings.daos.SuperPowerDaoImpl.SuperPowerJDBCMapper;
import thaaht.heroSightings.daos.locationDAOjdbcImpl.LocationMapper;

/**
 *
 * @author Bill Gates
 */
@Repository
public class CharacterDaoJdbcImpl implements CharacterDao{
    private final JdbcTemplate jdbcTemplate;
    
    

@Autowired
    public CharacterDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public static final class CharacterJDBCMapper implements RowMapper<Character>{

        @Override
        public Character mapRow(ResultSet rs, int i) throws SQLException {
            Character character = new Character ();
            character.setCharacterID(rs.getInt("characterID"));
            character.setName(rs.getString("name"));
            character.setDescription(rs.getString("description"));
            return character;
        }
        
    }
     
    @Override
    public Character getCharacterById (int id){
        try{
            final String selectCharacterByID= "select characterID, name, `description` from `character` where characterID = ?;";
            Character character= jdbcTemplate.queryForObject (selectCharacterByID, new CharacterJDBCMapper(), id );
            character.setLocations(getLocationsForCharacter (character));
            //character.setOrganizations(getOrganizationsForCharacter(character));
            character.setSuperPowers (getSuperPowersForCharacter(character));
            return character;
        }catch (DataAccessException ex){
            return null;
        }   
    }
    
    @Override
    public List <Character> getAllCharacters (){
        final String selectAllCharacters= "select characterID, name, `description` from `character`;";
        List<Character> characters = jdbcTemplate.query (selectAllCharacters, new CharacterJDBCMapper());
        addOrganizationAndLocationAndSuperPowerToCharacters (characters);
       
        return characters;
    }
    
    public List<Character> getCharactersForSuperPower (SuperPower superpower){
        final String SELECT_CHARACTERS_FOR_SUPERPOWER = "SELECT c.characterID, `name`, `description`"
            +"from `character` c"
            +"inner join character_superPower cs on c.characterID = cs.characterID where cs.superPowerID= ?;";
        List<Character> characters= jdbcTemplate.query(SELECT_CHARACTERS_FOR_SUPERPOWER, new CharacterJDBCMapper(), superpower.getSuperPowerID());
        addOrganizationAndLocationAndSuperPowerToCharacters (characters);
        return characters;
    }
    
    public List<Character> getCharactersForLocation(Location location){
        final String SELECT_CHARACTERS_FOR_LOCATION = "SELECT c.characterID, `name`, `description`"
            +"from `character` c"
            +"inner join character_location cl on c.characterID = cl.characterID where cl.locationID= ?;";
        List<Character> characters= jdbcTemplate.query(SELECT_CHARACTERS_FOR_LOCATION, new CharacterJDBCMapper(), location.getLocationID());
        addOrganizationAndLocationAndSuperPowerToCharacters (characters);
        return characters;
    }
    
    public List<Character> getCharactersForOrganization(Organization organization){
        final String SELECT_CHARACTERS_FOR_LOCATION = "SELECT c.characterID, `name`, `description`"
            +"from `character` c"
            +"inner join character_organization co on c.characterID = co.characterID where co.organizationID= ?;";
        List<Character> characters= jdbcTemplate.query(SELECT_CHARACTERS_FOR_LOCATION, new CharacterJDBCMapper(), organization.getOrganizationID());
        addOrganizationAndLocationAndSuperPowerToCharacters (characters);
        return characters;
    }
    
    public Character getCharacterForSighting (Sighting sighting){
        final String SELECT_CHARACTER_FOR_SIGHTING = "SELECT c.characterID, `name`, `description`"
            +"from `character` c"
            +"inner join sighting s on c.characterID = s.characterID where s.sightingID = ?;";
    
        Character character = jdbcTemplate.queryForObject(SELECT_CHARACTER_FOR_SIGHTING, new CharacterJDBCMapper(), sighting.getSightingID());
        return character;
    }
   
    @Override
    @Transactional
    public Character addCharacter (Character character){
        final String INSERT_CHARACTER = "insert into `character` (name, description) "
                + "values (?,?)";
        jdbcTemplate.update (INSERT_CHARACTER, character.getName(), character.getDescription());
        int newID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        character.setCharacterID(newID);
        
        //private helper method that establishes the bridge tables (organization, location, superPower)
        insertCharacterSuperPowerLocationOrganization (character);
        return character;
    }
    
    //WHAT ABOUT SIGHTING?
    //insert- to sql, creating bridge table
    //add-to java object
    private void insertCharacterSuperPowerLocationOrganization (Character character){
        final String INSERT_CHARACTER_SUPERPOWER= "insert into character_superPower (characterID, superPowerID) values (?,?);";
        final String INSERT_CHARACTER_LOCATION="insert into character_location (characterID, locationID) values (?,?);";
        final String INSERT_CHARACTER_ORGANIZATION="insert into character_organization (characterID, organizationID) values (?, ?);";
        for (SuperPower superpower: character.getSuperPowers()){
            jdbcTemplate.update(INSERT_CHARACTER_SUPERPOWER, character.getCharacterID(), superpower.getSuperPowerID());
        }
        
        for (Location location: character.getLocations()){
            jdbcTemplate.update(INSERT_CHARACTER_LOCATION, character.getCharacterID(), location.getLocationID());
        }
        
        for (Organization organization: character.getOrganizations()){
            jdbcTemplate.update(INSERT_CHARACTER_ORGANIZATION, character.getCharacterID(), organization.getOrganizationID());
        }
    }
    
    //WHAT ABOUT SIGHTING? 
    @Override
    @Transactional
    public void updateCharacter (Character character){
        final String UPDATE_CHARACTER ="update `character`"
            +"SET name = ?, description=? where characterID=? ;";
        jdbcTemplate.update (UPDATE_CHARACTER, 
                character.getName(),
                character.getDescription(),
                character.getCharacterID());
        
        final String DELETE_CHARACTER_SUPERPOWER = "DELETE from character_superpower where characterID= ?";
        final String DELETE_CHARACTER_LOCATION = "DELETE from character_location where characterID= ?";
        final String DELETE_CHARACTER_ORGANIZATION = "DELETE from character_organization where characterID= ?";
        
        jdbcTemplate.update (DELETE_CHARACTER_SUPERPOWER, character.getCharacterID());
        jdbcTemplate.update (DELETE_CHARACTER_LOCATION, character.getCharacterID());
        jdbcTemplate.update (DELETE_CHARACTER_ORGANIZATION, character.getCharacterID());
        insertCharacterSuperPowerLocationOrganization (character);
    }
    
    @Override
    @Transactional
    public void deleteCharacterById (int id){
        final String DELETE_CHARACTER_SUPERPOWER = "DELETE from character_superpower where characterID= ?";
        final String DELETE_CHARACTER_LOCATION = "DELETE from character_location where characterID= ?";
        final String DELETE_CHARACTER_ORGANIZATION = "DELETE from character_organization where characterID= ?";
        
        //delete sighting
        jdbcTemplate.update (DELETE_CHARACTER_SUPERPOWER, id);
        jdbcTemplate.update (DELETE_CHARACTER_LOCATION, id);
        jdbcTemplate.update (DELETE_CHARACTER_ORGANIZATION, id);
        
        final String DELETE_CHARACTER = "DELETE from `character` where characterID=?";
        jdbcTemplate.update (DELETE_CHARACTER, id);
    }
    

    
  
 
   //privtae helper methods 
    
    private void addOrganizationAndLocationAndSuperPowerToCharacters (List<Character> characters){
        for (Character character: characters){
            //character.setOrganizations (getOrganizationsForCharacter(character));
            character.setLocations (getLocationsForCharacter(character));
            character.setSuperPowers (getSuperPowersForCharacter(character));
        }
    }
    
    
    
   
    private List <Location> getLocationsForCharacter (Character character){
        final String SELECT_LOCATIONS_FOR_CHARACTER = "select l.locationID, `name`, `description`, address, longitude, latitude" 
            +"from location l"
            +"inner join character_location cl ON cl.locationID = l.locationID where characterID = ?";
        return jdbcTemplate.query (SELECT_LOCATIONS_FOR_CHARACTER, new LocationMapper (), character.getCharacterID());
    }
    
    /*
    private List <Organization> getOrganizationsForCharacter (Character character){
        final String SELECT_ORGANIZATIONS_FOR_CHARACTER = "select o.organizationID, `name`, `description`, address " +
            "from `organization` o" +
            "inner join character_organization co ON co.organizationID = o.organizationID where characterID = ?";
        return jdbcTemplate.query (SELECT_ORGANIZATIONS_FOR_CHARACTER, new OrganizationMapper (), character.getCharacterID());
    } 
    */
   private List <SuperPower> getSuperPowersForCharacter (Character character){
        final String SELECT_ORGANIZATIONS_FOR_CHARACTER = "select s.superPowerID,`description` " +
            "from superPower s" +
            "inner join character_superPower cs ON cs.superPowerID = s.superPowerID where characterID = ?";
        return jdbcTemplate.query (SELECT_ORGANIZATIONS_FOR_CHARACTER, new SuperPowerJDBCMapper (), character.getCharacterID());
    } 

}
