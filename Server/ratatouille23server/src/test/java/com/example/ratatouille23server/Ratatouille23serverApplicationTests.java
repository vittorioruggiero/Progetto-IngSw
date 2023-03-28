package com.example.ratatouille23server;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Amministratore.AmministratoreDAO;
import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.Avviso.AvvisoDAO;
import com.example.ratatouille23server.Entity.SezioneMenu.SezioneMenuDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Ratatouille23serverApplicationTests {

	@Autowired
	private AttivitaDAO attivitaDAO;
	@Autowired
	private AvvisoDAO avvisoDAO;
	@Autowired
	private AmministratoreDAO amministratoreDAO;
	@Autowired
	private SezioneMenuDAO sezioneMenuDAO;

	//@Test
	void addAttivitaTest() {
		Attivita attivita = new Attivita();
		attivita.setNome("GDDFD");
		attivita.setIndirizzo("jhgjhg");
		attivita.setCapienza(50);
		attivita.setTelefono("3386341777");
		attivitaDAO.save(attivita);
	}


	//@Test
	void getAllAttivita(){
		List<Attivita> attivitaList = attivitaDAO.getAll();
		System.out.println(attivitaList);
	}

	//@Test
	void deleteAll(){
		List<Attivita> attivitaList = attivitaDAO.getAll();
		for (Attivita attivita : attivitaList) {
			attivitaDAO.delete(attivita);
		}
	}

	//@Test
	void addAdmin() {
		Amministratore admin = new Amministratore();
		admin.setEmail("123");
		admin.setPassword("123");
		admin.setNomeUtente("123");
		//amministratoreDAO.save(admin);
	}


	@Test
	void deleteById(){
		String nome = "Secondi Pesce";
		sezioneMenuDAO.deleteById(nome);

	}




}
