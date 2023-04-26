package usermicroservice.controllers;
import org.springframework.web.bind.annotation.*;
import usermicroservice.models.User;
import usermicroservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;


    public UserController (UserService userService){
        this.userService = userService;
    }

    @GetMapping("/api/users")
     public ResponseEntity<List<User>> gettingAllUsers (){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/api/users/{email}")
    public ResponseEntity<Optional<User>> gettingUserById(@PathVariable String email) {
        return new ResponseEntity<>(userService.findUserById(email), HttpStatus.OK);
    }


    @PostMapping("/api/users")
    public ResponseEntity<User> creatingUser (@Valid @RequestBody User user ) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/api/users/{email}")
    public ResponseEntity<User> updatingUser (@RequestBody User user) {
       return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping ("/api/users/{email}")
    public void deletingUser(@PathVariable String email) {
        userService.deleteUser(email);
    }

    @GetMapping("/api/users/usersbyname/{name}")
    public ResponseEntity<List<User>> findingUserByName (@PathVariable ("name") String name) {
            return new ResponseEntity<>(userService.findUserByName(name), HttpStatus.OK);
    }
}
