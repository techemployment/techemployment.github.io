/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaaht.heroSightings.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thaaht.heroSightings.daos.locationDAOjdbcImpl.LocationMapper;
import thaaht.heroSightings.daos.CharacterDaoJdbcImpl.CharacterJDBCMapper;
import thaaht.heroSightings.dtos.Location;
import thaaht.heroSightings.dtos.Sighting;
import thaaht.heroSightings.dtos.Character;

/**
 *
 * @author trishatolentino
 */

@Repository
public class sightingDAOjdbcImpl implements sightingDAO {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    private final String INSERT_NEW_SIGHTING = "INSERT INTO sighting (sightingID, characterID, locationID, dateSeen) VALUES (?, ?, ?, ?,?)";
    private final String SELECT_ALL_SIGHTINGS = "SELECT sightingID, characterID, locationID, dateSeen FROM sightings";
    private final String SELECT_SIGHTING_BY_ID = "SELECT sightingID, characterID, locationID, dateSeen FROM sighting "
            + "WHERE sightingID = ?";
    private final String SELECT_SIGHTINGS_BY_DATE = "SELECT sightingID, characterID, locationID, dateSeen FROM sighting "
            + "WHERE dateSeen = ?";
    private final String UPDATE_SIGHTING = "UPDATE sighting SET characterID = ?, locationID = ?, dateSeen= ? "
            + "WHERE sightingID = ?";
    private final String DELETE_SIGHTING = "DELETE FROM sighting WHERE characterID = ?";
    
    private final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.locationID, l.name, l.description, "
            + "l.address, l.longitude, l.latitude FROM location l "
            + "INNER JOIN sighting s on l.locationID = s.locationID WHERE s.sightingID  = ?";

    private final String SELECT_CHARACTER_FOR_SIGHTING ="SELECT c.characterID, `name`, `description`"
            +"from `character` c "
            +"INNER JOIN sighting s on c.characterID = s.characterID where s.sightingID = ?";
    
    @Override
    @Transactional
    public Sighting createSighting(Sighting sighting) {
        jdbc.update(INSERT_NEW_SIGHTING, sighting.getSightingID(),
        sighting.getCharacter().getCharacterID(), sighting.getLocation().getLocationID(), sighting.getDateSeen().toString());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(newID);
        return sighting;
    }

    @Override
    @Transactional
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        for (Sighting sighting : sightings) {
            getCharacterForSighting(sighting);
            getLocationForSighting(sighting);
        }
        return sightings;
    }
    
    @Override
    @Transactional
    public Sighting getSightingByID(int sightingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public boolean updateSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public boolean deleteSighting(int sightingID) {
        return jdbc.update(DELETE_SIGHTING, sightingID) > 0;
    }

    @Override
    @Transactional
    public List<Sighting> getSightingsByDate(LocalDate date) {
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_BY_DATE, new SightingMapper(), date.toString());
        for (Sighting sighting : sightings) {
            getCharacterForSighting(sighting);
            getLocationForSighting(sighting);
        }
        return sightings;
    }
    
    private void getCharacterForSighting(Sighting sighting) {
        
       
        Character character = jdbc.queryForObject (SELECT_CHARACTER_FOR_SIGHTING, new CharacterJDBCMapper (), sighting.getSightingID());
        sighting.setCharacter (character);
    }

    private void getLocationForSighting(Sighting sighting) {
        Location location = jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationMapper(), sighting.getSightingID());
        sighting.setLocation(location);
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("sightingID"));

            sighting.setDateSeen(rs.getDate("dateSeen").toLocalDate());
            return sighting;
        }

    }
    
    
}
