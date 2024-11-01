package tn.esprit.spring;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.controllers.PisteRestController;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PisteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IPisteServices pisteServices;

    @InjectMocks
    private PisteRestController pisteRestController;

    private Piste piste;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        piste = new Piste();
        piste.setNumPiste(1L);
        piste.setNamePiste("Piste 1");
        piste.setColor("Blue");
        piste.setLength(1500);
        piste.setSlope(30);
    }

    @Test
    public void testAddPiste() throws Exception {
        when(pisteServices.addPiste(any(Piste.class))).thenReturn(piste);

        mockMvc.perform(post("/piste/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numPiste\":1,\"namePiste\":\"Piste 1\",\"color\":\"Blue\",\"length\":1500,\"slope\":30}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numPiste").value(1))
                .andExpect(jsonPath("$.namePiste").value("Piste 1"))
                .andExpect(jsonPath("$.color").value("Blue"))
                .andExpect(jsonPath("$.length").value(1500))
                .andExpect(jsonPath("$.slope").value(30));
    }

    @Test
    public void testGetAllPistes() throws Exception {
        List<Piste> pistes = new ArrayList<>();
        pistes.add(piste);
        when(pisteServices.retrieveAllPistes()).thenReturn(pistes);

        mockMvc.perform(get("/piste/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numPiste").value(1))
                .andExpect(jsonPath("$[0].namePiste").value("Piste 1"))
                .andExpect(jsonPath("$[0].color").value("Blue"))
                .andExpect(jsonPath("$[0].length").value(1500))
                .andExpect(jsonPath("$[0].slope").value(30));
    }

    @Test
    public void testUpdatePiste() throws Exception {
        Piste updatedPiste = new Piste();
        updatedPiste.setNumPiste(1L);
        updatedPiste.setNamePiste("Piste Updated");
        updatedPiste.setColor("Red");
        updatedPiste.setLength(1600);
        updatedPiste.setSlope(35);

        when(pisteServices.updatePiste(any(Piste.class))).thenReturn(updatedPiste);

        String requestBody = "{"
                + "\"numPiste\": 1,"
                + "\"namePiste\": \"Piste Updated\","
                + "\"color\": \"Red\","
                + "\"length\": 1600,"
                + "\"slope\": 35"
                + "}";

        mockMvc.perform(put("/piste/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numPiste").value(1))
                .andExpect(jsonPath("$.namePiste").value("Piste Updated"))
                .andExpect(jsonPath("$.color").value("Red"))
                .andExpect(jsonPath("$.length").value(1600))
                .andExpect(jsonPath("$.slope").value(35));
    }

    @Test
    public void testGetById() throws Exception {
        when(pisteServices.retrievePiste(1L)).thenReturn(piste);

        mockMvc.perform(get("/piste/get/{id-piste}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numPiste").value(1))
                .andExpect(jsonPath("$.namePiste").value("Piste 1"))
                .andExpect(jsonPath("$.color").value("Blue"))
                .andExpect(jsonPath("$.length").value(1500))
                .andExpect(jsonPath("$.slope").value(30));
    }

    @Test
    public void testDeleteById() throws Exception {
        doNothing().when(pisteServices).removePiste(1L);

        mockMvc.perform(delete("/piste/delete/{id-piste}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
