package usermicroservice.controllers;

import usermicroservice.models.User;
import usermicroservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController (UserService userService){
        this.userService = userService;
    }

    @GetMapping("api/users")
    ResponseEntity<List<User>> returningAllUsers (){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}
