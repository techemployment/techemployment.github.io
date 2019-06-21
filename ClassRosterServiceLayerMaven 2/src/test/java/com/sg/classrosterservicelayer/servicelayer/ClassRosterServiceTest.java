/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classrosterservicelayer.servicelayer;

import com.sg.classrosterservicelayer.DAO.ClassRosterAuditDao;
import com.sg.classrosterservicelayer.DAO.ClassRosterAuditStubDaoImpl;
import com.sg.classrosterservicelayer.DAO.ClassRosterDAO;
import com.sg.classrosterservicelayer.DAO.ClassRosterDaoStubImpl;
import com.sg.classrosterservicelayer.DTO.Student;
import com.sg.classrosterservicelayer.controller.ClassRosterController;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author acetip
 */
public class ClassRosterServiceTest {

    private ClassRosterService service;

    public ClassRosterServiceTest() {
        //using the stubs here
//        ClassRosterDAO dao = new ClassRosterDaoStubImpl();
//        ClassRosterAuditDao auditDao = new ClassRosterAuditStubDaoImpl();
//
//        service = new ClassRosterServiceImpl(dao, auditDao);

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", ClassRosterServiceImpl.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createStudent method, of class ClassRosterService.
     */
    @Test
    public void testCreateStudent() throws Exception {

        //use another student not in stub impl
        Student student = new Student("0002");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");
        service.createStudent(student);

        //we use the exception to assert for a test pass
    }

    @Test
    public void testCreateStudentDuplicateId() throws Exception {
        Student student = new Student("0001");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");

        //we want to see if it throws an exception
        //so if we catch the right exception it will return for "test passed"
        try {
            service.createStudent(student);
            //if we get to this line then we failed the test so call to fail and put explanation
            fail("Expected ClassRosterDuplicateIdException was not thrown.");
        } catch (ClassRosterDuplicateIdException e) {
            return;
        }
    }

    @Test
    public void testCreateStudentInvalidData() throws Exception {
        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");

        try {
            service.createStudent(student);
            fail("Expected ClassRosterDataValidationException was not thrown.");
        } catch (ClassRosterDataValidationException e) {
            return;
        }

    }

    /**
     * Test of getAllStudents method, of class ClassRosterService.
     */
    @Test
    public void testGetAllStudents() throws Exception {
        assertEquals(1, service.getAllStudents().size());
    }

    /**
     * Test of getStudent method, of class ClassRosterService.
     */
    @Test
    public void testGetStudent() throws Exception {
        Student student = service.getStudent("0001");
        assertNotNull(student);

        //bogus one to check if null
        student = service.getStudent("9999");
        assertNull(student);

    }

    @Test
    public void testRemoveStudent() throws Exception {

        //not testing if this is persisting thats the dao test
        //this we testing pass through methods are doing what they are supposed to be doing
        Student student = service.removeStudent("0001");
        assertNotNull(student);

        //bogus one to check if null
        student = service.removeStudent("9999");
        assertNull(student);

    }
}
