package co.com.alten.booking.service;

import java.time.LocalDate;
import java.util.List;

import co.com.alten.booking.dto.RoomAvailable;
import co.com.alten.booking.entity.Booking;

public interface BookingService {
	List<Booking> getAllBooking();
	
    public List<RoomAvailable> findAvailableRoom(Integer roomId, LocalDate startDate, LocalDate endDate);
    public void createBooking(Booking booking);

}
