package layeredarchitectureexample.ui;

import java.util.Scanner;

import layeredarchitectureexample.exceptions.CustomerNotFoundException;
import layeredarchitectureexample.model.Customer;
import layeredarchitectureexample.service.CustomerService;
import layeredarchitectureexample.service.CustomerServiceImpl;

public class Driver {

	public static void main(String[] args) {
		//Created an object of customer class
		Customer cust = new Customer();
		
		Scanner sc = new Scanner(System.in);
		//Reading input and setting it to customer class object
		cust.setCustId(sc.nextInt());
		cust.setCustName(sc.next());
		
		//Save the data to base
		//To save customer data need to call service layer
		CustomerService customerService = new CustomerServiceImpl();
		String message = customerService.addCustomer(cust);
		System.out.println(message);
		try {
			Customer cust1 = customerService.getCustomerById(2);
			System.out.println(cust1);
		} catch (CustomerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
