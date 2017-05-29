package com.testTask.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.testTask.dao.CustomerDaoImpl;
import com.testTask.form.CustomerChangeForm;
import com.testTask.form.CustomerForm;
import com.testTask.model.Customer;
import com.testTask.validator.CustomerValidator;

@Controller
public class MainController {

		@Autowired
		private CustomerDaoImpl service;
		
		@RequestMapping(value="/",method=RequestMethod.GET)
		public ModelAndView displayCustomers(HttpServletRequest request, HttpServletResponse response) throws SQLException
		{
	        ModelAndView model = new ModelAndView("customers");
	        List<Customer> customers = service.getCustomers();
	        request.setAttribute("customers", customers);
	        return model;
		}
		
		
		
		@RequestMapping(value="/add",method=RequestMethod.GET)
		public ModelAndView displayAdd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("customerAddForm") CustomerForm customerAddForm)
		{
			ModelAndView model = new ModelAndView("addForm");
			model.addObject("customerAddForm",customerAddForm);
			return model;
		}
		
		
		
		@RequestMapping(value="/add",method=RequestMethod.POST)
		public ModelAndView executeAdd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("customerAddForm") CustomerForm customerAddForm, BindingResult result) throws SQLException
		{
			ModelAndView model = new ModelAndView("addForm");
			model.addObject("customerAddForm",customerAddForm);
			CustomerValidator customerValidator = new CustomerValidator();
			customerValidator.validate(customerAddForm, result);

			if(result.hasErrors()){
	            return model;
	        }

	        String success = "Customer added";
	        service.addCustomer(customerAddForm.getFirstName(), customerAddForm.getLastName(), customerAddForm.getDateOfBirth(), customerAddForm.getUsername(), customerAddForm.getPassword());
			request.setAttribute("successfulMessage", success);
			return model;
		}
		
		
		@RequestMapping(value="/edit",method=RequestMethod.GET)
		public ModelAndView displayChange(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("changeForm")CustomerForm changeForm, @RequestParam(value="id", required= false) Integer id, @RequestParam(value= "delete", required=false) boolean delete) throws SQLException
		{
			ModelAndView model = new ModelAndView("changeForm");
			if(delete && id!=null){
				service.deleteCustomer(id);
				String success = "Data deleted";
				request.setAttribute("successfulMessage", success);
			}
			
			if(id!=null){
				if(!delete){
					Customer customer =service.getCustomerById(id);
					model.addObject("changeForm",changeForm);
					request.setAttribute("customer", customer);
				}
			}
			return model;

		}
		
		@RequestMapping(value="/edit",method=RequestMethod.POST)
		public ModelAndView executeChange(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("changeForm")CustomerChangeForm changeForm, BindingResult result) throws SQLException
		{
			ModelAndView model = new ModelAndView("changeForm");
			model.addObject("changeForm",changeForm);
			CustomerValidator customerValidator = new CustomerValidator();
			customerValidator.validate(changeForm, result);
			
			Customer customer =new  Customer(changeForm.getId(),changeForm.getFirstName(),changeForm.getLastName(),
					changeForm.getDateOfBirth(),changeForm.getUsername(), changeForm.getPassword());
			request.setAttribute("customer", customer);
			

			if(result.hasErrors()){
	            return model;
	        }

	        String success = "Data changed";
	        service.changeCustomerData(changeForm.getId(), changeForm.getFirstName(), changeForm.getLastName(), changeForm.getDateOfBirth(), changeForm.getUsername(), changeForm.getPassword());
			request.setAttribute("successfulMessage", success);
			return model;
		}
		
		
		
		 @InitBinder
		    public void initBinder(WebDataBinder binder) {
		        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		        sdf.setLenient(true);
		        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		    }
		
}
