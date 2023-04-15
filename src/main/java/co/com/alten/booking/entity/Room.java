package co.com.alten.booking.entity;

import java.util.Date;
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
@Table(name = "room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRoom;

	@Column(name = "numberRoom")
	private Integer numberRoom;

	@Column(name = "statusRoom")
	private String statusRoom;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "room")
	private Set<Booking> booking;

}
