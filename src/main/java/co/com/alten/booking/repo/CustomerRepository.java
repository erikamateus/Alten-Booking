package co.com.alten.booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.alten.booking.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "SELECT id_Customer,document_Customer,  name_Customer,email_Customer  FROM customer c  where c.document_Customer = ?", nativeQuery = true)
	Integer findCustomerDoc(String documentCustomer);

}
