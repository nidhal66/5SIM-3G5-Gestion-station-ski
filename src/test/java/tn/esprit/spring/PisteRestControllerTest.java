import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;
import tn.esprit.spring.controllers.PisteRestController;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PisteRestControllerTest {

    @Mock
    private IPisteServices pisteServices;

    @InjectMocks
    private PisteRestController pisteRestController;

    @Test
    public void testAddPiste() {
        // Arrange
        Piste piste = new Piste();
        piste.setNumPiste(1L);
        piste.setNamePiste("Piste 1");

        when(pisteServices.addPiste(piste)).thenReturn(piste);

        // Act
        Piste result = pisteRestController.addPiste(piste);

        // Assert
        assertNotNull(result, "Le résultat ne doit pas être nul.");
        assertEquals(piste.getNumPiste(), result.getNumPiste(), "Les IDs de piste devraient correspondre.");
        assertEquals(piste.getNamePiste(), result.getNamePiste(), "Les noms de piste devraient correspondre.");
        verify(pisteServices, times(1)).addPiste(piste);
    }

    @Test
    public void testGetAllPistes() {
        // Arrange
        Piste piste1 = new Piste();
        piste1.setNumPiste(1L);
        Piste piste2 = new Piste();
        piste2.setNumPiste(2L);
        List<Piste> pistes = Arrays.asList(piste1, piste2);

        when(pisteServices.retrieveAllPistes()).thenReturn(pistes);

        // Act
        List<Piste> result = pisteRestController.getAllPistes();

        // Assert
        assertNotNull(result, "La liste de pistes ne doit pas être nulle.");
        assertEquals(2, result.size(), "La taille de la liste de pistes devrait être de 2.");
        verify(pisteServices, times(1)).retrieveAllPistes();
    }

    @Test
    public void testGetById() {
        // Arrange
        Piste piste = new Piste();
        piste.setNumPiste(1L);

        when(pisteServices.retrievePiste(1L)).thenReturn(piste);

        // Act
        Piste result = pisteRestController.getById(1L);

        // Assert
        assertNotNull(result, "La piste récupérée ne doit pas être nulle.");
        assertEquals(1L, result.getNumPiste(), "L'ID de piste devrait correspondre.");
        verify(pisteServices, times(1)).retrievePiste(1L);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Long numPiste = 1L;

        // Act
        pisteRestController.deleteById(numPiste);

        // Assert
        verify(pisteServices, times(1)).removePiste(numPiste);
    }
}
