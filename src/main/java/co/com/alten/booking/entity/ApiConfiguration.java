package co.com.alten.booking.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="apiconfiguration")
public class ApiConfiguration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int	idApi;	
	
	@Column(name = "nameApi")
private String	nameApi;
	
	@Column(name = "detail")
private String detail;
	
	@Column(name = "value")
private String value;
}
