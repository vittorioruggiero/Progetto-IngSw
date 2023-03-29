package com.example.ratatouille23;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class ControlloCampiProdottoUtenteMockTest {
    ControlloCampiProdottoUtenteMock controlloCampiProdottoUtenteMock;

    @Before
    public void init(){
        controlloCampiProdottoUtenteMock = new ControlloCampiProdottoUtenteMock();
    }
    @Test
    public void testProdottoAggiuntoCorrettamenteNoSecondaLinguaNoAllergeni(){
        assertTrue(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "", "Pomodoro, Mozzarella, Olio",
                "", "", 10.0));
    }
    @Test
    public void testProdottoAggiuntoCorrettamenteNoSecondaLingua(){
        assertTrue(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "", "Pomodoro, Mozzarella, Olio",
                "", "Glutine", 10.0));
    }
    @Test
    public void testProdottoAggiuntoCorrettamenteNoAllergeni(){
        assertTrue(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "Margherita Pizza", "Pomodoro, Mozzarella, Olio",
                "Tomato Sauce, Mozzarella Cheese, Oil", "", 10.0));
    }
    @Test
    public void testProdottoAggiuntoCorrettamente(){
        assertTrue(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "Margherita Pizza", "Pomodoro, Mozzarella, Olio",
                "Tomato Sauce, Mozzarella Cheese, Oil", "Glutine", 10.0));
    }
    @Test
    public void testNomeProdottoNull(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto(null, "Margherita Pizza", "Pomodoro, Mozzarella, Olio",
                "Tomato Sauce, Mozzarella Cheese, Oil", "Glutine", 10.0));
    }
    @Test
    public void testNomeProdottoVuoto(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto("", "Margherita Pizza", "Pomodoro, Mozzarella, Olio",
                "Tomato Sauce, Mozzarella Cheese, Oil", "Glutine", 10.0));
    }
    @Test
    public void testIngredientiNull(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "Margherita Pizza", null,
                "Tomato Sauce, Mozzarella Cheese, Oil", "Glutine", 10.0));
    }
    @Test
    public void testIngredientiVuoto(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "Margherita Pizza", "",
                "Tomato Sauce, Mozzarella Cheese, Oil", "Glutine", 10.0));
    }
    @Test
    public void testCostoNull(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "Margherita Pizza", "Pomodoro, Mozzarella, Olio",
                "Tomato Sauce, Mozzarella Cheese, Oil", "Glutine", null));
    }
    @Test
    public void testCostoZero(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "Margherita Pizza", "Pomodoro, Mozzarella, Olio",
                "Tomato Sauce, Mozzarella Cheese, Oil", "Glutine", 0.0));
    }
    @Test
    public void testAllergeniNull(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "Margherita Pizza", "Pomodoro, Mozzarella, Olio",
                "Tomato Sauce, Mozzarella Cheese, Oil", null, 10.0));
    }
    @Test
    public void testAllergeniVuoto(){
        assertTrue(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "Margherita Pizza", "Pomodoro, Mozzarella, Olio",
                "Tomato Sauce, Mozzarella Cheese, Oil", "", 10.0));
    }

    @Test
    public void testNomeSecondaLinguaVuoto(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "", "Pomodoro, Mozzarella, Olio",
                "Tomato Sauce, Mozzarella Cheese, Oil", "Glutine", 10.0));
    }
    @Test
    public void testIngredientiSecondaLinguaVuoto(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "Margherita Pizza", "Pomodoro, Mozzarella, Olio",
                "", "Glutine", 10.0));
    }
    @Test
    public void testIngredientiSecondaLinguaNull(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", "Margherita Pizza", "Pomodoro, Mozzarella, Olio",
                null, "Glutine", 10.0));
    }
    @Test
    public void testNomeSecondaLinguaNull(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiProdotto("Margherita", null, "Pomodoro, Mozzarella, Olio",
                "Tomato Sauce, Mozzarella Cheese, Oil", "Glutine", 10.0));
    }

    @Test
    public void testCampiValidi(){
        assertTrue(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente("supervisore", "supervisore@gmail.com", "prova", "Supervisore"));
    }
    @Test
    public void testEmailVuota(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente("supervisore", "", "prova", "Supervisore"));
    }
    @Test
    public void testEmailNull(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente("supervisore", null, "prova", "Supervisore"));
    }
    @Test
    public void testNomeUtenteVuoto(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente("", "supervisore@gmail.com", "prova", "Supervisore"));
    }
    @Test
    public void testNomeUtenteNull(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente(null, "supervisore@gmail.com", "prova", "Supervisore"));
    }
    @Test
    public void testPasswordVuota(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente("supervisore", "supervisore@gmail.com", "", "Supervisore"));
    }
    @Test
    public void testPasswordNull(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente("supervisore", "supervisore@gmail.com", null, "Supervisore"));
    }
    @Test
    public void testTipologiaSpinnerVuota(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente("supervisore", "supervisore@gmail.com", "prova", ""));
    }
    @Test
    public void testTipologiaSpinnerNull(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente("supervisore", "supervisore@gmail.com", "prova", null));
    }
    @Test
    public void testEmailSenzaDominio(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente("supervisore", "supervisore@gmail", "prova", "Supervisore"));
    }
    @Test
    public void testEmailSenzaChiocciola(){
        assertFalse(controlloCampiProdottoUtenteMock.controlloCampiCreazioneUtente("supervisore", "supervisoregmail.com", "prova", "Supervisore"));
    }

}