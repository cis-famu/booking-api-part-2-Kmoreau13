package edu.famu.hotelprodject.Controller;

import edu.famu.hotelprodject.Service.RoomService;
import edu.famu.hotelprodject.Service.UserService;
import edu.famu.hotelprodject.Util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
