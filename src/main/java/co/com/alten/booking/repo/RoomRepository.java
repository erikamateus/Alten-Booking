package co.com.alten.booking.repo;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import co.com.alten.booking.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	

	@Query(value = "SELECT  SUBSTRING(CAST(DAY(r.fi) AS VARCHAR(2)), 1, 2) dias   FROM room ", nativeQuery = true)
	List<Room> findRoomAvailable333();

}
