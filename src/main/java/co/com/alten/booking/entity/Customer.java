package co.com.alten.booking.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCustomer;

	@Column(name = "documentCustomer")
	private String documentCustomer;

	@Column(name = "nameCustomer")
	private String nameCustomer;

	@Column(name = "emailCustomer")
	private String emailCustomer;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "customer")
	private Set<Booking> booking;
}
