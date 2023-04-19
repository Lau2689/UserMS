package usermicroservice;

import usermicroservice.models.User;
import usermicroservice.repositories.UserRepository;
import usermicroservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private User user;

    @BeforeEach
    void setUp(){
        userService = new UserService(userRepository);
        user = User.builder()
                .email("usuario@hotmail.com")
                .name("María")
                .lastName("García")
                .city("Asturias")
                .paymentMethod("Paypal")
                .fidelityPoints(10)
                .purchasePriceAverage(20.5)
                .build();

    }

    @DisplayName("Test to verfify the FindAll")
    @Test
    void givenACreatedUserThenReturnAllUsers() {
        //GIVEN
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

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
        when (userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));

        //WHEN
        var result = userService.findUserById("usuario@hotmail.com");

        //THEN
        assertThat(result.getEmail()).isEqualTo("usuario@hotmail.com");
    }

    @DisplayName("Test for updating a  user")
    @Test
    public void givenSomeInfoTheUpdateUser(){
        //GIVEN
        when(userRepository.save(user)).thenReturn(user);

        //WHEN
        User newUser = userService.findUserById(user.getEmail());
        newUser.setCity("Madrid");
        User result = userService.updateUser(newUser, "usuario@hotmail.com");

        //THEN
        assertThat(result.getEmail()).isEqualTo("usuario@hotmail.com");
        assertThat(result.getCity()).isEqualTo("Madrid");

    }

    @DisplayName("Test for creating a new user")
    @Test
    public void givenAUserObjectThenCreateANewUser(){
        //GIVEN
        when(userRepository.save(user)).thenReturn(user);

        //WHEN
        var result = userService.createUser(user);

        //THEN
        assertThat(result.getEmail()).isEqualTo("usuario@hotmail.com");
        assertThat(result.getName()).isEqualTo("María");
        assertThat(result.getFidelityPoints()).isEqualTo(10);
    }




}
