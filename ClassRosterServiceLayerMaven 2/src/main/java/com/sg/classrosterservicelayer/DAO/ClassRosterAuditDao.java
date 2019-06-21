/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classrosterservicelayer.DAO;

import com.sg.classrosterservicelayer.servicelayer.ClassRosterPersistenceException;



/**
 *
 * @author stephenespinal
 */
public interface ClassRosterAuditDao {
        public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;

}