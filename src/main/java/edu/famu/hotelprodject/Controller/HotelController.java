package edu.famu.hotelprodject.Controller;

import edu.famu.hotelprodject.Service.HotelService;
import edu.famu.hotelprodject.Service.UserService;
import edu.famu.hotelprodject.Util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelservice;

    public HotelController(HotelService hotelservice) {
        this.hotelservice = hotelservice;

    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllHotels() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelservice.getAllHotels(),null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable String hotelId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelservice.getHotelById(hotelId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred",null, e.getMessage()));
        }
    }


}
