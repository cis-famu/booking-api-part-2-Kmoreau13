package edu.famu.hotelprodject.Controller;

import edu.famu.hotelprodject.Models.Hotel;
import edu.famu.hotelprodject.Models.User;
import edu.famu.hotelprodject.Service.HotelService;
import edu.famu.hotelprodject.Service.UserService;
import edu.famu.hotelprodject.Util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

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
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelservice.getAllHotels(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable String hotelId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelservice.getHotelById(hotelId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createHotel(@RequestBody Hotel hotel) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelservice.createNewHotel(hotel), null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(401).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));

        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }

@PostMapping ("/{hotelId}")
    public ResponseEntity<ApiResponse> updateHotel(@PathVariable(name = "hotelId") String id, @RequestBody Map<String, String> data) {
        try {
            hotelservice.updateHotel(id, data);
            return ResponseEntity.ok(new ApiResponse(true, "Hotel successfully updated", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }





}
