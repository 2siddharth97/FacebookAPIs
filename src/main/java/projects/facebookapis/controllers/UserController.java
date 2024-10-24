package projects.facebookapis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.facebookapis.models.User;
import projects.facebookapis.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestParam String username, @RequestParam String password) {
        try {
            User user = userService.createUser(username, password);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            String message = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @PostMapping("/follow/{user_id}")
    public ResponseEntity followUser(@PathVariable String user_id, @RequestParam String username) {
        try {
            userService.followUser(username, user_id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("user '" + user_id + "' has been followed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/unfollow/{user_id}")
    public ResponseEntity unfollowUser(@PathVariable String user_id, @RequestParam String username) {
        try {
            userService.unfollowUser(username, user_id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("user '" + user_id + "' has been unfollowed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
