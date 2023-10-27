package edu.famu.hotelprodject.Controller;


import edu.famu.hotelprodject.Models.User;
import edu.famu.hotelprodject.Service.UserService;
import edu.famu.hotelprodject.Util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

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

   @PostMapping
    public ResponseEntity<ApiResponse> createNewUser(User user) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", userservice.createNewUser(user), null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(401).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));

        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }

    public ResponseEntity<ApiResponse> updateUser(@PathVariable(name = "userID") String id, @RequestBody Map<String, String> data) {
        try {
            userservice.updateUser(id, data);
            return ResponseEntity.ok(new ApiResponse(true, "User successfully updated", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }

}
