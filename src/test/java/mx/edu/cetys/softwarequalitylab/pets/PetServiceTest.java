package mx.edu.cetys.softwarequalitylab.pets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 *
 */
@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService;

    /***
     * Recibir peticion desde el controller (Pet)
     *         Verificar valores de pet
     *         edad no sea negativa
     *         nombre mas de 2 letras
     *         guardar a BD  y la base de datos nos regresa el mismo pet pero con ID
     */
    @Test
    void savePet() {
        // arrange
        var petRequest = new PetController.PetRequest("Juan","Negro","Chihuahua", 5);

        // act
        when(petRepository
                .save(any(Pet.class)))
                .thenReturn(new Pet(1L, "Juan", "Negro","Chihuahua", 5));

        PetController.PetResponse petResponse = petService.savePet(petRequest);

        // assert
        Mockito.verify(petRepository, times(1)).save(any(Pet.class));

        assertEquals(1, petResponse.id());
        assertEquals("Juan", petResponse.name());
        assertEquals("Negro", petResponse.color());
        assertEquals("Chihuahua", petResponse.race());
        assertEquals(5, petResponse.age());
    }

    @Test
    void getAllPets() {
        // recibir peticion del controller
        // query a la bd
        // mapear los valores del dto a la respuesta de pet
        // si la bd no tiene valoreas no fallar, regresar array vacio
    }





}
