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
public interface CharacterOrganizationDao {
    void create (int characterID, int organizationID);
    void delete (int characterID, int organizationID);
    void deleteCharacterByOrganizationID (int organizationID);
    void deleteOrganizationByCharacterID (int chrarcterID);
}
