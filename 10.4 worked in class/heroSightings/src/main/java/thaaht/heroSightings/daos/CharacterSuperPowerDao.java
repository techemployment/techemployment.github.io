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
public interface CharacterSuperPowerDao {
    void create (int characterID, int superPowerID);
    void delete (int characterID, int superPowerID);
    void deleteCharacterBysuperPowerID (int superPowerID);
    void deletesuperPowerByCharacterID (int chrarcterID);
}
