package co.com.alten.booking.service;

import java.util.List;
import co.com.alten.booking.entity.Customer;

public interface CustomerService {
	List<Customer> getAllCustomer();

	Integer findCustomerDoc(String documentCustomer);

}
