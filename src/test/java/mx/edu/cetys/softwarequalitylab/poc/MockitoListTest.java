package mx.edu.cetys.softwarequalitylab.poc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

import mx.edu.cetys.softwarequalitylab.validators.EmailValidatorService;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class MockitoListTest {

    @Mock
    List<String> mockList;

    @Mock
    EmailValidatorService emailValidatorServiceMock;

//    @BeforeEach
//    void setup(){
//        mockList = Mockito.mock(List.class);
//    }

    @Test
    void shouldReturn_CustomSizeWhenMocked(){
        //Arrange : is already done for my mocklist

        //Define mocks behavior
        when(mockList.size()).thenReturn(999_999_999)
                .thenReturn(1)
                .thenReturn(67)
                .thenThrow(new RuntimeException())
                .thenReturn(0);

        //Act
        // var size = mockList.size();

        //assert
        assertEquals(999_999_999, mockList.size());
        assertEquals(1, mockList.size());
        assertEquals(67, mockList.size());
//        assertEquals(2, mockList.size()); Has to fail
        assertThrows(RuntimeException.class, () -> mockList.size());
        assertEquals(0, mockList.size());

        //Post assert act
        // var mockList.size
    }

    @Test
    void shouldMock_LiatGetWithParameters(){
        // define mock behavior
        when(mockList.get(0))
                .thenReturn("Hello")
                .thenReturn("Aaron")
                .thenReturn("Cetys");
        when(mockList.get(1)).thenReturn("World");

        assertEquals("Hello", mockList.get(0));
        assertEquals("World", mockList.get(1));
        assertEquals("Aaron", mockList.get(0));
        assertEquals("Cetys", mockList.get(0));

    }

    @Test
    void mock_emailValidatorWithArgumentMatchers(){
        // define mock behavior
        when(emailValidatorServiceMock.isValid(anyString())).
                thenReturn(true);
        when(emailValidatorServiceMock.isValid(isNull())) // isNotNull
                .thenReturn(false)
                .thenReturn(true);

        assertTrue(emailValidatorServiceMock.isValid("aeiou"));
        assertFalse(emailValidatorServiceMock.isValid(null));
        assertTrue(emailValidatorServiceMock.isValid(null));
        assertTrue(emailValidatorServiceMock.isValid(null));
        assertTrue(emailValidatorServiceMock.isValid(""));
        assertTrue(emailValidatorServiceMock.isValid("@"));
    }

}