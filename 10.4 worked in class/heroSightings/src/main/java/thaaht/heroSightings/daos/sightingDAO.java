/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaaht.heroSightings.daos;

import java.time.LocalDate;
import java.util.List;
import thaaht.heroSightings.dtos.Sighting;


/**
 *
 * @author trishatolentino
 */
public interface sightingDAO {
    
    //Create
    public Sighting createSighting(Sighting sighting);

    //Read All
    public List<Sighting> getAllSightings();

    //Read By ID
    public Sighting getSightingByID(int sightingID);

    //Update
    public boolean updateSighting(Sighting sighting);

    //Delete
    public boolean deleteSighting(int sightingID);
        
    public List<Sighting> getSightingsByDate(LocalDate date);
    
}
