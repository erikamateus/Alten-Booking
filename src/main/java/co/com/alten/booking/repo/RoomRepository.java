package co.com.alten.booking.repo;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import co.com.alten.booking.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	

	@Query(value = "SELECT  idRoom, numberRoom FROM room  where status <> 'BUSY'", nativeQuery = true)
	List<Room> findRoomAvailable();

	@Query(value = "SELECT  idRoom, numberRoom FROM room  ", nativeQuery = true)
	List<Room> getAllRoom();
	
	@Query(value = "SELECT  idRoom room  where status <> 'BUSY' AND numberRoom = ?", nativeQuery = true)
	Integer findRoom(Integer numberRoom);

}
