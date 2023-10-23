package edu.famu.hotelprodject.Controller;


import edu.famu.hotelprodject.Service.UserService;
import edu.famu.hotelprodject.Util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userservice;

    public UserController(UserService userService) {
        this.userservice = userService;

    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", userservice.getAllUsers(),null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", userservice.getUserById(userId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred",null, e.getMessage()));
        }
    }
}
