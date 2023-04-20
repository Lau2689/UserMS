package usermicroservice.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import usermicroservice.models.User;
import usermicroservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
