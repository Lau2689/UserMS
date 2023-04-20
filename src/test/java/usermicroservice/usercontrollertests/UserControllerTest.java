package usermicroservice.usercontrollertests;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import usermicroservice.controllers.UserController;
import usermicroservice.models.User;
import usermicroservice.services.UserService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.ArgumentMatcher.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {UserController.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;


    void setup(){
        userController = new UserController(userService);
    }

    @Test
    void whenIsNotEmptyThenOk() throws Exception {
        //GIVEN
        List<User> allUsers = new ArrayList<>();
        allUsers.add(getMockedUser("laura@gmail.com"));
        given(userService.findAllUsers()).willReturn(allUsers);

        //WHEN
        ResultActions response = mockMvc.perform(get("/api/users"));

        //THEN
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(allUsers.size())));

    }

    @Test
    void whenAnExistingIdIsProvidingThenOk() throws Exception {
        //GIVEN
        given(userService.findUserById("laura@gmail.com")).
                willReturn(Optional.ofNullable(getMockedUser("laura@gmail.com")));

        //WHEN
        ResultActions response = mockMvc.perform(get("/api/users/{email}",getMockedUser("laura@gmail.com").getEmail()));

        //THEN
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name",is(getMockedUser("laura@gmail.com").getName())));
    }

    @Test
    void whenNecessaryParametersAreGivenForCreatingAUserThenOk() throws Exception {
        //GIVEN
        given(userService.createUser(any())).willReturn(getMockedUser("laura@gmail.com"));

        //WHEN
        ResultActions response = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getMockedUser("laura@gmail.com"))));
        //THEN/
        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.email", is(getMockedUser("laura@gmail.com").getEmail())))
                .andExpect(jsonPath("$.name", is(getMockedUser("laura@gmail.com").getName())))
                .andExpect(jsonPath("$.lastname", is(getMockedUser("laura@gmail.com").getLastName())))
                .andExpect(jsonPath("$.city", is(getMockedUser("laura@gmail.com").getCity())))
                .andExpect(jsonPath("$.paymentmethod", is(getMockedUser("laura@gmail.com").getPaymentMethod()))
                );
    }

    //GIVEN
    //WHEN
    //THEN/


    private User getMockedUser(String email){
        return User.builder()
                .email(email)
                .name("Laura")
                .lastName("García")
                .city("Asturias")
                .paymentMethod("Paypal")
                .build();
    }



}
