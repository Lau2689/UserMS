package usermicroservice.valdiationstests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import usermicroservice.validations.EmailValidation;

import java.util.stream.Stream;


public class EmailValidationTest {
    private final EmailValidation emailValidation = new EmailValidation();
    @DisplayName("Testing that email has valid format")
    @ParameterizedTest
    @MethodSource("givenAValidEmailFormatShouldReturnTrue")
    void checkingEmail (String emailInput,boolean expected){
        boolean result = emailValidation.isEmail(emailInput);
        assertEquals(expected,result);
    }
    private static Stream<Arguments> givenAValidEmailFormatShouldReturnTrue(){
        return Stream.of(
                Arguments.of("laura@gmail.com",true),
                Arguments.of("Laura@gmail.com",true),
                Arguments.of("Laura@gmail.es",true),
                Arguments.of("Laura@hotmail.com",true),
                Arguments.of("Lauragmail.com",false),
                Arguments.of("Laura@gmailcom",false)
        );
    }





}
