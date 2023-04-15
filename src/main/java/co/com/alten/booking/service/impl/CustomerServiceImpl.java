package co.com.alten.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.alten.booking.entity.Customer;
import co.com.alten.booking.service.CustomerService;
import co.com.alten.booking.repo.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerrService;

	public List<Customer> getAllCustomer() {
		return customerrService.findAll();
	}

	public Customer getApiConfigurationById(Integer idCustomer) {
		return customerrService.findById(idCustomer).orElse(null);
	}

	public void saveCustomer(Customer apiConfiguration) {
		customerrService.save(apiConfiguration);
	}

	public void deleteCustomer(Integer idCustomer) {
		customerrService.deleteById(idCustomer);
	}

	public Integer findCustomerDoc(String documentCustomer) {
		Integer idCustomer = null;

		if (documentCustomer != null) {
			idCustomer = customerrService.findCustomerDoc(documentCustomer);

			if (idCustomer == null) {
				throw new RuntimeException(
						"Not Exist Customer with this ID:" + documentCustomer + "Please. Firts: Create the Customer");
			}
		} else {
			throw new RuntimeException("Please; to make a Booking we need the ID. Create the Customer");

		}
		return idCustomer;
	}

}
