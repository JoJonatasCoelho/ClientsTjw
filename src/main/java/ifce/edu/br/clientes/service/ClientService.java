package ifce.edu.br.clientes.service;

import ifce.edu.br.clientes.model.Client;
import ifce.edu.br.clientes.model.Phone;
import ifce.edu.br.clientes.repository.ClientRepository;
import ifce.edu.br.clientes.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public Client registerClient(Client client){
        return clientRepository.save(client);
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

    public Client updateClient(Long id, Client updatedClient){
        Client client =  clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        client = clientRepository.save(updatedClient);
        return client;
    }
}
