package co.com.alten.booking.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.alten.booking.dto.RoomAvailable;
import co.com.alten.booking.entity.Booking;
import co.com.alten.booking.entity.Customer;
import co.com.alten.booking.entity.Room;
import co.com.alten.booking.repo.BookingRepository;
import co.com.alten.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingService;

	public List<Booking> getAllBooking() {
		return bookingService.findAll();
	}

	public Booking getApiConfigurationById(Integer idBooking) {
		return bookingService.findById(idBooking).orElse(null);
	}

	public void saveBooking(Booking booking) {
		bookingService.save(booking);
	}

	public void deleteApiConfiguration(Integer idBooking) {
		bookingService.deleteById(idBooking);
	}

	@Override
	public List<Booking> findAvailableRoom(Integer room, LocalDate startDate, LocalDate endDate) {

		// conditions
		List<Booking> reservedDates = new ArrayList<>();
		List<Booking> excludedDays = new ArrayList<>();

		if (dateRangeValidation(startDate, endDate)) {
			// make a list with 30 days
			LocalDate todayDate = LocalDate.now();
			LocalDate endDate30Days = todayDate.plusDays(30);
			List<Booking> existDatesBoookings = bookingService.findAvailableDates(room, startDate, endDate30Days);
			if (!isAvailableRoom(room, startDate, endDate)) {
				for (Booking BusyBooking : existDatesBoookings) {
					if (isAvailableRoom(BusyBooking.getRoom().getIdRoom(),
							convertDateToLocaldate(BusyBooking.getCheckIn()),
							convertDateToLocaldate(BusyBooking.getCheckOut()))) {
						// lleno la lista con los que no se pueden
						excludedDays.add(BusyBooking);
					} else {
						reservedDates.add(BusyBooking);
						// lleno la lista con los que SI se pueden
					}
				}
			} else {
				throw new RuntimeException("Room is not available for the requested dates");
			}

		}

		return reservedDates;

	}

	private boolean isAvailableRoom(Integer room, LocalDate startDate, LocalDate endDate) {
		// busco si existe el dia con los parametros
		List<Booking> existDatesBoookings = bookingService.existingBookings(room, startDate, endDate);
		return existDatesBoookings.isEmpty();
		// validar entrada de datos. no mas de 3 dias en reservar, y no mayor a 30 dias
		// los dias que puede seleccionar

	}

	@Override
	public Booking createUpdateBooking(Booking booking) {

		if (!isAvailableRoom(booking.getRoom().getIdRoom(), convertDateToLocaldate(booking.getCheckIn()),
				convertDateToLocaldate(booking.getCheckOut()))) {
			throw new RuntimeException("Room is not available for the requested dates");
		}

		bookingService.save(booking);
		return booking;
	}

	@Override
	public boolean dateRangeValidation(LocalDate startDate, LocalDate endDate) {

		// Busco que no sea superior a 30 dias la fecha inicial
		if (startDate.isBefore(LocalDate.now().plusDays(1)) || startDate.isAfter(LocalDate.now().plusDays(30))) {

			throw new RuntimeException("Reservation date must be between tomorrow and 30 days from now");
		}
		// Busco que no sea superior a 3 dias la reserva la final de la inicial
		else if (endDate.isAfter(startDate.plusDays(3))) {

			throw new RuntimeException("Reservation must be 3 days from " + startDate);

		}
		// Busco que no sea superior a 30 dias la reserva
		else if (endDate.isAfter(LocalDate.now().plusDays(30))) {

			throw new RuntimeException("Reservation must be in 30 days from " + startDate);

		}

		return true;

	}

	public LocalDate convertDateToLocaldate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public void findBookingCustomer(Integer idCustomer) {
		Integer idfndCustomer = null;

		if (idCustomer != null) {

			idfndCustomer = bookingService.findBookingCustomer(idCustomer);

			if (idfndCustomer > 0) {
				throw new RuntimeException("The Customer  has an active Booking. ");
			}
		} else {
			throw new RuntimeException("Create the Customer.");

		}

	}
}
