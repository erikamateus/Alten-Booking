package co.com.alten.booking.service;

import java.util.List;
import co.com.alten.booking.entity.Room;

public interface RoomService {

	List<Room> getAllRoom();

// Hay dos caminos
	Room getRoomById(Integer idRoom);

	Integer findRoom(Integer numberRoom);

}
