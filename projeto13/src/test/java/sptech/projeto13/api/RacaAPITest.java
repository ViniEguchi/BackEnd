package sptech.projeto13.api;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RacaAPITest {
    @Value("classpath:racas.json")
    Resource racasJSON;

    @Autowired
    private MockMvc mockMvc;

    String url = "/racas";

    @Test
    @Order(1)
    public void testGetComDados() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        new String(racasJSON.getContentAsByteArray())
                ));
    }

    @Test
    @Order(2)
    void testPostRacaValida() throws Exception {
        String novaRacaJson = """
                {
                    "codigo": "PUGLOKO",
                    "nome" : "Pug Loko"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novaRacaJson))
                .andExpect(status().is(201));
    }

    @Test
    @Order(3)
    void testPostInvalido() throws Exception {
        String novaRacaJson = """
                {
                    "codigo": "TESTE",
                    "nome": ""
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(novaRacaJson))
                .andExpect(status().is(400));
    }
}
