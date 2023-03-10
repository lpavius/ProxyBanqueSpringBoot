package org.formation.service;

import java.time.LocalDate;
import java.util.List;

import org.formation.entity.Client;
import org.formation.entity.CompteCourant;
import org.formation.entity.CompteEpargne;
import org.formation.repository.ClientRepository;
import org.formation.repository.ICompteCourantRepository;
import org.formation.repository.ICompteEpargneRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
	
	private ClientRepository clientRepository;
	private CompteServiceImpl compteService;
	
	public ClientServiceImpl(ClientRepository clientRepository, CompteServiceImpl compteService) {
		super();
		this.clientRepository = clientRepository;
		this.compteService = compteService;
	}

	@Override
	public Client createClient(Client client) {
		
		//LocalDate date = LocalDate.now();
//		CompteCourant cc = new CompteCourant(client, 500,LocalDate.now(), 1000d);
		client.setCc(addCcourant(client));
		client.setCompteEp(addCptEpargne(client));
//		cc.setClient(client);
		System.out.println(client);
		return clientRepository.save(client);
	}

	@Override
	public Client readClient(Long id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public Client updateClient(Client client) {
		System.out.println(client);
		compteService.updateCcourant(client.getCc());
		compteService.updateCEpargne(client.getCompteEp());
		return clientRepository.save(client);
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
	}

	@Override
	public List<Client> listClient() {
		return clientRepository.findAll();
	}

	@Override
	public CompteCourant addCcourant(Client client) {
		CompteCourant cc = new CompteCourant(500, LocalDate.now());
		cc.setClient(client);
		return cc;
	}

	@Override
	public CompteEpargne addCptEpargne(Client client) {
		CompteEpargne ce = new CompteEpargne(2000, LocalDate.now());
		ce.setClient(client);
		return ce;
	}

	
}
