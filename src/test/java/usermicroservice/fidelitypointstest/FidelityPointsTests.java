package usermicroservice.fidelitypointstest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import usermicroservice.fidelitypoints.FidelityPoints;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FidelityPointsTests {
     private final FidelityPoints fidelityPoints = new FidelityPoints();

     @DisplayName("Testing the calculation of fidelity point")
     @ParameterizedTest
     @MethodSource("givenSeveralCartAmountThenReturnFidelityPoints")
     void givenACartThenCalculateFidelityPoints (Double cartAmountInput,int expected){
          int result = fidelityPoints.calculatingFidelityPoints(cartAmountInput);
          assertEquals(expected,result);
     }
     private static Stream<Arguments> givenSeveralCartAmountThenReturnFidelityPoints(){
          return Stream.of(
                  Arguments.of(10.25,0),
                  Arguments.of(20.0,1),
                  Arguments.of(26.48,1),
                  Arguments.of(30.0,3),
                  Arguments.of(50.30,5),
                  Arguments.of(75.10,5),
                  Arguments.of(92.30,5),
                  Arguments.of(100.6,10),
                  Arguments.of(260.9,10)
          );
     }
}
