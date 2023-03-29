package com.example.ratatouille23;

import java.util.regex.Pattern;

public class ControlloCampiProdottoUtenteMock {

    public ControlloCampiProdottoUtenteMock(){

    }

    public boolean controlloCampiProdotto(String nomeProdotto, String nomeProdottoSecondaLingua, String ingredienti,
                                          String ingredientiSecondaLingua, String allergeni, Double costo){

        if(nomeProdotto == null || nomeProdottoSecondaLingua == null || ingredientiSecondaLingua == null || ingredienti == null || allergeni == null || costo == null){
            return false;
        }

        if(!(nomeProdotto.isEmpty()) && !(ingredienti.isEmpty()) && costo != 0){
            if(nomeProdottoSecondaLingua.isEmpty() && ingredientiSecondaLingua.isEmpty()){
                return true;
            }
            else return !nomeProdottoSecondaLingua.isEmpty() && !ingredientiSecondaLingua.isEmpty();
        }else{
            return false;
        }

    }

    public boolean controlloCampiCreazioneUtente(String nomeUtente, String email, String password, String tipologiaUtente){

        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        if(nomeUtente == null || email == null || password == null || tipologiaUtente == null){
            return false;
        }

        if(!(VALID_EMAIL_ADDRESS_REGEX.matcher(email)).matches()){
            return false;
        }

        if(nomeUtente.equals("") || email.equals("") || password.equals("") || tipologiaUtente.equals("")){
            return false;
        }

        return true;


    }



}
