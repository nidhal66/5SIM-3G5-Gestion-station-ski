package tn.esprit.spring.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.impl.PisteServicesImpl;

import java.util.List;

public class PisteServicesTest {

    private IPisteServices pisteServices;

    @BeforeEach
    public void setUp() {
        pisteServices = new PisteServicesImpl(); // Crée une instance de PisteServicesImpl
    }

    @Test
    public void testAddPiste() {
        // Crée une nouvelle piste
        Piste pisteToAdd = new Piste();
        pisteToAdd.setNumPiste(1L);
        pisteToAdd.setNamePiste("Piste 1");
        pisteToAdd.setColor(Color.BLUE);
        pisteToAdd.setLength(1500);
        pisteToAdd.setSlope(30);
        
        // Ajoute la piste
        Piste result = pisteServices.addPiste(pisteToAdd);
        
        // Vérifie que la piste a été ajoutée correctement
        assertNotNull(result);
        assertEquals("Piste 1", result.getNamePiste());
        assertEquals(Color.BLUE, result.getColor());
        assertEquals(1500, result.getLength());
        assertEquals(30, result.getSlope());
    }

    @Test
    public void testRetrieveAllPistes() {
        // Ajoute quelques pistes
        Piste piste1 = new Piste();
        piste1.setNumPiste(1L);
        piste1.setNamePiste("Piste 1");
        piste1.setColor(Color.BLUE);
        piste1.setLength(1500);
        piste1.setSlope(30);
        pisteServices.addPiste(piste1);
        
        Piste piste2 = new Piste();
        piste2.setNumPiste(2L);
        piste2.setNamePiste("Piste 2");
        piste2.setColor(Color.GREEN);
        piste2.setLength(2000);
        piste2.setSlope(25);
        pisteServices.addPiste(piste2);

        // Récupère toutes les pistes
        List<Piste> pistes = pisteServices.retrieveAllPistes();
        
        // Vérifie que les pistes sont présentes
        assertEquals(2, pistes.size());
        assertEquals("Piste 1", pistes.get(0).getNamePiste());
        assertEquals("Piste 2", pistes.get(1).getNamePiste());
    }

    @Test
    public void testRetrievePiste() {
        // Ajoute une piste
        Piste pisteToAdd = new Piste();
        pisteToAdd.setNumPiste(1L);
        pisteToAdd.setNamePiste("Piste 1");
        pisteToAdd.setColor(Color.BLUE);
        pisteToAdd.setLength(1500);
        pisteToAdd.setSlope(30);
        pisteServices.addPiste(pisteToAdd);
        
        // Récupère la piste par son ID
        Piste retrievedPiste = pisteServices.retrievePiste(1L);
        
        // Vérifie que la piste récupérée est correcte
        assertNotNull(retrievedPiste);
        assertEquals("Piste 1", retrievedPiste.getNamePiste());
    }

    @Test
    public void testRemovePiste() {
        // Ajoute une piste
        Piste pisteToAdd = new Piste();
        pisteToAdd.setNumPiste(1L);
        pisteToAdd.setNamePiste("Piste 1");
        pisteToAdd.setColor(Color.BLUE);
        pisteToAdd.setLength(1500);
        pisteToAdd.setSlope(30);
        pisteServices.addPiste(pisteToAdd);
        
        // Vérifie que la piste est ajoutée
        assertNotNull(pisteServices.retrievePiste(1L));

        // Supprime la piste
        pisteServices.removePiste(1L);
        
        // Vérifie que la piste a été supprimée
        assertNull(pisteServices.retrievePiste(1L));
    }
}
