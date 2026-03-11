package mx.edu.cetys.softwarequalitylab.poc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedCalculatorTest {

    @ParameterizedTest
    @DisplayName("Test sum with CSV source")
    @CsvSource({
            "10,10,20",
            "0,0,0,5", //extra param
            "-5,5,0",
//            "uno,dos,tres",
            "'1','2','3'"
    })
    void testSumWithCsvSource(int a, int b, int expected){
        // arrange
        //act
        assertEquals(expected, a+b);
    }

    @DisplayName("Validate String not empty")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "hola",
                    "",
                    " ",
            }
    )
    void testValidateStringNotEmpty(String string){
        assertFalse(string.isEmpty());
    }

    public static Stream<Object[]> provideNumbers(){
        return Stream.of(
                new Object[] {2,4},
                new Object[] {5,10},
                new Object[] {10,20}
        );
    }

    @DisplayName("Validate double of an integer with MethodSource")
    @ParameterizedTest
    @MethodSource("provideNumbers")
    void testValidateDoubleOfIntegerWithMethodSource(int a, int expected){
        var doubleValue = a * 2;
        assertEquals(expected, doubleValue);
    }


    // POJO - Plain Old Java Object: Clase con getters and setters
    // Records - POJO inmutable
    private record Pet(String name, String color, int age, String race){}

    public static Stream<Object[]> providePets(){
        return Stream.of(
                new Object[] {new Pet("nini","negro",5,"chihuahua"), false},
                new Object[] {new Pet("camaron","naranja",11,"cocker"), true},
                new Object[] {new Pet("chocoroll","cafe",19,"camaleon"), true}

        );
    }

    @ParameterizedTest
    @DisplayName("Validate Pets Age")
    @MethodSource("providePets")
    void testValidatePetsAgeHigherThan10(Pet pet, boolean expected){
        var isOlderThanTen = pet.age() > 10;
        assertEquals(expected, isOlderThanTen);
    }
}
