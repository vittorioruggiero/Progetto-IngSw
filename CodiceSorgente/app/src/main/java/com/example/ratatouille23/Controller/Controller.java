package com.example.ratatouille23.Controller;

import static com.example.ratatouille23.UI.activity.LoginActivity.getAddettoSala;
import static com.example.ratatouille23.UI.activity.LoginActivity.getAdmin;
import static com.example.ratatouille23.UI.activity.LoginActivity.getSupervisore;
//import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.getSezioni;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ratatouille23.UI.activity.HomeAddettoSalaActivity;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.UI.activity.HomeSupervisoreActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.UI.activity.ReimpostaPasswordActivity;
import com.example.ratatouille23.UI.fragment.AggiungiProdottoFragment;
import com.example.ratatouille23.UI.fragment.AggiungiSezioneFragment;
import com.example.ratatouille23.UI.fragment.ContiFragment;
import com.example.ratatouille23.UI.fragment.HomeAdminFragment;
import com.example.ratatouille23.UI.fragment.ModificaProdottoFragment;
import com.example.ratatouille23.UI.fragment.ModificaSezioniFragment;
import com.example.ratatouille23.UI.fragment.OrdinazioniFragment;
import com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment;
import com.example.ratatouille23.UI.fragment.VisualizzaMenuFragment;
import com.example.ratatouille23.adapter.SingoliOrdiniAdapter;
import com.example.ratatouille23.entity.AddettoSala;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.Avviso;
import com.example.ratatouille23.entity.Ordinazione;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.entity.SingoloOrdine;
import com.example.ratatouille23.entity.Supervisore;
import com.example.ratatouille23.retrofit.API.AddettoSalaAPI;
import com.example.ratatouille23.retrofit.API.AmministratoreAPI;
import com.example.ratatouille23.retrofit.API.AttivitaAPI;
import com.example.ratatouille23.retrofit.API.AvvisoAPI;
import com.example.ratatouille23.retrofit.API.OrdinazioneAPI;
import com.example.ratatouille23.retrofit.API.ProdottoMenuAPI;
import com.example.ratatouille23.retrofit.API.SezioneMenuAPI;
import com.example.ratatouille23.retrofit.API.SingoloOrdineAPI;
import com.example.ratatouille23.retrofit.API.SupervisoreAPI;
import com.example.ratatouille23.retrofit.RetrofitService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Controller {

    private AmministratoreAPI amministratoreAPI;
    private AddettoSalaAPI addettoSalaAPI;
    private SupervisoreAPI supervisoreAPI;
    private Amministratore admin;
    private Supervisore supervisore;
    private SezioneMenuAPI sezioneMenuAPI;
    private ProdottoMenuAPI prodottoMenuAPI;
    private AttivitaAPI attivitaAPI;
    private AvvisoAPI avvisoAPI;
    private AddettoSala addettoSala;
    private List<Ordinazione> listaOrdinazioni;
    private List<SingoloOrdine> listaSingoliOrdini;
    private SingoliOrdiniAdapter singoliOrdiniAdapter;
    private RetrofitService retrofitService;
    //private ArrayList<SezioneMenu> sezioni = getSezioni();
    private ArrayList<SezioneMenu> sezioni;

    public Controller() {

    }

    public Controller(String utente){

        if(utente != null){
            retrofitService = new RetrofitService();
            if(utente.contains("Admin")){
                admin = getAdmin();
                amministratoreAPI = retrofitService.getRetrofit().create(AmministratoreAPI.class);
            }else if(utente.contains("AddettoSala")){
                addettoSala = getAddettoSala();
                addettoSalaAPI = retrofitService.getRetrofit().create(AddettoSalaAPI.class);
            }else if(utente.contains("Supervisore")){
                supervisore = getSupervisore();
                supervisoreAPI = retrofitService.getRetrofit().create(SupervisoreAPI.class);
            }
        }

    }

    public void checkAddettoSala(LoginActivity loginActivity, String username, String password) {

        if(addettoSalaAPI == null) {
            retrofitService = new RetrofitService();
            addettoSalaAPI = retrofitService.getRetrofit().create(AddettoSalaAPI.class);
        }

        addettoSalaAPI.getAddettoSalaByUsername(username)
                .enqueue(new Callback<AddettoSala>() {
                    @Override
                    public void onResponse(Call<AddettoSala> call, Response<AddettoSala> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            addettoSala = response.body();
                            loginActivity.setAddettoSala(addettoSala);
                            if(addettoSala != null){
                                if(addettoSala.getPrimoAccesso() && addettoSala.getPassword().equals(password)){
                                    mostraReimpostaPasswordActivity(loginActivity);
                                }else{
                                    if(username.equals(addettoSala.getNomeUtente())
                                            && password.equals(addettoSala.getPassword())){
                                        loginActivity.setCampiErratiTextViewVisibility(View.INVISIBLE);
                                        mostraHomeAddettoSalaActivity(loginActivity);
                                    }else{
                                        loginActivity.setCampiErratiTextViewVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                            loginActivity.setCampiErratiTextViewVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onFailure(Call<AddettoSala> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(loginActivity, "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void checkSupervisore(LoginActivity loginActivity, String username, String password) {

        if(supervisoreAPI == null) {
            retrofitService = new RetrofitService();
            supervisoreAPI = retrofitService.getRetrofit().create(SupervisoreAPI.class);
        }

        supervisoreAPI.getSupervisoreByUsername(username)
                .enqueue(new Callback<Supervisore>() {
                    @Override
                    public void onResponse(Call<Supervisore> call, Response<Supervisore> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            supervisore = response.body();
                            loginActivity.setSupervisore(supervisore);
                            if(supervisore != null){
                                if(supervisore.getPrimoAccesso() && supervisore.getPassword().equals(password)){
                                    mostraReimpostaPasswordActivity(loginActivity);
                                }else{
                                    if(username.equals(supervisore.getNomeUtente())
                                            && password.equals(supervisore.getPassword())){
                                        loginActivity.setCampiErratiTextViewVisibility(View.INVISIBLE);
                                        mostraHomeSupervisoreActivity(loginActivity);
                                    }else{
                                        loginActivity.setCampiErratiTextViewVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Trying addettosala...");
                            checkAddettoSala(loginActivity, username, password);
                        }

                    }

                    @Override
                    public void onFailure(Call<Supervisore> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(loginActivity, "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void checkAdmin(LoginActivity loginActivity, String username, String password) {

        if(amministratoreAPI == null) {
            retrofitService = new RetrofitService();
            amministratoreAPI = retrofitService.getRetrofit().create(AmministratoreAPI.class);
        }

        amministratoreAPI.getAdminByUsername(username)
                .enqueue(new Callback<Amministratore>() {
                    @Override
                    public void onResponse(Call<Amministratore> call, Response<Amministratore> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            admin = response.body();
                            loginActivity.setAdmin(admin);
                            if(admin != null){
                                if(username.equals(admin.getNomeUtenteAmministratore())
                                        && password.equals(admin.getPasswordAmministratore())){
                                    mostraHomeAdminActivity(loginActivity);
                                    loginActivity.setCampiErratiTextViewVisibility(View.INVISIBLE);
                                }
                                else{
                                    loginActivity.setCampiErratiTextViewVisibility(View.VISIBLE);
                                }
                            }
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Trying supervisore...");
                            checkSupervisore(loginActivity, username, password);
                        }

                    }
                    @Override
                    public void onFailure(Call<Amministratore> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(loginActivity, "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void checkSezioniAdmin(Activity activity, PersonalizzaMenuFragment fragmentPersonalizzaMenu) {

        if(sezioneMenuAPI == null){
            sezioneMenuAPI = retrofitService.getRetrofit().create(SezioneMenuAPI.class);
        }

        sezioneMenuAPI.getSezioniByAttivita(admin.getNomeAttivita(), admin.getIndirizzoAttivita())
                .enqueue(new Callback<ArrayList<SezioneMenu>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SezioneMenu>> call, Response<ArrayList<SezioneMenu>> response) {
                        if(response.body() != null){
                            setProdotti(response.body(), activity, fragmentPersonalizzaMenu);
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                        }else{
                            Toast.makeText(activity, "Non ci sono prodotti da visualizzare", Toast.LENGTH_SHORT).show();
                        }
                        fragmentPersonalizzaMenu.setMenuRecyclerAdapter(sezioni);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SezioneMenu>> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public void checkSezioniSupervisore(Activity activity, PersonalizzaMenuFragment fragmentPersonalizzaMenu) {

        if(sezioneMenuAPI == null){
            sezioneMenuAPI = retrofitService.getRetrofit().create(SezioneMenuAPI.class);
        }

        sezioneMenuAPI.getSezioniByAttivita(supervisore.getNomeAttivita(), supervisore.getIndirizzoAttivita())
                .enqueue(new Callback<ArrayList<SezioneMenu>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SezioneMenu>> call, Response<ArrayList<SezioneMenu>> response) {
                        if(response.body() != null){
                            setProdotti(response.body(), activity, fragmentPersonalizzaMenu);
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                        }else{
                            Toast.makeText(activity, "Non ci sono prodotti da visualizzare", Toast.LENGTH_SHORT).show();
                        }
                        fragmentPersonalizzaMenu.setMenuRecyclerAdapter(sezioni);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SezioneMenu>> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void checkSezioniAddettoSala(Activity activity, VisualizzaMenuFragment visualizzaMenuFragment) {

        if(sezioneMenuAPI == null){
            sezioneMenuAPI = retrofitService.getRetrofit().create(SezioneMenuAPI.class);
        }

        sezioneMenuAPI.getSezioniByAttivita(addettoSala.getNomeAttivita(), addettoSala.getIndirizzoAttivita())
                .enqueue(new Callback<ArrayList<SezioneMenu>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SezioneMenu>> call, Response<ArrayList<SezioneMenu>> response) {
                        if(response.body() != null){
                            setProdottiAddettoSala(response.body(), activity, visualizzaMenuFragment);
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                        }else{
                            Toast.makeText(activity, "Non ci sono prodotti da visualizzare", Toast.LENGTH_SHORT).show();
                        }
                        visualizzaMenuFragment.setVisualizzaMenuRecyclerAdapter(sezioni);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SezioneMenu>> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void checkAttivita(String nome, String indirizzo, HomeAdminFragment homeAdminFragment){

        if(attivitaAPI == null){
            attivitaAPI = retrofitService.getRetrofit().create(AttivitaAPI.class);
        }

        attivitaAPI.getAttivitaById(nome, indirizzo)
                .enqueue(new Callback<Attivita>() {
                    @Override
                    public void onResponse(Call<Attivita> call, Response<Attivita> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            homeAdminFragment.setAttivita(response.body());
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Attivita> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                    }
                });

    }

    public void checkAttivitaAddettoSala(String nome, String indirizzo, OrdinazioniFragment ordinazioniFragment){

        if(attivitaAPI == null){
            attivitaAPI = retrofitService.getRetrofit().create(AttivitaAPI.class);
        }

        attivitaAPI.getAttivitaById(nome, indirizzo)
                .enqueue(new Callback<Attivita>() {
                    @Override
                    public void onResponse(Call<Attivita> call, Response<Attivita> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            ordinazioniFragment.setTavoliAttivita(response.body());
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Attivita> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                    }
                });

    }

    public void setProdotti(ArrayList<SezioneMenu> sezioniMenu, Activity activity, PersonalizzaMenuFragment fragmentPersonalizzaMenu) {
        if(prodottoMenuAPI == null){
            prodottoMenuAPI = retrofitService.getRetrofit().create(ProdottoMenuAPI.class);
        }
        sezioni = sezioniMenu;
        for(SezioneMenu sezioneMenu : sezioni){
            prodottoMenuAPI.getProdottiBySezione(sezioneMenu.getNome())
                    .enqueue(new Callback<List<ProdottoMenu>>() {
                        @Override
                        public void onResponse(Call<List<ProdottoMenu>> call, Response<List<ProdottoMenu>> response) {
                            if(response.body() != null){
                                sezioneMenu.setProdottiMenu(response.body());
                                Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            }
                            fragmentPersonalizzaMenu.notifyDataChanged();
                        }

                        @Override
                        public void onFailure(Call<List<ProdottoMenu>> call, Throwable t) {
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                            Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    public void setProdottiAddettoSala(ArrayList<SezioneMenu> sezioniMenu, Activity activity,
                                       VisualizzaMenuFragment visualizzaMenuFragment) {
        if(prodottoMenuAPI == null){
            prodottoMenuAPI = retrofitService.getRetrofit().create(ProdottoMenuAPI.class);
        }
        sezioni = sezioniMenu;
        for(SezioneMenu sezioneMenu : sezioni){
            prodottoMenuAPI.getProdottiBySezione(sezioneMenu.getNome())
                    .enqueue(new Callback<List<ProdottoMenu>>() {
                        @Override
                        public void onResponse(Call<List<ProdottoMenu>> call, Response<List<ProdottoMenu>> response) {
                            if(response.body() != null){
                                sezioneMenu.setProdottiMenu(response.body());
                                Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            }
                            visualizzaMenuFragment.notifyDataChanged();
                        }

                        @Override
                        public void onFailure(Call<List<ProdottoMenu>> call, Throwable t) {
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                            Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    public void setListaSingoliOrdini(ContiFragment contiFragment, int id_ordinazione) {

        retrofitService = new RetrofitService();

        SingoloOrdineAPI singoloOrdineAPI = retrofitService.getRetrofit().create(SingoloOrdineAPI.class);

        singoloOrdineAPI.getAllSingoliOrdiniByOrdinazione(id_ordinazione)
                .enqueue(new Callback<List<SingoloOrdine>>() {
                    @Override
                    public void onResponse(Call<List<SingoloOrdine>> call, Response<List<SingoloOrdine>> response) {
                        if(response.body() != null) {
                            listaSingoliOrdini = response.body();
//                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "OK: " + listaSingoliOrdini.toString());
                        }
                        else {
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SingoloOrdine>> call, Throwable t) {
                        Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(contiFragment.getContext(), "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void setOrdinazioneinConto(ContiFragment contiFragment, String nomeAttivita, String indirizzoAttivita) {

        retrofitService = new RetrofitService();

        OrdinazioneAPI ordinazioneAPI = retrofitService.getRetrofit().create(OrdinazioneAPI.class);

        ordinazioneAPI.getAllOrdinazioniByAttivita(nomeAttivita, indirizzoAttivita)
                .enqueue(new Callback<List<Ordinazione>>() {
                    @Override
                    public void onResponse(Call<List<Ordinazione>> call, Response<List<Ordinazione>> response) {

                        if(response.body() != null) {

                            listaOrdinazioni = response.body();
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "OK: " + listaOrdinazioni.toString());

                            ArrayList<Integer> listaTavoli = new ArrayList<>();

                            Iterator<Ordinazione> listaOrdinazioniIterator = listaOrdinazioni.iterator();
                            Ordinazione ordinazioneCorrente;

                            //Inserisco solo i tavoli in cui c'è un'ordinazione
                            while(listaOrdinazioniIterator.hasNext()) {
                                ordinazioneCorrente = listaOrdinazioniIterator.next();
                                listaTavoli.add(ordinazioneCorrente.getNumeroTavolo());
                            }

                            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(contiFragment.getActivity(),
                                    android.R.layout.simple_spinner_item, listaTavoli);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            contiFragment.getSelezionaTavoloSpinner().setAdapter(adapter);

                            Ordinazione primaOrdinazione = listaOrdinazioni.get(0);
                            setListaSingoliOrdini(contiFragment, primaOrdinazione.getId_ordinazione());
                            primaOrdinazione.setListaProdotti(listaSingoliOrdini);

//                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "OK: " + listaSingoliOrdini.toString());

//                            contiFragment.setTextView(String.valueOf(primaOrdinazione.getNumeroCommensali()), String.valueOf(primaOrdinazione.calcolaTotale()));
//
//                            singoliOrdiniAdapter = new SingoliOrdiniAdapter(primaOrdinazione.getListaProdotti());
//                            contiFragment.getRecyclerView().setAdapter(singoliOrdiniAdapter);
//
//                            AlertDialog alertDialog = contiFragment.creaChiusuraContoAlertDialog(primaOrdinazione.getListaProdotti(), singoliOrdiniAdapter);
                        }
                        else {
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Ordinazione>> call, Throwable t) {
                        Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(contiFragment.getContext(), "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void addSezioneAdmin(SezioneMenu nuovaSezione, Activity activity, AggiungiSezioneFragment aggiungiSezioneFragment) {

        if(sezioneMenuAPI == null){
            sezioneMenuAPI = retrofitService.getRetrofit().create(SezioneMenuAPI.class);
        }

        nuovaSezione.setNomeAttivita(admin.getNomeAttivita());
        nuovaSezione.setIndirizzoAttivita(admin.getIndirizzoAttivita());

        sezioneMenuAPI.save(nuovaSezione)
                .enqueue(new Callback<SezioneMenu>() {
                    @Override
                    public void onResponse(Call<SezioneMenu> call, Response<SezioneMenu> response) {
                        if(response.body() != null){
                            Toast.makeText(activity, "Sezione Salvata Correttamente", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                        aggiungiSezioneFragment.sostituisciFragment();
                    }

                    @Override
                    public void onFailure(Call<SezioneMenu> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    public void addSezioneSupervisore(SezioneMenu nuovaSezione, Activity activity, AggiungiSezioneFragment aggiungiSezioneFragment) {

        if(sezioneMenuAPI == null){
            sezioneMenuAPI = retrofitService.getRetrofit().create(SezioneMenuAPI.class);
        }

        nuovaSezione.setNomeAttivita(supervisore.getNomeAttivita());
        nuovaSezione.setIndirizzoAttivita(supervisore.getIndirizzoAttivita());

        sezioneMenuAPI.save(nuovaSezione)
                .enqueue(new Callback<SezioneMenu>() {
                    @Override
                    public void onResponse(Call<SezioneMenu> call, Response<SezioneMenu> response) {
                        if(response.body() != null){
                            Toast.makeText(activity, "Sezione Salvata Correttamente", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                        aggiungiSezioneFragment.sostituisciFragment();
                    }

                    @Override
                    public void onFailure(Call<SezioneMenu> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void aggiungiProdotto(ProdottoMenu nuovoProdotto, String nomeSezione, Activity activity, AggiungiProdottoFragment aggiungiProdottoFragment) {

        if(prodottoMenuAPI == null){
            prodottoMenuAPI = retrofitService.getRetrofit().create(ProdottoMenuAPI.class);
        }

        nuovoProdotto.setNomeSezione(nomeSezione);
        prodottoMenuAPI.save(nuovoProdotto)
                .enqueue(new Callback<ProdottoMenu>() {
                    @Override
                    public void onResponse(Call<ProdottoMenu> call, Response<ProdottoMenu> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            Toast.makeText(activity, "Prodotto aggiunto correttamente", Toast.LENGTH_SHORT).show();
                        }
                        aggiungiProdottoFragment.sostituisciFragment();
                    }

                    @Override
                    public void onFailure(Call<ProdottoMenu> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void eliminaProdotto(String nomeProdottoOriginale, Activity activity, ModificaProdottoFragment modificaProdottoFragment) {

        if(prodottoMenuAPI == null){
            prodottoMenuAPI = retrofitService.getRetrofit().create(ProdottoMenuAPI.class);
        }

        prodottoMenuAPI.deleteById(nomeProdottoOriginale)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(activity, "Prodotto eliminato correttamente", Toast.LENGTH_SHORT).show();
                        modificaProdottoFragment.sostituisciFragment();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void eliminaEdAggiungiProdotto(ProdottoMenu nuovoProdotto, String nomeSezione, String nomeProdottoOriginale, Activity activity, ModificaProdottoFragment modificaProdottoFragment){

        if(prodottoMenuAPI == null){
            prodottoMenuAPI = retrofitService.getRetrofit().create(ProdottoMenuAPI.class);
        }

        prodottoMenuAPI.deleteById(nomeProdottoOriginale)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        nuovoProdotto.setNomeSezione(nomeSezione);
                        prodottoMenuAPI.save(nuovoProdotto)
                                .enqueue(new Callback<ProdottoMenu>() {
                                    @Override
                                    public void onResponse(Call<ProdottoMenu> call, Response<ProdottoMenu> response) {
                                        if(response.body() != null){
                                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                                            Toast.makeText(activity, "Prodotto salvato correttamente", Toast.LENGTH_SHORT).show();
                                        }
                                        modificaProdottoFragment.sostituisciFragment();
                                    }

                                    @Override
                                    public void onFailure(Call<ProdottoMenu> call, Throwable t) {
                                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                                        Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        modificaProdottoFragment.sostituisciFragment();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void eliminaSezione(String nomeSezione, Activity activity, ModificaSezioniFragment modificaSezioniFragment) {

        if(sezioneMenuAPI == null){
            sezioneMenuAPI = retrofitService.getRetrofit().create(SezioneMenuAPI.class);
        }

        sezioneMenuAPI.deleteById(nomeSezione)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(activity, "Sezione Eliminata Correttamente", Toast.LENGTH_SHORT).show();
                        modificaSezioniFragment.sostituisciFragment();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public void salvaAddettoSala(String nomeUtente, String email, String password, Activity activity) {

        if(addettoSalaAPI == null){
            addettoSalaAPI = retrofitService.getRetrofit().create(AddettoSalaAPI.class);
        }

        AddettoSala addettoSala = new AddettoSala(email, nomeUtente, password);
        if(admin.getNomeAttivita() != null){
            addettoSala.setNomeAttivita(admin.getNomeAttivita());
            addettoSala.setIndirizzoAttivita(admin.getIndirizzoAttivita());
        }

        addettoSalaAPI.save(addettoSala)
                .enqueue(new Callback<AddettoSala>() {
                    @Override
                    public void onResponse(Call<AddettoSala> call, Response<AddettoSala> response) {
                        if(response.body() != null){
                            Toast.makeText(activity, "Addetto Sala salvato correttamente", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: ", response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<AddettoSala> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void salvaSupervisore(String nomeUtente, String email, String password, Activity activity) {

        if(supervisoreAPI == null){
            supervisoreAPI = retrofitService.getRetrofit().create(SupervisoreAPI.class);
        }

        Supervisore supervisore = new Supervisore(email, nomeUtente, password);
        if(admin.getNomeAttivita() != null){
            supervisore.setNomeAttivita(admin.getNomeAttivita());
            supervisore.setIndirizzoAttivita(admin.getIndirizzoAttivita());
        }

        supervisoreAPI.salvataggioSupervisore(supervisore)
                .enqueue(new Callback<Supervisore>() {
                    @Override
                    public void onResponse(Call<Supervisore> call, Response<Supervisore> response) {
                        if(response.body() != null){
                            Toast.makeText(activity, "Supervisore salvato correttamente", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: ", response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Supervisore> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void salvaAdmin(Amministratore amministratore) {

        if(amministratoreAPI != null){
            amministratoreAPI = retrofitService.getRetrofit().create(AmministratoreAPI.class);
        }

        amministratoreAPI.salvataggioAdmin(amministratore)
                .enqueue(new Callback<Amministratore>() {
                    @Override
                    public void onResponse(Call<Amministratore> call, Response<Amministratore> response) {

                    }

                    @Override
                    public void onFailure(Call<Amministratore> call, Throwable t) {

                    }
                });

    }

    public void salvaAttivita(Attivita nuovaAttivita, Activity activity) {

        if(attivitaAPI == null){
            attivitaAPI = retrofitService.getRetrofit().create(AttivitaAPI.class);
        }

        attivitaAPI.save(nuovaAttivita)
                .enqueue(new Callback<Attivita>() {
                    @Override
                    public void onResponse(Call<Attivita> call, Response<Attivita> response) {
                        Toast.makeText(activity, "Salvataggio Completato Correttamente", Toast.LENGTH_SHORT).show();
                        if(admin.getNomeAttivita() == null || !(admin.getNomeAttivita().equals(response.body().getNome()))){
                            admin.setNomeAttivita(response.body().getNome());
                            admin.setIndirizzoAttivita(response.body().getIndirizzo());
                            salvaAdmin(admin);
                        }
                    }

                    @Override
                    public void onFailure(Call<Attivita> call, Throwable t) {
                        Toast.makeText(activity, "Salvataggio Fallito", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error", t);
                    }
                });
        //salvaImmagine();

    }

    public void salvaAttivitaEdAdmin(Attivita attivita, Activity activity){

        salvaAttivita(attivita, activity);

    }



    public void salvaAvviso(Avviso avviso, Activity activity){

        if(avvisoAPI == null){
            avvisoAPI = retrofitService.getRetrofit().create(AvvisoAPI.class);
        }

        avviso.setNomeAttivita(admin.getNomeAttivita());
        avviso.setIndirizzoAttivita(admin.getIndirizzoAttivita());

        avvisoAPI.salvataggioAvviso(avviso)
                .enqueue(new Callback<Avviso>() {
                    @Override
                    public void onResponse(Call<Avviso> call, Response<Avviso> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body().toString());
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Avviso> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(activity, "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void mostraReimpostaPasswordActivity(LoginActivity loginActivity) {
        Intent intent = new Intent(loginActivity, ReimpostaPasswordActivity.class);
        loginActivity.startActivity(intent);
        loginActivity.finish();
    }

    public void mostraHomeAddettoSalaActivity(LoginActivity loginActivity) {
        Intent intent = new Intent(loginActivity, HomeAddettoSalaActivity.class);
        loginActivity.startActivity(intent);
        loginActivity.finish();
    }

    public void mostraHomeSupervisoreActivity(LoginActivity loginActivity) {
        Intent intent = new Intent(loginActivity, HomeSupervisoreActivity.class);
        loginActivity.startActivity(intent);
        loginActivity.finish();
    }

    public void mostraHomeAdminActivity(LoginActivity loginActivity) {
        Intent intent = new Intent(loginActivity, HomeAdminActivity.class);
        loginActivity.startActivity(intent);
        loginActivity.finish();
    }

}
