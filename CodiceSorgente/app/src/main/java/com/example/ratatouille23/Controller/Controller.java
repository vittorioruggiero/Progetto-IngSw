package com.example.ratatouille23.Controller;

import static com.example.ratatouille23.UI.activity.LoginActivity.getAddettoSala;
import static com.example.ratatouille23.UI.activity.LoginActivity.getAdmin;
import static com.example.ratatouille23.UI.activity.LoginActivity.getSupervisore;
import static com.example.ratatouille23.UI.activity.LoginActivity.setAddettoSala;
import static com.example.ratatouille23.UI.activity.LoginActivity.setAdmin;
import static com.example.ratatouille23.UI.activity.LoginActivity.setSupervisore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ratatouille23.UI.activity.HomeAddettoSalaActivity;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.UI.activity.HomeSupervisoreActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.UI.activity.ReimpostaPasswordActivity;
import com.example.ratatouille23.UI.fragment.AggiungiProdottoFragment;
import com.example.ratatouille23.UI.fragment.AggiungiSezioneFragment;
import com.example.ratatouille23.UI.fragment.ContiFragment;
import com.example.ratatouille23.UI.fragment.GraficoStatisticaFragment;
import com.example.ratatouille23.UI.fragment.HomeAddettoSalaFragment;
import com.example.ratatouille23.UI.fragment.HomeAdminFragment;
import com.example.ratatouille23.UI.fragment.HomeSupervisoreFragment;
import com.example.ratatouille23.UI.fragment.ModificaProdottoFragment;
import com.example.ratatouille23.UI.fragment.ModificaSezioniFragment;
import com.example.ratatouille23.UI.fragment.OrdinazioniFragment;
import com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment;
import com.example.ratatouille23.UI.fragment.VisualizzaMenuFragment;
import com.example.ratatouille23.UI.fragment.VisualizzaStatisticheFragment;
import com.example.ratatouille23.entity.AddettoSala;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.Avviso;
import com.example.ratatouille23.entity.Conto;
import com.example.ratatouille23.entity.Immagine;
import com.example.ratatouille23.entity.Ordinazione;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.entity.SingoloOrdine;
import com.example.ratatouille23.entity.Supervisore;
import com.example.ratatouille23.retrofit.API.AddettoSalaAPI;
import com.example.ratatouille23.retrofit.API.AmministratoreAPI;
import com.example.ratatouille23.retrofit.API.AttivitaAPI;
import com.example.ratatouille23.retrofit.API.AvvisoAPI;
import com.example.ratatouille23.retrofit.API.ContoAPI;
import com.example.ratatouille23.retrofit.API.ImmagineAPI;
import com.example.ratatouille23.retrofit.API.OrdinazioneAPI;
import com.example.ratatouille23.retrofit.API.ProdottoMenuAPI;
import com.example.ratatouille23.retrofit.API.SezioneMenuAPI;
import com.example.ratatouille23.retrofit.API.SingoloOrdineAPI;
import com.example.ratatouille23.retrofit.API.SupervisoreAPI;
import com.example.ratatouille23.retrofit.RetrofitService;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
    private SingoloOrdineAPI singoloOrdineAPI;
    private OrdinazioneAPI ordinazioneAPI;
    private ImmagineAPI immagineAPI;
    private ContoAPI contoAPI;
    private AttivitaAPI attivitaAPI;
    private AvvisoAPI avvisoAPI;
    private AddettoSala addettoSala;
    private RetrofitService retrofitService;
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
                            setAddettoSala(addettoSala);
                            if(addettoSala != null){
                                if(addettoSala.getPrimoAccesso() && addettoSala.getPassword().equals(password)){
                                    mostraReimpostaPasswordActivity(loginActivity);
                                }else{
                                    if(username.equals(addettoSala.getNomeUtente())
                                            && password.equals(addettoSala.getPassword())){

                                        Bundle bundle = new Bundle();
                                        bundle.putString(FirebaseAnalytics.Param.METHOD, "login addetto alla sala");
                                        FirebaseAnalytics.getInstance(loginActivity).logEvent(FirebaseAnalytics.Event.LOGIN, bundle);

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
                            setSupervisore(supervisore);
                            if(supervisore != null){
                                if(supervisore.getPrimoAccesso() && supervisore.getPassword().equals(password)){
                                    mostraReimpostaPasswordActivity(loginActivity);
                                }else{
                                    if(username.equals(supervisore.getNomeUtente())
                                            && password.equals(supervisore.getPassword())){

                                        Bundle bundle = new Bundle();
                                        bundle.putString(FirebaseAnalytics.Param.METHOD, "login supervisore");
                                        FirebaseAnalytics.getInstance(loginActivity).logEvent(FirebaseAnalytics.Event.LOGIN, bundle);

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
                            setAdmin(admin);
                            if(admin != null){
                                if(username.equals(admin.getNomeUtente())
                                        && password.equals(admin.getPassword())){

                                    Bundle bundle = new Bundle();
                                    bundle.putString(FirebaseAnalytics.Param.METHOD, "login admin");
                                    FirebaseAnalytics.getInstance(loginActivity).logEvent(FirebaseAnalytics.Event.LOGIN, bundle);

                                    mostraHomeAdminActivity(loginActivity, admin);
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

        sezioneMenuAPI.getSezioniByAttivita(admin.getIdAttivita())
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

    public void checkSezioniSupervisore(Activity activity, PersonalizzaMenuFragment fragmentPersonalizzaMenu) {

        if(sezioneMenuAPI == null){
            sezioneMenuAPI = retrofitService.getRetrofit().create(SezioneMenuAPI.class);
        }

        sezioneMenuAPI.getSezioniByAttivita(supervisore.getIdAttivita())
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

        sezioneMenuAPI.getSezioniByAttivita(addettoSala.getIdAttivita())
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

    public void checkAttivita(int idAttivita, HomeAdminFragment homeAdminFragment){

        if(attivitaAPI == null){
            attivitaAPI = retrofitService.getRetrofit().create(AttivitaAPI.class);
        }

        attivitaAPI.getAttivitaById(idAttivita)
                .enqueue(new Callback<Attivita>() {
                    @Override
                    public void onResponse(Call<Attivita> call, Response<Attivita> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            homeAdminFragment.setAttivita(response.body());
                            //checkImmagine(response.body().getId(), homeAdminFragment);
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

    public void checkImmagine(int idAttivita, HomeAdminFragment homeAdminFragment){
        if(immagineAPI == null){
            immagineAPI = retrofitService.getRetrofit().create(ImmagineAPI.class);
        }

        immagineAPI.getByIdAttivita(idAttivita)
                .enqueue(new Callback<Immagine>() {
                    @Override
                    public void onResponse(Call<Immagine> call, Response<Immagine> response) {
                        if(response.body() != null){
                            if(!(response.body().getUri().equals(""))){
                                Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                                homeAdminFragment.setUri(response.body());
                            }else{
                                Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                            }
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Immagine> call, Throwable t) {

                    }
                });
    }

    public void checkAttivitaAddettoSala(int idAttivita, OrdinazioniFragment ordinazioniFragment){

        if(attivitaAPI == null){
            attivitaAPI = retrofitService.getRetrofit().create(AttivitaAPI.class);
        }

        attivitaAPI.getAttivitaById(idAttivita)
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


    public void checkAttivitaSupervisore(int idAttivita, ContiFragment contiFragment){

        if(attivitaAPI == null){
            attivitaAPI = retrofitService.getRetrofit().create(AttivitaAPI.class);
        }

        attivitaAPI.getAttivitaById(idAttivita)
                .enqueue(new Callback<Attivita>() {
                    @Override
                    public void onResponse(Call<Attivita> call, Response<Attivita> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            contiFragment.setTavoliAttivita(response.body());
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

    public void getOrdinazioneByTavolo(ContiFragment contiFragment, int idAttivita, int tavolo){
        retrofitService = new RetrofitService();

        OrdinazioneAPI ordinazioneAPI = retrofitService.getRetrofit().create(OrdinazioneAPI.class);
        ordinazioneAPI.getOrdinazioneByTavolo(idAttivita, tavolo)
                .enqueue(new Callback<Ordinazione>() {
                    @Override
                    public void onResponse(Call<Ordinazione> call, Response<Ordinazione> response) {
                        if(response.body() != null){
                            setProdottiOrdinazione(response.body(), contiFragment);
                        }else{
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                            contiFragment.setSingoliOrdiniRecyclerAdapter(null);
                            //contiFragment.notifyDataChanged();
                            contiFragment.setTextView("", "");
                        }
                    }

                    @Override
                    public void onFailure(Call<Ordinazione> call, Throwable t) {

                    }
                });
    }

    public void setProdottiOrdinazione(Ordinazione ordinazione, ContiFragment contiFragment){
        retrofitService = new RetrofitService();

        SingoloOrdineAPI singoloOrdineAPI = retrofitService.getRetrofit().create(SingoloOrdineAPI.class);
        singoloOrdineAPI.getAllSingoliOrdiniByOrdinazione(ordinazione.getId_ordinazione())
                .enqueue(new Callback<List<SingoloOrdine>>() {
                    @Override
                    public void onResponse(Call<List<SingoloOrdine>> call, Response<List<SingoloOrdine>> response) {
                        if(response.body() != null){
                            setProdottiSingoloOrdine(response.body(), contiFragment, ordinazione);
                        }else{
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }


                    @Override
                    public void onFailure(Call<List<SingoloOrdine>> call, Throwable t) {

                    }
                });
    }

    public void setProdottiSingoloOrdine(List<SingoloOrdine> singoliOrdini, ContiFragment contiFragment, Ordinazione ordinazione){
        if(prodottoMenuAPI == null){
            prodottoMenuAPI = retrofitService.getRetrofit().create(ProdottoMenuAPI.class);
        }
        Iterator<SingoloOrdine> iterator = singoliOrdini.iterator();

        while(iterator.hasNext()){
            SingoloOrdine singoloOrdine = iterator.next();
            if(iterator.hasNext()){
                prodottoMenuAPI.getProdottoById(singoloOrdine.getNomeProdotto())
                        .enqueue(new Callback<ProdottoMenu>() {
                            @Override
                            public void onResponse(Call<ProdottoMenu> call, Response<ProdottoMenu> response) {
                                if (response.body() != null) {
                                    ProdottoMenu prodottoMenu = new ProdottoMenu();
                                    prodottoMenu.setNomeProdotto(response.body().getNomeProdotto());
                                    prodottoMenu.setCosto(response.body().getCosto());
                                    singoloOrdine.setProdottoMenu(prodottoMenu);
                                    contiFragment.setSingoliOrdiniRecyclerAdapter(singoliOrdini);
                                    Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "PROVA: " + prodottoMenu);

                                } else {
                                    Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<ProdottoMenu> call, Throwable t) {

                            }

                        });
            }else{
                prodottoMenuAPI.getProdottoById(singoloOrdine.getNomeProdotto())
                        .enqueue(new Callback<ProdottoMenu>() {
                            @Override
                            public void onResponse(Call<ProdottoMenu> call, Response<ProdottoMenu> response) {
                                if (response.body() != null) {
                                    ProdottoMenu prodottoMenu = new ProdottoMenu();
                                    prodottoMenu.setNomeProdotto(response.body().getNomeProdotto());
                                    prodottoMenu.setCosto(response.body().getCosto());
                                    singoloOrdine.setProdottoMenu(prodottoMenu);
                                    Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "PROVA: " + prodottoMenu);
                                    contiFragment.notifyDataChanged();
                                    ordinazione.setListaProdotti(singoliOrdini);
                                    contiFragment.setTextView(String.valueOf(ordinazione.getNumeroCommensali()), String.valueOf(ordinazione.calcolaTotale()));
                                    contiFragment.setChiusuraContoAlertDialog(contiFragment.creaChiusuraContoAlertDialog(singoliOrdini));
                                    Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "PROVA: " + singoliOrdini);

                                } else {
                                    Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                                }

                            }

                            @Override
                            public void onFailure(Call<ProdottoMenu> call, Throwable t) {

                            }
                        });
            }


        }

    }

    public void chiusuraConto(int tavolo, ContiFragment contiFragment, int idAttivita, Double totale){
        retrofitService = new RetrofitService();

        OrdinazioneAPI ordinazioneAPI = retrofitService.getRetrofit().create(OrdinazioneAPI.class);
        ordinazioneAPI.getOrdinazioneByTavolo(idAttivita, tavolo)
                .enqueue(new Callback<Ordinazione>() {
                    @Override
                    public void onResponse(Call<Ordinazione> call, Response<Ordinazione> response) {
                        if(response.body() != null){
                            //Toast.makeText(contiFragment.getActivity(), "OK: " + response.body().calcolaTotale(), Toast.LENGTH_SHORT).show();
                            //setProdottiOrdinazioneFinale(response.body(), contiFragment);
                            salvaContoEdEliminaOrdinazione(contiFragment, response.body(), totale);
                        }else{
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                            Toast.makeText(contiFragment.getActivity(), "Nessun conto da chiudere", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Ordinazione> call, Throwable t) {

                    }
                });
    }

    public void visualizzaConto(int tavolo, ContiFragment contiFragment, int idAttivita){
        retrofitService = new RetrofitService();

        OrdinazioneAPI ordinazioneAPI = retrofitService.getRetrofit().create(OrdinazioneAPI.class);
        ordinazioneAPI.getOrdinazioneByTavolo(idAttivita, tavolo)
                .enqueue(new Callback<Ordinazione>() {
                    @Override
                    public void onResponse(Call<Ordinazione> call, Response<Ordinazione> response) {
                        if(response.body() != null){
                            //Toast.makeText(contiFragment.getActivity(), "OK: " + response.body().calcolaTotale(), Toast.LENGTH_SHORT).show();
                            setProdottiOrdinazioneVisualizza(response.body(), contiFragment);
                            //salvaContoEdEliminaOrdinazione(contiFragment, response.body(), totale);
                        }else{
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                            Toast.makeText(contiFragment.getActivity(), "Nessun conto da chiudere", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Ordinazione> call, Throwable t) {

                    }
                });
    }

    public void setProdottiOrdinazioneVisualizza(Ordinazione ordinazione, ContiFragment contiFragment){
        retrofitService = new RetrofitService();

        SingoloOrdineAPI singoloOrdineAPI = retrofitService.getRetrofit().create(SingoloOrdineAPI.class);
        singoloOrdineAPI.getAllSingoliOrdiniByOrdinazione(ordinazione.getId_ordinazione())
                .enqueue(new Callback<List<SingoloOrdine>>() {
                    @Override
                    public void onResponse(Call<List<SingoloOrdine>> call, Response<List<SingoloOrdine>> response) {
                        if(response.body() != null){
                            setProdottiSingoloOrdineVisualizza(response.body(), contiFragment, ordinazione);
                        }else{
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }


                    @Override
                    public void onFailure(Call<List<SingoloOrdine>> call, Throwable t) {

                    }
                });
    }

    public void setProdottiSingoloOrdineVisualizza(List<SingoloOrdine> singoliOrdini, ContiFragment contiFragment, Ordinazione ordinazione){
        if(prodottoMenuAPI == null){
            prodottoMenuAPI = retrofitService.getRetrofit().create(ProdottoMenuAPI.class);
        }
        Iterator<SingoloOrdine> iterator = singoliOrdini.iterator();

        while(iterator.hasNext()){
            SingoloOrdine singoloOrdine = iterator.next();
            if(iterator.hasNext()){
                prodottoMenuAPI.getProdottoById(singoloOrdine.getNomeProdotto())
                        .enqueue(new Callback<ProdottoMenu>() {
                            @Override
                            public void onResponse(Call<ProdottoMenu> call, Response<ProdottoMenu> response) {
                                if (response.body() != null) {
                                    ProdottoMenu prodottoMenu = new ProdottoMenu();
                                    prodottoMenu.setNomeProdotto(response.body().getNomeProdotto());
                                    prodottoMenu.setCosto(response.body().getCosto());
                                    singoloOrdine.setProdottoMenu(prodottoMenu);
                                    Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "PROVA: " + prodottoMenu);

                                } else {
                                    Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<ProdottoMenu> call, Throwable t) {

                            }

                        });
            }else{
                prodottoMenuAPI.getProdottoById(singoloOrdine.getNomeProdotto())
                        .enqueue(new Callback<ProdottoMenu>() {
                            @Override
                            public void onResponse(Call<ProdottoMenu> call, Response<ProdottoMenu> response) {
                                if (response.body() != null) {
                                    ProdottoMenu prodottoMenu = new ProdottoMenu();
                                    prodottoMenu.setNomeProdotto(response.body().getNomeProdotto());
                                    prodottoMenu.setCosto(response.body().getCosto());
                                    singoloOrdine.setProdottoMenu(prodottoMenu);
                                    ordinazione.setListaProdotti(singoliOrdini);
                                    contiFragment.sostituisciFragment(contiFragment.preparaBundle(singoliOrdini));

                                    Bundle bundle = new Bundle();
                                    FirebaseAnalytics.getInstance(contiFragment.getActivity()).logEvent("conto_visualizzato", bundle);

                                    Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "PROVA: " + singoliOrdini);

                                } else {
                                    Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                                }

                            }

                            @Override
                            public void onFailure(Call<ProdottoMenu> call, Throwable t) {

                            }
                        });
            }


        }

    }

    public void salvaContoEdEliminaOrdinazione(ContiFragment contiFragment, Ordinazione ordinazione, Double totale){
        retrofitService = new RetrofitService();

        LocalDate dataCorrente = LocalDate.from(java.time.LocalDateTime.now());

        ContoAPI contoAPI = retrofitService.getRetrofit().create(ContoAPI.class);
        Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "CAMPI: " + java.sql.Date.valueOf(dataCorrente.toString()) + " " + totale);

        contoAPI.saveConCampi(java.sql.Date.valueOf(dataCorrente.toString()), totale)
                .enqueue(new Callback<Conto>() {
                    @Override
                    public void onResponse(Call<Conto> call, Response<Conto> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            eliminaOrdinazione(contiFragment, ordinazione.getId_ordinazione());
                        }else{
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "ERROR: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Conto> call, Throwable t) {
                        Toast.makeText(contiFragment.getActivity(), "Controlla connessione1", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "ERROR: ", t);
                    }
                });

    }

    public void eliminaOrdinazione(ContiFragment contiFragment, int id_ordinazione) {

        OrdinazioneAPI ordinazioneAPI = retrofitService.getRetrofit().create(OrdinazioneAPI.class);
        ordinazioneAPI.deleteById(id_ordinazione)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        Bundle bundle = new Bundle();
                        FirebaseAnalytics.getInstance(contiFragment.getActivity()).logEvent("conto_chiuso", bundle);

                        Toast.makeText(contiFragment.getActivity(), "Ordinazione eliminata e conto salvato correttamente", Toast.LENGTH_SHORT).show();
                        contiFragment.notifyDataChanged();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(contiFragment.getActivity(), "Controlla connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void addSezioneAdmin(SezioneMenu nuovaSezione, Activity activity, AggiungiSezioneFragment aggiungiSezioneFragment) {

        if(sezioneMenuAPI == null){
            sezioneMenuAPI = retrofitService.getRetrofit().create(SezioneMenuAPI.class);
        }

        nuovaSezione.setIdAttivita(admin.getIdAttivita());

        sezioneMenuAPI.save(nuovaSezione)
                .enqueue(new Callback<SezioneMenu>() {
                    @Override
                    public void onResponse(Call<SezioneMenu> call, Response<SezioneMenu> response) {
                        if(response.body() != null){

                            Bundle bundle = new Bundle();
                            FirebaseAnalytics.getInstance(activity).logEvent("sezione_aggiunta", bundle);

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

        nuovaSezione.setIdAttivita(supervisore.getIdAttivita());

        sezioneMenuAPI.save(nuovaSezione)
                .enqueue(new Callback<SezioneMenu>() {
                    @Override
                    public void onResponse(Call<SezioneMenu> call, Response<SezioneMenu> response) {
                        if(response.body() != null){

                            Bundle bundle = new Bundle();
                            FirebaseAnalytics.getInstance(activity).logEvent("sezione_aggiunta", bundle);

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

                            Bundle bundle = new Bundle();
                            FirebaseAnalytics.getInstance(activity).logEvent("prodotto_aggiunto", bundle);

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

                        Bundle bundle = new Bundle();
                        FirebaseAnalytics.getInstance(activity).logEvent("prodotto_eliminato", bundle);

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

                        Bundle bundle = new Bundle();
                        FirebaseAnalytics.getInstance(activity).logEvent("sezione_eliminata", bundle);

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
        if(admin.getIdAttivita() != 0){
            addettoSala.setIdAttivita(admin.getIdAttivita());
            addettoSalaAPI.save(addettoSala)
                    .enqueue(new Callback<AddettoSala>() {
                        @Override
                        public void onResponse(Call<AddettoSala> call, Response<AddettoSala> response) {
                            if(response.body() != null){

                                Bundle bundle = new Bundle();
                                bundle.putString("tipologia_utente", "addetto alla sala");
                                FirebaseAnalytics.getInstance(activity).logEvent("utente_creato", bundle);

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
        }else{
            Toast.makeText(activity, "Inserire dettagli attività", Toast.LENGTH_SHORT).show();
        }



    }

    public void salvaSupervisore(String nomeUtente, String email, String password, Activity activity) {

        if(supervisoreAPI == null){
            supervisoreAPI = retrofitService.getRetrofit().create(SupervisoreAPI.class);
        }

        Supervisore supervisore = new Supervisore(email, nomeUtente, password);
        if(admin.getIdAttivita() != 0){
            supervisore.setIdAttivita(admin.getIdAttivita());
            supervisoreAPI.salvataggioSupervisore(supervisore)
                    .enqueue(new Callback<Supervisore>() {
                        @Override
                        public void onResponse(Call<Supervisore> call, Response<Supervisore> response) {
                            if(response.body() != null){

                                Bundle bundle = new Bundle();
                                bundle.putString("tipologia_utente", "supervisore");
                                FirebaseAnalytics.getInstance(activity).logEvent("utente_creato", bundle);

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
        }else{
            Toast.makeText(activity, "Inserire dettagli attività", Toast.LENGTH_SHORT).show();
        }



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

    public void salvaAttivita(int id, String nome, String indirizzo, String telefono, int capienza, Activity activity) {

        if(attivitaAPI == null){
            attivitaAPI = retrofitService.getRetrofit().create(AttivitaAPI.class);
        }

        attivitaAPI.update(id, nome, indirizzo, telefono, capienza)
                .enqueue(new Callback<Attivita>() {
                    @Override
                    public void onResponse(Call<Attivita> call, Response<Attivita> response) {
                        if (response.body() != null) {

                            Bundle bundle = new Bundle();
                            FirebaseAnalytics.getInstance(activity).logEvent("attività_modificata", bundle);

                            Toast.makeText(activity, "Salvataggio Completato Correttamente", Toast.LENGTH_SHORT).show();
                            if(admin.getIdAttivita() == 0 || !(admin.getIdAttivita() == response.body().getId())){
                                admin.setIdAttivita(response.body().getId());
                                salvaAdmin(admin);
                            }
                        }else{
                            Toast.makeText(activity, "Attività già esistente", Toast.LENGTH_SHORT).show();
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

    public void salvaAttivitaEdAdmin(int id, String nome, String indirizzo, String telefono, int capienza, Activity activity){

        salvaAttivita(id, nome, indirizzo, telefono, capienza, activity);

    }



    public void salvaAvvisoSupervisore(Avviso avviso, Activity activity){

        if(avvisoAPI == null){
            avvisoAPI = retrofitService.getRetrofit().create(AvvisoAPI.class);

        }
        supervisoreAPI = retrofitService.getRetrofit().create(SupervisoreAPI.class);

        supervisoreAPI.getAllSupervisore()
                        .enqueue(new Callback<List<Supervisore>>() {
                            @Override
                            public void onResponse(Call<List<Supervisore>> call, Response<List<Supervisore>> response) {
                                if(response.body() != null){
                                    for(Supervisore supervisore : response.body()){
                                        avviso.setEmail(supervisore.getEmail());
                                        avvisoAPI.salvataggioAvviso(avviso)
                                                .enqueue(new Callback<Avviso>() {
                                                    @Override
                                                    public void onResponse(Call<Avviso> call, Response<Avviso> response) {

                                                        Bundle bundle = new Bundle();
                                                        FirebaseAnalytics.getInstance(activity).logEvent("avviso_creato", bundle);

                                                    }

                                                    @Override
                                                    public void onFailure(Call<Avviso> call, Throwable t) {

                                                    }
                                                });
                                    }
                                }
                                    //avvisoAPI.saveAll(response.body(), avviso);
                                else{
                                    Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                                    //Toast.makeText(activity, "Avviso troppo lungo!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Supervisore>> call, Throwable t) {

                            }
                        });
    }

    public void salvaAvvisoAddettoSala(Avviso avviso, Activity activity) {

        if (avvisoAPI == null) {
            avvisoAPI = retrofitService.getRetrofit().create(AvvisoAPI.class);

        }
        addettoSalaAPI = retrofitService.getRetrofit().create(AddettoSalaAPI.class);

        addettoSalaAPI.getAllAddettoSala()
                .enqueue(new Callback<List<AddettoSala>>() {
                    @Override
                    public void onResponse(Call<List<AddettoSala>> call, Response<List<AddettoSala>> response) {
                        if (response.body() != null){
                            for(AddettoSala addettoSala : response.body()){
                                avviso.setEmail(addettoSala.getEmail());
                                avvisoAPI.salvataggioAvviso(avviso)
                                        .enqueue(new Callback<Avviso>() {
                                            @Override
                                            public void onResponse(Call<Avviso> call, Response<Avviso> response) {
                                                Bundle bundle = new Bundle();
                                                FirebaseAnalytics.getInstance(activity).logEvent("avviso_creato", bundle);
                                            }

                                            @Override
                                            public void onFailure(Call<Avviso> call, Throwable t) {

                                            }
                                        });
                            }
                        }else {
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<AddettoSala>> call, Throwable t) {

                    }
                });
    }

    public void checkAvvisiAddettoSala(String email, HomeAddettoSalaFragment homeAddettoSalaFragment){

        if(avvisoAPI == null){
            avvisoAPI = retrofitService.getRetrofit().create(AvvisoAPI.class);
        }

        avvisoAPI.getAllAvvisiByEmail(email)
                .enqueue(new Callback<List<Avviso>>() {
                    @Override
                    public void onResponse(Call<List<Avviso>> call, Response<List<Avviso>> response) {
                        if(response.body() != null){
                            homeAddettoSalaFragment.setAvvisiRecyclerView(response.body());
                            homeAddettoSalaFragment.notifyDataChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Avviso>> call, Throwable t) {

                    }
                });


    }

    public void checkAvvisiSupervisore(String email, HomeSupervisoreFragment homeSupervisoreFragment){

        if(avvisoAPI == null){
            avvisoAPI = retrofitService.getRetrofit().create(AvvisoAPI.class);
        }

        avvisoAPI.getAllAvvisiByEmail(email)
                .enqueue(new Callback<List<Avviso>>() {
                    @Override
                    public void onResponse(Call<List<Avviso>> call, Response<List<Avviso>> response) {
                        if(response.body() != null){


                            homeSupervisoreFragment.setAvvisiRecyclerView(response.body());
                            homeSupervisoreFragment.notifyDataChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Avviso>> call, Throwable t) {

                    }
                });

    }

    public void eliminaAvvisoSupervisore(Avviso avviso, HomeSupervisoreFragment fragment, String email) {

        if(avvisoAPI == null){
            avvisoAPI = retrofitService.getRetrofit().create(AvvisoAPI.class);
        }

        avvisoAPI.deleteById(avviso.getId())
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        Bundle bundle = new Bundle();
                        bundle.putString("utente", "supervisore");
                        FirebaseAnalytics.getInstance(fragment.getActivity()).logEvent("avviso_visualizzato", bundle);

                        checkAvvisiSupervisore(email, fragment);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

    }

    public void eliminaAvvisoAddettoSala(Avviso avviso, HomeAddettoSalaFragment fragment, String email) {

        if(avvisoAPI == null){
            avvisoAPI = retrofitService.getRetrofit().create(AvvisoAPI.class);
        }

        avvisoAPI.deleteById(avviso.getId())
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        Bundle bundle = new Bundle();
                        bundle.putString("utente", "addetto alla sala");
                        FirebaseAnalytics.getInstance(fragment.getActivity()).logEvent("avviso_visualizzato", bundle);

                        checkAvvisiAddettoSala(email, fragment);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

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

    public void mostraHomeAdminActivity(LoginActivity loginActivity, Amministratore admin) {
        Gson gson = new Gson();
        String adminJson = gson.toJson(admin);
        Intent intent = new Intent(loginActivity, HomeAdminActivity.class);
        intent.putExtra("admin", adminJson);
        loginActivity.startActivity(intent);
        loginActivity.finish();
    }

    public void checkOrdinazione(int tavolo, int commensali, List<SingoloOrdine> prodottiOrdine, OrdinazioniFragment ordinazioniFragment,
                                 int idAttivita) {

        if(ordinazioneAPI == null){
            ordinazioneAPI = retrofitService.getRetrofit().create(OrdinazioneAPI.class);
        }

        ordinazioneAPI.getOrdinazioneByTavolo(idAttivita, tavolo)
                .enqueue(new Callback<Ordinazione>() {
                    @Override
                    public void onResponse(Call<Ordinazione> call, Response<Ordinazione> response) {
                        if(response.body() == null){
                            salvaOrdinazione(tavolo, commensali, prodottiOrdine, ordinazioniFragment, idAttivita);
                        }else{
                            Toast.makeText(ordinazioniFragment.getActivity(), "È già presente un'ordinazione", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Ordinazione> call, Throwable t) {
                        Toast.makeText(ordinazioniFragment.getActivity(), "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void salvaOrdinazione(int tavolo, int commensali, List<SingoloOrdine> prodottiOrdine, OrdinazioniFragment ordinazioniFragment,
                                  int idAttivita) {
        if(ordinazioneAPI == null){
            ordinazioneAPI = retrofitService.getRetrofit().create(OrdinazioneAPI.class);
        }

        ordinazioneAPI.saveConCampi(tavolo, commensali, idAttivita)
                .enqueue(new Callback<Ordinazione>() {
                    @Override
                    public void onResponse(Call<Ordinazione> call, Response<Ordinazione> response) {
                        if(response.body() != null){
                            salvaSingoliOrdini(prodottiOrdine, response.body().getId_ordinazione(), ordinazioniFragment);
                        }
                    }

                    @Override
                    public void onFailure(Call<Ordinazione> call, Throwable t) {
                        Toast.makeText(ordinazioniFragment.getActivity(), "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void salvaSingoliOrdini(List<SingoloOrdine> prodottiOrdine, int idOrdinazione, OrdinazioniFragment ordinazioniFragment) {

        if(singoloOrdineAPI == null){
            singoloOrdineAPI = retrofitService.getRetrofit().create(SingoloOrdineAPI.class);
        }

        for(SingoloOrdine singoloOrdine : prodottiOrdine){
            singoloOrdine.setIdOrdinazione(idOrdinazione);
        }

        singoloOrdineAPI.saveAll(prodottiOrdine)
                .enqueue(new Callback<List<SingoloOrdine>>() {
                    @Override
                    public void onResponse(Call<List<SingoloOrdine>> call, Response<List<SingoloOrdine>> response) {
                        if(response.body() != null){

                            Bundle bundle = new Bundle();
                            FirebaseAnalytics.getInstance(ordinazioniFragment.getActivity()).logEvent("ordinazione_creata", bundle);

                            Toast.makeText(ordinazioniFragment.getActivity(), "Ordinazione salvata correttamente", Toast.LENGTH_SHORT).show();
                            ordinazioniFragment.clearProdotti();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SingoloOrdine>> call, Throwable t) {
                        Toast.makeText(ordinazioniFragment.getActivity(), "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void cercaContiPerDate(java.sql.Date dataInizio, java.sql.Date dataFine, VisualizzaStatisticheFragment visualizzaStatisticheFragment) {

        if(contoAPI == null){
            contoAPI = retrofitService.getRetrofit().create(ContoAPI.class);
        }

        contoAPI.getAllContoByDate(dataInizio, dataFine)
                .enqueue(new Callback<List<Conto>>() {
                    @Override
                    public void onResponse(Call<List<Conto>> call, Response<List<Conto>> response) {
                        if(response.body() != null){
                            Double totale = 0.0;
                            Double valoreMedio = 0.0;
                            if(!(response.body().isEmpty())){

                                Bundle bundle = new Bundle();
                                FirebaseAnalytics.getInstance(visualizzaStatisticheFragment.getActivity()).logEvent("statistiche_visualizzate", bundle);

                                Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                                Fragment fragment = GraficoStatisticaFragment.newInstance(response.body());
                                visualizzaStatisticheFragment.sostituisciFragment(fragment);
                                for(Conto conto : response.body()){
                                    totale += conto.getImporto();
                                }
                                if(totale != 0.0){
                                    valoreMedio = totale / response.body().size();
                                }
                                visualizzaStatisticheFragment.setTextView(String.valueOf(totale), String.valueOf(valoreMedio));
                            }else{
                                Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "ERROR: " + response.body());
                                visualizzaStatisticheFragment.setTextView(String.valueOf(totale), String.valueOf(valoreMedio));
                                Toast.makeText(visualizzaStatisticheFragment.getActivity(), "Nessuna statistica da visualizzare", Toast.LENGTH_SHORT).show();
                            }

                            //setStatistiche(response.body(), visualizzaStatisticheFragment);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Conto>> call, Throwable t) {

                    }
                });


    }


    public void salvaImmagine(Uri resultUri, int idAttivita, Activity activity) {

        if(immagineAPI == null){
            immagineAPI = retrofitService.getRetrofit().create(ImmagineAPI.class);
        }

        String uri = resultUri.toString();

        immagineAPI.salvaImmagine(uri, idAttivita)
                .enqueue(new Callback<Immagine>() {
                    @Override
                    public void onResponse(Call<Immagine> call, Response<Immagine> response) {
                        if(response.body() != null){

                            Bundle bundle = new Bundle();
                            FirebaseAnalytics.getInstance(activity).logEvent("immagine_caricata", bundle);

                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            //homeAdminFragment.setUri(response.body());

                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "ERROR: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Immagine> call, Throwable t) {

                    }
                });

    }
}
