package edu.famu.hotelprodject.Controller;

import edu.famu.hotelprodject.Models.Hotel;
import edu.famu.hotelprodject.Models.Room;
import edu.famu.hotelprodject.Service.HotelService;
import edu.famu.hotelprodject.Service.RoomService;
import edu.famu.hotelprodject.Service.UserService;
import edu.famu.hotelprodject.Util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/room")
public class RoomController {


    private final RoomService roomservice;

    public RoomController(RoomService roomservice) {
        this.roomservice = roomservice;

    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRooms() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", roomservice.getAllRooms(),null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @GetMapping("/{roomID}")
    public ResponseEntity<ApiResponse> getRoomById(@PathVariable String roomID) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", roomservice.getRoomById(roomID), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred",null, e.getMessage()));
        }
    }

    public ResponseEntity<ApiResponse> createRoom(Room room) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", RoomService.createNewRoom(room), null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(401).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));

        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }

    public ResponseEntity<ApiResponse> updateRoom(@PathVariable(name = "roomID") String id, @ResponseBody Map<String, String> data) {
        try {
            RoomService.updateRoom(id, data);
            return ResponseEntity.ok(new ApiResponse(true, "Room successfully updated", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }

}
