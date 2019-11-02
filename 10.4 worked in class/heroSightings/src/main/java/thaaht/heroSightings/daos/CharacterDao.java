/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaaht.heroSightings.daos;

import java.util.List;
import thaaht.heroSightings.dtos.Character;
import thaaht.heroSightings.dtos.Location;
import thaaht.heroSightings.dtos.Organization;

/**
 *
 * @author Bill Gates
 */
public interface CharacterDao {
    
    Character getCharacterById (int id);
    List <Character> getAllCharacters ();
    Character addCharacter (Character character);
    void updateCharacter (Character character);
    void deleteCharacterById (int id);
    //List <Location> getLocationsForCharacter (int id);
    //List <Organization> getOrganizationsForCharacter (int id);
}
