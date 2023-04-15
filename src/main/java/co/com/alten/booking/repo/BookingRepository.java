package co.com.alten.booking.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.alten.booking.dto.RoomAvailable;
import co.com.alten.booking.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query(value = "SELECT b.room, b.fi FROM booking b  where b.status <> 'BUSY'", nativeQuery = true)
	List<Booking> findBookingAvailable1();

	// cancular los dias que hay en un rango de fechas

	@Query(value = "WITH RECURSIVE dates(fecha) AS (\n" + "  SELECT ?1 AS fecha\n" + "  UNION ALL\n"
			+ "  SELECT DATEADD('DAY', 1, fecha) FROM dates WHERE fecha < ?2\n" + ")\n"
			+ "SELECT * FROM dates", nativeQuery = true)
	List<Date> getdaterange(Date startDate, Date endDate);

	@Query(value = "SELECT b.room, b.fi FROM booking b  where b.status = 'BUSY'", nativeQuery = true)
	List<Booking> findBookingBusy();

	// mas de 3 dias
	@Query(value = "SELECT CASE WHEN DATEDIFF('DAY', :fechaInicio, :fechaFin) > 3 THEN TRUE ELSE FALSE END", nativeQuery = true)
	Boolean moreThantreeDays(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

	// no tener mas reservas

	@Query(value = "SELECT count(*) FROM booking b  where b.status = 'BUSY' and b.id_customer = ?", nativeQuery = true)
	Integer findBookingCustomer(Integer idCustomer);

	@Query("SELECT b.checkIn , b.checkOut  FROM Booking b " + "  WHERE  b.room = :roomId "
			+ "AND b.checkIn NOT BETWEEN :startDate AND :endDate "
	// "AND b.checkOut NOT BETWEEN :startDate AND :endDate " +
	// "AND (:startDate NOT BETWEEN b.start_date AND b.end_date " +
	// "OR :endDate NOT BETWEEN b.checkIn AND b.checkOut)"
	)
	List<Booking> findAvailableDates(@Param("roomId") Integer roomId, @Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	@Query("SELECT b.room, b.checkIn,  b.checkOut  FROM Booking b" + " WHERE  b.room   = :roomId "
			+ "AND b.checkIn BETWEEN :startDate AND :endDate ")
	List<Booking> existingBookings(@Param("roomId") Integer roomId, @Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

}
