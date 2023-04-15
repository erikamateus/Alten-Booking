package co.com.alten.booking.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.alten.booking.entity.Room;
import co.com.alten.booking.repo.RoomRepository;
import co.com.alten.booking.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomService;

	public List<Room> getAllRoom() {
		return roomService.findAll();
	}

	public Room getRoomById(Integer idRoom) {
		return roomService.findById(idRoom).orElse(null);
	}

	public void saveRoom(Room room) {
		roomService.save(room);
	}

	public void deleteRoom(Integer idRoom) {
		roomService.deleteById(idRoom);
	}

	public Integer findRoom(Integer numberRoom) {
		return roomService.findRoom(numberRoom);
	}

}
