package co.com.alten.booking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.alten.booking.dto.RoomAvailable;
import co.com.alten.booking.entity.Booking;
import co.com.alten.booking.service.ApiConfigurationService;
import co.com.alten.booking.service.BookingService;
import co.com.alten.booking.service.CustomerService;
import co.com.alten.booking.service.RoomService;







@RestController
public class ControllerApiAlten {
	@Autowired
	private ApiConfigurationService apiConfigurationService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private CustomerService customerservice;
	@Autowired

	private RoomService roomService;

	// primer servicio : Mostrar habitaciones diponibles <= 30 (sin metodo de
	// entrada)
	// segundo: condicion que la estadia debe ser entre 1 y 3 dias
	// Tercero: No tener mas reservas asignadas el huesped
	// Cuarto: Insertar en la tabla reserva y cambiar de estado la habitacion
	@GetMapping("/{roomId}/available-dates")
	public ResponseEntity<List<RoomAvailable>> getAvailableRoom(@PathVariable Integer roomId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

		List<RoomAvailable> availableDates = bookingService.findAvailableRoom(roomId, startDate, endDate);
		return ResponseEntity.ok(availableDates);

	}

	@GetMapping("/{roomId}/edit-room")

	public ResponseEntity<String> updateRoom(@RequestBody Booking booking) {

		bookingService.createBooking(booking);
		return ResponseEntity.ok("Great: You hava a reservations!! congrat!");

	}

}
