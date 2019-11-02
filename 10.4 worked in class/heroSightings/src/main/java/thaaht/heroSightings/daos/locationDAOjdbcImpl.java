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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thaaht.heroSightings.dtos.Location;

/**
 *
 * @author trishatolentino
 */

@Repository
public class locationDAOjdbcImpl implements locationDAO {
    
    @Autowired
    JdbcTemplate jdbc;
    
    private final String INSERT_NEW_LOCATION = "INSERT INTO location(name, description, "
        + "address, longitude, latitude) VALUES (?, ?, ?, ?, ?) ";
    private final String SELECT_ALL_LOCATIONS = "SELECT locationID, name, description, "
        + "address, longitude, longitude FROM location";
    private final String SELECT_LOCATION_BY_ID = "SELECT locationID, name, description, "
        + "address, longitude, latitude FROM location WHERE locationID = ?";
    private final String UPDATE_LOCATION = "UPDATE location SET name = ?, description = ?,"
        + "address = ?, longitude = ?, latitude = ? WHERE locationID = ?";
    private final String DELETE_LOCATION = "DELETE FROM location WHERE locationID = ?";
    //need to delete from sightings + organizations as well
    private final String DELETE_FROM_SIGHTINGS = "DELETE FROM sighting WHERE locationID = ?";
    private final String DELETE_FROM_ORGANIZATIONS = "UPDATE organization SET locationID = NULL WHERE locationID = ?";

    @Override
    @Transactional
    public Location createLocation(Location location) {
        jdbc.update(INSERT_NEW_LOCATION, location.getName(), location.getDescription(), location.getAddress(),
                location.getLatitude(), location.getLongitude());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationID(newID);
        return location;
    }

    @Override
    @Transactional
    public List<Location> getAllLocations() {
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location getLocationByID(int locationID) {
        return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), locationID);
    }

    @Override
    @Transactional
    public Boolean updateLocation(Location location) {
        return jdbc.update(UPDATE_LOCATION, location.getName(), location.getDescription(),
                location.getAddress(), location.getLatitude(), location.getLongitude(),
                location.getLocationID()) > 0;
    }

    @Override
    @Transactional
    public Boolean deleteLocation(int locationID) {
        jdbc.update(DELETE_FROM_SIGHTINGS, locationID);
        jdbc.update(DELETE_FROM_ORGANIZATIONS, locationID);
        return jdbc.update(DELETE_LOCATION, locationID) > 0;
    }
    
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("locationID"));
            location.setName(rs.getString("name"));
             location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setLongitude(rs.getFloat("longitude"));
            location.setLatitude(rs.getFloat("latitude"));
            return location;
        }

    }
    
}
