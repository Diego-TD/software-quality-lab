package mx.edu.cetys.softwarequalitylab.pets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // Rest Client like insomnia

    @Autowired
    private PetRepository petRepository;

    //BeforeAll, AfterAll, BeforeEach, AfterEach

    @BeforeEach
    void tearDown() {
        // Clean DB after each test
        petRepository.deleteAll();
    }

    @Test
    void shouldCreatePetAndReturn201() throws Exception {
        String requestBody = """
                {
                    "name": "firu",
                    "color": "cacahuate",
                    "race": "chichuaha",
                    "age": 10
                }
                """;

        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
                .andExpect(jsonPath("$.info")
                        .value("New Pet was added"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.response.id").value(1))
                .andExpect(jsonPath("$.response.name").value("firu"))
                .andExpect(jsonPath("$.response.color").value("cacahuate"))
                .andExpect(jsonPath("$.response.race").value("chichuaha"))
                .andExpect(jsonPath("$.response.age").value(10))
        ;
    }

}
