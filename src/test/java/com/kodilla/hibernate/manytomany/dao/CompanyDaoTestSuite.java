package com.kodilla.hibernate.manytomany.dao;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyDaoTestSuite {
    @Autowired
    CompanyDao companyDao;
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testNamedQueries() {
        //Given
        Employee johnSmith1 = new Employee("John", "Smith");
        Employee stephanieClarckson1 = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky1 = new Employee("Linda", "Kovalsky");

        Company softwareMachine1 = new Company("Software Machine");
        Company dataMaesters1 = new Company("Data Maesters");
        Company greyMatter1 = new Company("Grey Matter");

        softwareMachine1.getEmployees().add(johnSmith1);
        dataMaesters1.getEmployees().add(stephanieClarckson1);
        dataMaesters1.getEmployees().add(lindaKovalsky1);
        greyMatter1.getEmployees().add(johnSmith1);
        greyMatter1.getEmployees().add(lindaKovalsky1);

        johnSmith1.getCompanies().add(softwareMachine1);
        johnSmith1.getCompanies().add(greyMatter1);
        stephanieClarckson1.getCompanies().add(dataMaesters1);
        lindaKovalsky1.getCompanies().add(dataMaesters1);
        lindaKovalsky1.getCompanies().add(greyMatter1);

        companyDao.save(softwareMachine1);
        int softwareMachineId = softwareMachine1.getId();
        companyDao.save(dataMaesters1);
        int dataMaestersId = dataMaesters1.getId();
        companyDao.save(greyMatter1);
        int greyMatterId = greyMatter1.getId();

        //When
        List<Company> threeFirstLetters = companyDao.retrieveWithThreeFirstLetters("Sof");
        List<Employee> lastname = employeeDao.retrieveWithLastname("Smith");

        //Than
        try {
            Assert.assertEquals(1, threeFirstLetters.size());
            Assert.assertEquals(1, lastname.size());
            //CleanUp
            companyDao.deleteById(softwareMachineId);
            companyDao.deleteById(dataMaestersId);
            companyDao.deleteById(greyMatterId);
        } catch (Exception e) {
        }
    }
}
