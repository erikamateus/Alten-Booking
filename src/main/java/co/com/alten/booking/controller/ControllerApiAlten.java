package co.com.alten.booking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.alten.booking.entity.Booking;
import co.com.alten.booking.response.ResponseHTTP;
import co.com.alten.booking.service.BookingService;
import co.com.alten.booking.service.CustomerService;
import co.com.alten.booking.service.RoomService;

@RestController
@RequestMapping("/Booking")
public class ControllerApiAlten {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private CustomerService customerService;

	@GetMapping("/{roomId}/available-dates")
	public ResponseEntity<ResponseHTTP> getAvailableRoom(@PathVariable Integer numberRoom,
			@PathVariable String documentCustomer,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

		List<Booking> availableDates = new ArrayList<>();
		Integer roomId = null;
		Integer idCustomer = null;

		try {

			roomId = roomService.findRoom(numberRoom);
			idCustomer = customerService.findCustomerDoc(documentCustomer);
			bookingService.findBookingCustomer(idCustomer);
			availableDates = bookingService.findAvailableRoom(roomId, startDate, endDate);

		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseHTTP(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return !availableDates.isEmpty()
				? new ResponseEntity<>(new ResponseHTTP(HttpStatus.OK.value(), availableDates), HttpStatus.OK)
				: new ResponseEntity<>(new ResponseHTTP(HttpStatus.NOT_FOUND.value(), null), HttpStatus.NOT_FOUND);
	}

	@PostMapping("/create-Update")
	public ResponseEntity<ResponseHTTP> createUpdateBooking(@RequestBody Booking booking) {

		Booking savedBooking = null;
		try {
			savedBooking = bookingService.createUpdateBooking(booking);

		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseHTTP(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return savedBooking != null
				? new ResponseEntity<>(new ResponseHTTP(HttpStatus.OK.value(), savedBooking), HttpStatus.OK)
				: new ResponseEntity<>(new ResponseHTTP(HttpStatus.NOT_FOUND.value(), null), HttpStatus.NOT_FOUND);
	}

}
