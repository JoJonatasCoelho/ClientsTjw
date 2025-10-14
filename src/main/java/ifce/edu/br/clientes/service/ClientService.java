package ifce.edu.br.clientes.service;

import ifce.edu.br.clientes.model.Client;
import ifce.edu.br.clientes.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client create(Client client) {
        if (clientRepository.existsByCpf(client.getCpf())) {
            throw new IllegalArgumentException("Cliente com este CPF já existe");
        }
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new IllegalArgumentException("Cliente com este email já existe");
        }
        return clientRepository.save(client);
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findByCpf(String cpf) {
        Client client = clientRepository.findByCpf(cpf);
        if (client == null) {
            throw new IllegalArgumentException("Cliente com CPF " + cpf + " não encontrado");
        }
        return client;
    }

    public Client findByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        if (client == null) {
            throw new IllegalArgumentException("Cliente com email " + email + " não encontrado");
        }
        return client;
    }

    // ============ UPDATE ============
    public Client update(Long id, Client newClient) {
        Optional<Client> clientExists = clientRepository.findById(id);

        if (clientExists.isEmpty()) {
            throw new IllegalArgumentException("Cliente com ID " + id + " não encontrado");
        }

        Client client = clientExists.get();

        // Atualizar nome
        if (newClient.getName() != null && !newClient.getName().isEmpty()) {
            client.setName(newClient.getName());
        }

        // Atualizar email com validação
        if (newClient.getEmail() != null && !newClient.getEmail().isEmpty()) {
            if (clientRepository.existsByEmail(newClient.getEmail()) &&
                    !client.getEmail().equals(newClient.getEmail())) {
                throw new IllegalArgumentException("Email já cadastrado para outro cliente");
            }
            client.setEmail(newClient.getEmail());
        }

        // Atualizar telefones
        if (newClient.getPhone() != null && !newClient.getPhone().isEmpty()) {
            client.setPhone(newClient.getPhone());
        }

        // Atualizar endereço
        if (newClient.getAddress() != null) {
            client.setAddress(newClient.getAddress());
        }

        return clientRepository.save(client);
    }

    // ============ DELETE ============
    public void deletar(Long id) {
        if (!clientRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Cliente com ID " + id + " não encontrado");
        }
        clientRepository.deleteById(id);
    }

    public void deletarPorCpf(String cpf) {
        Client client = clientRepository.findByCpf(cpf);
        if (client == null) {
            throw new IllegalArgumentException("Cliente com CPF " + cpf + " não encontrado");
        }
        clientRepository.deleteById(client.getId());
    }

    // ============ FILTROS ============
    public List<Client> filtrarPorNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        return clientRepository.filterByName(nome);
    }

    public List<Client> filtrarPorCpfContains(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio");
        }
        return clientRepository.filterByCpfContains(cpf);
    }

    public List<Client> filtrarPorEmailContains(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        return clientRepository.filterByEmailContains(email);
    }

    public List<Client> filtrarPorNomeEEmail(String nome, String email) {
        if ((nome == null || nome.isEmpty()) || (email == null || email.isEmpty())) {
            throw new IllegalArgumentException("Nome e email não podem ser vazios");
        }
        return clientRepository.filterByNameAndEmail(nome, email);
    }

    public List<Client> filtrarPorNomeECpf(String nome, String cpf) {
        if ((nome == null || nome.isEmpty()) || (cpf == null || cpf.isEmpty())) {
            throw new IllegalArgumentException("Nome e CPF não podem ser vazios");
        }
        return clientRepository.filterByNameAndCpf(nome, cpf);
    }

    public long contarClientes() {
        return clientRepository.countClients();
    }

    public boolean existsByCpf(String cpf) {
        return clientRepository.existsByCpf(cpf);
    }

    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    public List<Client> findClientsWithAddress() {
        return clientRepository.findClientsWithAddress();
    }

    public List<Client> findClientsWithoutAddress() {
        return clientRepository.findClientsWithoutAddress();
    }

    public List<Client> findClientsWithPhone() {
        return clientRepository.findClientsWithPhone();
    }

    public List<Client> findClientsWithoutPhone() {
        return clientRepository.findClientsWithoutPhone();
    }

    public List<Client> findClientsWithAddressAndPhone() {
        return clientRepository.findClientsWithAddressAndPhone();
    }

    public List<Client> findAsc() {
        return clientRepository.findAllOrderedByName();
    }

    public List<Client> filterByNameWithLimit(String nome, int limit) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("Limite deve ser maior que 0");
        }
        return clientRepository.filterByNameWithLimit(nome, limit);
    }
}
