package usermicroservice.controllers;

import org.springframework.web.bind.annotation.*;
import usermicroservice.models.User;
import usermicroservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    public UserController (UserService userService){
        this.userService = userService;
    }

    @GetMapping("/api/users")
    ResponseEntity<List<User>> gettingAllUsers (){
        try {
            return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/users/{email}")
    ResponseEntity<Optional<User>> gettingUserById(@PathVariable String email) {
        try {
            Optional<User> user = userService.findUserById(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/api/users")
    public ResponseEntity<User> creatingUser (@RequestBody User user ) {
        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
        }catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/api/users/{email}")
    public ResponseEntity<User> updatingUser (@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping ("/api/users/{email}")
    public void deletingUser(@PathVariable String email) {
        userService.deleteUser(email);
    }
}
