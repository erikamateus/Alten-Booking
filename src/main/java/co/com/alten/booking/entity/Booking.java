package co.com.alten.booking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBooking;

	@ManyToOne
	@JoinColumn(name = "idRoom")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "idCustomer")
	private Customer customer;

	@Column(name = "checkIn")
	private Date checkIn;

	@Column(name = "checkOut")
	private Date checkOut;

	@Column(name = "bookingDay")
	private Date bookingDay;

	@Column(name = "status")
	private String status;

}
