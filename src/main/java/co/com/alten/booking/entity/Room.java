package co.com.alten.booking.entity;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="room")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRoom;
	
	@Column(name = "numberRoom")
	private int numberRoom;
	
	@Column(name = "StatusRoom")
	private String StatusRoom;
	
	@OneToMany(mappedBy = "room")
	private Set<Booking> booking;

}
