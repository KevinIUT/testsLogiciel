package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
public class StatistiqueTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Test
    void testAjouterVoiture() {
        // On crée une voiture mockée
        Voiture voiture = mock(Voiture.class);

        // Définir un comportement simulé pour la méthode `getPrix()`
        when(voiture.getPrix()).thenReturn(100_000);  // On spécifie que `getPrix()` retournera 100000

        // Ajouter la voiture mockée
        statistiqueImpl.ajouter(voiture);

        // Vérification que la méthode `ajouter()` a bien été appelée
        verify(statistiqueImpl, times(1)).ajouter(voiture);

        // Vérification du prix retourné par la voiture mockée
        assertEquals(100_000, voiture.getPrix(), "Le prix de la voiture doit être 100 000");
    }

    @Test
    void testPrixMoyenAvecVoitureMockée() {
        // On crée des voitures mockées
        Voiture voiture1 = mock(Voiture.class);
        Voiture voiture2 = mock(Voiture.class);

        // Définir des comportements simulés pour `getPrix()`
        when(voiture1.getPrix()).thenReturn(100_000);
        when(voiture2.getPrix()).thenReturn(200_000);

        // Ajouter les voitures mockées
        statistiqueImpl.ajouter(voiture1);
        statistiqueImpl.ajouter(voiture2);

        // Simuler le calcul du prix moyen
        Echantillon echantillon = statistiqueImpl.prixMoyen();

        // Vérification du prix moyen
        assertEquals(150_000, echantillon.getMoyenne(), "Le prix moyen doit être de 150 000");
    }
}
