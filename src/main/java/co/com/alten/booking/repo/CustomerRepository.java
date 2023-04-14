package co.com.alten.booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.alten.booking.entity.Customer;
@Repository
public interface CustomerRepository  extends JpaRepository <Customer,Integer> {
	
	
	@Query(value = "SELECT idCustomer,documentCustomer,  nameCustomer,emailCustomer  FROM customer c  where c.documentCustomer = ?", nativeQuery = true)
	Integer findCustomerDoc(String documentCustomer);

}
