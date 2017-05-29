package com.testTask.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.testTask.model.Customer;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	
    @Autowired
    public DataSource DataSource;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() 
    {
        return namedParameterJdbcTemplate;
    }


	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public CustomerDaoImpl () {

	}

	@Override
	public List<Customer> getCustomers() throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		
		String sql = "SELECT * FROM Customer";
        List<Customer> result = namedParameterJdbcTemplate.query(sql, params, new CustomerMapper());
        return result;
	}
	

	@Override
	public boolean addCustomer(String firstName, String lastName, Date dateOfBirth, String username, String password)
			throws SQLException {
	      String SQL = "INSERT INTO Customer (FirstName, LastName, DateOfBirth, Username, Password) VALUES (:FirstName, :LastName, :DateOfBirth, :Username, :Password)";
	      Map namedParameters = new HashMap();
	      namedParameters.put("FirstName", firstName);   
	      namedParameters.put("LastName", lastName);
	      namedParameters.put("DateOfBirth", dateOfBirth);
	      
	      namedParameters.put("Username", username);
	      namedParameters.put("Password", password);
	      namedParameterJdbcTemplate.update(SQL, namedParameters);
	      return true;
	}

	@Override
	public boolean changeCustomerData(int id, String firstName, String lastName, Date dateOfBirth, String username,
			String password) throws SQLException {
	      String SQL = "UPDATE Customer  SET FirstName=:FirstName, LastName=:LastName, DateOfBirth=:DateOfBirth, Username=:Username, Password=:Password WHERE ID=:Id";
	      
	      
	      Map namedParameters = new HashMap();
	      namedParameters.put("FirstName", firstName);   
	      namedParameters.put("LastName", lastName);
	      namedParameters.put("DateOfBirth", dateOfBirth);
	      
	      namedParameters.put("Username", username);
	      namedParameters.put("Password", password);
	      namedParameters.put("Id",id);
	      namedParameterJdbcTemplate.update(SQL, namedParameters);
	      return true;
	}

	@Override
	public Customer getCustomerById(int id) throws SQLException {

		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM CUSTOMER WHERE ID ="+id;
        Customer result = namedParameterJdbcTemplate.queryForObject(sql, params, new CustomerMapper());
        return result;


	}
	
	private static final class CustomerMapper implements RowMapper<Customer> {

		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setId(rs.getInt("id"));
			customer.setFirstName(rs.getString("FirstName"));
			customer.setLastName(rs.getString("LastName"));
			customer.setDateOfBirth(rs.getDate("DateOfBirth"));
			customer.setUsername(rs.getString("Username"));
			customer.setPassword(rs.getString("Password"));
			return customer;
		}
	}

	@Override
	public boolean deleteCustomer(int id) throws SQLException {
		  String SQL = "DELETE FROM CUSTOMER WHERE Id = :Id";
	      Map namedParameters = new HashMap();
	      namedParameters.put("Id", id);   
		  namedParameterJdbcTemplate.update(SQL, namedParameters);
		return true;
	}
}
