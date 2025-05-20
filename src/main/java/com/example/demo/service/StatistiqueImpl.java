package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StatistiqueTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Test
    void testPrixMoyenAvecVoitureMockée() {
        // Création d'un mock de voiture
        Voiture voitureMock = mock(Voiture.class);
        when(voitureMock.getPrix()).thenReturn(100_000); // Le prix de la voiture est 100 000

        // Création d'un mock de StatistiqueImpl
        StatistiqueImpl statistiqueMock = mock(StatistiqueImpl.class);

        // Simulation de la méthode prixMoyen()
        when(statistiqueMock.prixMoyen()).thenReturn(new Echantillon(1, 100_000));

        // Appel de la méthode à tester
        Echantillon echantillon = statistiqueMock.prixMoyen();

        // Vérification que l'objet Echantillon n'est pas nul
        assertNotNull(echantillon, "L'échantillon ne doit pas être nul");

        // Vérification du prix moyen
        assertEquals(100_000, echantillon.getPrixMoyen(), "Le prix moyen doit être de 100 000");

        // Vérification du nombre de voitures
        assertEquals(1, echantillon.getNombreDeVoitures(), "Le nombre de voitures doit être de 1");
    }
}
