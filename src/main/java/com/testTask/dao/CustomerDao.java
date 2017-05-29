package com.testTask.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.testTask.model.Customer;

public interface CustomerDao {
	
	public List<Customer> getCustomers() throws SQLException;
	public boolean addCustomer(String firstName, String lastName, Date dateOfBirth, String username, String password) throws SQLException;
	public boolean changeCustomerData(int id,String firstName, String lastName, Date dateOfBirth, String username, String password) throws SQLException;
	public Customer getCustomerById(int id) throws SQLException;
	public boolean deleteCustomer(int id) throws SQLException;

}
