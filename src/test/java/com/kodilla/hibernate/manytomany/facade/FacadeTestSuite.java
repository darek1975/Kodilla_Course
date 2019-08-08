package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacadeTestSuite {
    @Autowired
    Facade facade;
    @Autowired
    CompanyDao companyDao;
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testFindCompanies() {
        //given
        Company assecoPoland = new Company("Asseco Poland");
        Company comarch = new Company("Comarch");
        Company softarch = new Company("Softarch");

        //when
        companyDao.save(assecoPoland);
        companyDao.save(comarch);
        companyDao.save(softarch);
        List<Company> companies = facade.findCompanies("arch");

        //then
        Assert.assertEquals(2, companies.size());

        //cleanup
        try {
            companyDao.delete(assecoPoland);
            companyDao.delete(comarch);
            companyDao.delete(softarch);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testFindEmployees() {
        //Given
        Employee johnKeneddy = new Employee("John", "Keneddy");
        Employee ronaldRegan = new Employee("Ronald", "Regan");
        Employee kevinMakegan = new Employee("Kevin", "Makegan");

        //when
        employeeDao.save(johnKeneddy);
        employeeDao.save(ronaldRegan);
        employeeDao.save(kevinMakegan);
        List<Employee> employees = facade.findEmployees("egan");

        //then
        Assert.assertEquals(2, employees.size());

        //cleanup
        try {
            employeeDao.delete(johnKeneddy);
            employeeDao.delete(ronaldRegan);
            employeeDao.delete(kevinMakegan);
        } catch (Exception e) {
            //do nothing
        }
    }

}
