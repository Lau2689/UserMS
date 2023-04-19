package usermicroservice;

import usermicroservice.models.User;
import usermicroservice.repositories.UserRepository;
import usermicroservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        given(userRepository.findAll()).willReturn(Arrays.asList(user));

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
        given (userRepository.findById(user.getEmail())).willReturn(Optional.ofNullable(user));

        //WHEN
        var result = userService.findUserById("usuario@hotmail.com");

        //THEN
        assertThat(result.map(User::getEmail).equals(user.getEmail()));

    }

    @DisplayName("Test for creating a new user")
    @Test
    public void givenAUserObjectThenCreateANewUser(){
        //GIVEN
        given(userRepository.save(user)).willReturn(user);

        //WHEN
        var result = userService.createUser(user);

        //THEN
        assertThat(result.getEmail()).isEqualTo("usuario@hotmail.com");
        assertThat(result.getName()).isEqualTo("María");
        assertThat(result.getFidelityPoints()).isEqualTo(10);
    }

    @DisplayName("Test for updating a  user")
    @Test
    public void givenSomeInfoTheUpdateUser(){

        //GIVEN
        given(userRepository.save(user)).willReturn(user);

        //WHEN

        user.setCity("Madrid");

        //ESTO NO FUNCIONAL. ME DEVUELVE UN OPTIONAL VACIO
        var result = userService.updateUser(user, "usuario@hotmail.com");

        /*
        ESTO FUNCIONA
        var result = userRepository.save(user);
        assertThat(result.getCity()).isEqualTo("Madrid");
         */


        //THEN
        assertThat(result.map(User::getEmail)).isEqualTo("usuario@hotmail.com");
        assertThat(result.map(User::getCity)).isEqualTo("Madrid");

    }

    @DisplayName("Test for deliting a  user")
    @Test
    public void givenTheIdThenDeleteUser() {

        //GIVEN
        userRepository.save(user);

        //WHEN
        userService.deleteUser(user.getEmail());
        var result = userService.findUserById(user.getEmail());

        //THEN
        assertThat(result).isEmpty();

    }





}
