/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaaht.heroSightings.daos;

import java.util.List;
import thaaht.heroSightings.dtos.Location;

/**
 *
 * @author trishatolentino
 */

public interface locationDAO {
    
    //create
    Location createLocation(Location location);

    //read all
    List<Location> getAllLocations();

    //read by ID
    Location getLocationByID(int locationID);

    //update
    Boolean updateLocation(Location location);

    //delete
    Boolean deleteLocation(int locationID);
    
}
