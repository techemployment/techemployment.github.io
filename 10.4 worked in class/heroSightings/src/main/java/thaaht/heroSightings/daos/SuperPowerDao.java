/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaaht.heroSightings.daos;

import java.util.List;
import thaaht.heroSightings.dtos.SuperPower;

/**
 *
 * @author Bill Gates
 */
public interface SuperPowerDao {
    SuperPower getSuperPowerByID (int id);
    List<SuperPower> getAllSuperPowers ();
    SuperPower addSuperPower(SuperPower superpower);
    void updateSuperPower (SuperPower superpower);
    void deleteSuperPowerByID (int id);
}
