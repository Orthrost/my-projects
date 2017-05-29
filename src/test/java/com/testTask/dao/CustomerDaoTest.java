package com.testTask.dao;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.testTask.model.Customer;

public class CustomerDaoTest {

    private EmbeddedDatabase db;

    
    @Before
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
    		.addScript("db/sql/create-db.sql")
    		.addScript("db/sql/insert-data.sql")
    		.build();
    }

    @Test
    public void testGetCustomerById() throws SQLException {
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    	CustomerDaoImpl customerDao = new CustomerDaoImpl();
    	customerDao.setNamedParameterJdbcTemplate(template);
    	
    	
    	
    	Customer customer = customerDao.getCustomerById(1);
  
    	Assert.assertNotNull(customer);
    	Assert.assertEquals("Mari", customer.getFirstName());
    	Assert.assertEquals("Maasikas", customer.getLastName());

    }
    
    
    @Test
    public void testChangeCustomerData() throws SQLException {
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    	CustomerDaoImpl customerDao = new CustomerDaoImpl();
    	customerDao.setNamedParameterJdbcTemplate(template);
    	
    	
    	// first name equals Kaarel
    	Customer customer = customerDao.getCustomerById(2);
    	
    	//changes the row where id equals 2
    	customerDao.changeCustomerData(customer.getId(), "Martin", customer.getLastName(),customer.getDateOfBirth(), customer.getUsername(), customer.getPassword());
    	
    	//Turns to the same row as customer after the change
    	Customer customerChanged = customerDao.getCustomerById(2);
  
    	Assert.assertNotNull(customer);
    	Assert.assertNotNull(customerChanged);
    	
    	Assert.assertEquals("Kaarel", customer.getFirstName());
    	Assert.assertEquals("Martin", customerChanged.getFirstName());

    }
    
    

    @After
    public void tearDown() {
        db.shutdown();
    }

}