/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaaht.heroSightings.daos;

/**
 *
 * @author Bill Gates
 */
public interface CharacterLocationDao {
    void create (int characterID, int locationID);
    void delete (int characterID, int locationID);
    void deleteCharacterByLocationID (int locationID);
    void deleteLocationByCharacterID (int chrarcterID);
}
