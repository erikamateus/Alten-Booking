package co.com.alten.booking.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<RoomAvailable> findAvailableRoom(Integer room, LocalDate startDate, LocalDate endDate) {
		
		
	List<Booking> reservedDates = bookingService.findAvailableDates(room, "", "");
		
		
		/*List<RoomAvailable> addListaRoom = new ArrayList<>() ;
		
	
		for (Booking listBusyBooking : reservedDates) {
			
			//RoomAvailable  addRoomAvailable = RoomAvailable.builder().dateAvailable(listBusyBooking.getCheckIn()).numberRoom(listBusyBooking.getRoom().getNumberRoom()).build();
			
			
	       // List<Integer> excludedDays = Arrays.asList(16, 30);
			

	        // Recorrer todos los días del mes y mostrar los días terminados en pares, excluyendo los días en la lista de exclusión
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	        
	        for (int i = 1; i <= startDate.lengthOfMonth(); i++) {
	        	
	            LocalDate date = LocalDate.of(year, month, i);
	            
	            int day = date.getDayOfMonth();
	            if (day % 2 == 0 && !reservedDates.contains(day)) {
	                String formattedDate = date.format(formatter);
	                System.out.println(formattedDate);
	                
	                reservedDates.add(addRoomAvailable);
	            }
	        }

	        
			
		}
		*/
		
		
		 
		return null;
	}

	@Override
	public void createBooking(Booking booking) {

		bookingService.save(booking);
	}

}
