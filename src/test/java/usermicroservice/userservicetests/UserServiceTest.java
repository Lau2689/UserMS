package usermicroservice.userservicetests;

import usermicroservice.exceptions.BadArgumentsException;
import usermicroservice.exceptions.ResourceNotFoundException;
import usermicroservice.models.User;
import usermicroservice.repositories.UserRepository;
import usermicroservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private ResourceNotFoundException resourceNotFoundException;
    private BadArgumentsException badArgumentsException ;


    @BeforeEach
    void setUp(){
        userService = new UserService(userRepository);
    }

    @DisplayName("Test to verfify the FindAll")
    @Test
    void givenCreatedUserThenReturnAllUsers() {
        //GIVEN
        given(userRepository.findAll()).willReturn(Arrays.asList(getMockedUser("laura@gmail.com")));

        //WHEN
        var result = userService.findAllUsers();

        //THEN
        assertThat(result).isNotNull();
        assertThat(result.size()).isGreaterThan(0);
    }

    @DisplayName("Test for finding a user by the id which is email")
    @Test
    public void givenAUserIdThenReturnUser(){
        //GIVEN
        given (userRepository.findById("laura@hotmail.com")).willReturn(Optional.ofNullable(getMockedUser("laura@hotmail.com")));

        //WHEN
        var result = userService.findUserById("laura@hotmail.com");

        //THEN
        assertThat(result.map(User::getEmail).equals("laura@hotmail.com"));
    }

    @DisplayName("Test for creating a new user")
    @Test
    public void givenAUserObjectThenCreateANewUser(){
        //GIVEN
        given(userRepository.save(any())).willReturn(getMockedUser("laura@gmail.com"));

        //WHEN
        var result = userService.createUser(getMockedUser("laura@gmail.com"));

        //THEN
        assertThat(result.getEmail()).isEqualTo("laura@gmail.com");
        assertThat(result.getName()).isEqualTo("Laura");
        assertThat(result.getLastName()).isEqualTo("García");
        assertThat(result.getFidelityPoints()).isNull();
    }

    @DisplayName("Test for updating a user")
    @Test
    public void givenSomeInfoTheUpdateUser(){

        //GIVEN
        User databaseStoredUser = getMockedUser("laura@gmail.com");
        given(userRepository.save(any())).willReturn(databaseStoredUser);


        //WHEN
        databaseStoredUser.setCity("Madrid");
        var result = userService.updateUser(databaseStoredUser);

        //THEN
        assertThat(result.getEmail()).isEqualTo("laura@gmail.com");
        assertThat(result.getCity()).isEqualTo("Madrid");
        assertThat(result.getName()).isEqualTo("Laura");
        assertThat(result.getPaymentMethod()).isNotNull();

    }

    @DisplayName("Test for deliting a  user")
    @Test
    public void givenTheIdThenDeleteUser() {

        //GIVEN
        userRepository.save(any());

        //WHEN
        userService.deleteUser(getMockedUser("laura@gmail.com").getEmail());
        var result = userRepository.findById(getMockedUser("laura@gmail.com").getEmail());

        //THEN
        assertThat(result).isEmpty();
    }

    @DisplayName("Test for finding a user by the name")
    @Test
    public void givenAUserNameThenReturnUser(){
        //GIVEN
        List <User> usersWithSameName = new ArrayList<>();
        usersWithSameName.add(getMockedUser("laura@gmail.com"));
        given(userRepository.findByName("Laura")).willReturn(usersWithSameName);

        //WHEN
        var result = userService.findUserByName("Laura");

        //THEN
        assertThat(result.size()).isEqualTo(1);
    }



    @DisplayName("Testing findAllUsers Exception")
    @Test
    void havingNoUserThenReturnNotFoundException() {
        //GIVEN
        List<User> allUsers = new ArrayList<>();
        given(userRepository.findAll()).willReturn(allUsers);

        //WHEN
        allUsers.isEmpty();

        //THEN
        assertThatThrownBy(() -> userService.findAllUsers()).hasCause(resourceNotFoundException);
    }
    @DisplayName("Testin findUserById Exception ")
    @Test
    public void givenAnInvalidUserIdThenReturnNotFoundExceptionr() {
        //GIVEN

        given (userRepository.findById("laura@hotmail.com")).willReturn(Optional.ofNullable(getMockedUser("laura@hotmail.com")));

        //WHEN
        String userId = "lau@gmail.com";

        //THEN
        assertThatThrownBy(() -> userService.findUserById(userId)).hasCause(resourceNotFoundException);
    }

    //MIRARLO BIEN!!! MIRAR BIEN EL SERVICE
    /*@DisplayName("Testing createUser Exception")
    @Test
    public void givenAnExistingUserThenReturnBadArgumentsException() {
        //GIVEN

        User existingUser = userService.createUser(getMockedUser("laura@gmail.com"));
        given (userRepository.findById("laura@gmail.com")).willReturn(Optional.ofNullable(existingUser));

        //WHEN
        User user= getMockedUser2("laura@gmail.com");


        //THEN
        assertThatThrownBy(() -> userRepository.save().hasCause(badArgumentsException);
    }*/

    @DisplayName("Testin findUserByName Exception ")
    @Test
    public void givenANonExistingNameThenReturnNotFoundExceptionr() {
        //GIVEN

        List <User> usersWithSameName = new ArrayList<>();
        usersWithSameName.add(getMockedUser("laura@gmail.com"));
        given(userRepository.findByName("Laura")).willReturn(usersWithSameName);

        //WHEN
        String userName = "Nuria";

        //THEN
        assertThatThrownBy(() -> userService.findUserByName(userName)).hasCause(resourceNotFoundException);
    }

    private User getMockedUser(String email){
        return User.builder()
                .email(email)
                .name("Laura")
                .lastName("García")
                .city("Asturias")
                .paymentMethod("Paypal")
                .build();
    }
    private User getMockedUser2(String email){
        return User.builder()
                .email(email)
                .name("Laura")
                .lastName("García")
                .city("Asturias")
                .paymentMethod("Paypal")
                .build();
    }



}
