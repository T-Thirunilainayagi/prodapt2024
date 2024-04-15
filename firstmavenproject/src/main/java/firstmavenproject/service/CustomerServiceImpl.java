package firstmavenproject.service;

import java.util.Optional;

import firstmavenproject.dao.CustomerDAO;
import firstmavenproject.dao.CustomerDAOImpl;
import firstmavenproject.exception.CustomerNotFoundException;
import firstmavenproject.model.Customer;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public String addCustomer(Customer customer) {
		CustomerDAO custDAO = new CustomerDAOImpl();
		/*
		 * String message = custDAO.addCustomer(customer); 
		 * return message;
		 */
		return custDAO.addCustomer(customer);
	}

	@Override
	public Optional<Customer> getCustomerById(Integer customerId) throws CustomerNotFoundException {
		CustomerDAO custDAO = new CustomerDAOImpl();
		return custDAO.getCustomerById(customerId);
	}

	@Override
	public String updateCustomer(Customer customer) {
		CustomerDAO custDAO = new CustomerDAOImpl();
		return custDAO.updateCustomer(customer);
	}

	@Override
	public Optional<String> deleteCustomer(Integer customerId) throws CustomerNotFoundException {
		CustomerDAO custDAO = new CustomerDAOImpl();
		return custDAO.deleteCustomer(customerId);
	}

}
