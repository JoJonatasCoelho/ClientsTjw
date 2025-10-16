package ifce.edu.br.clientes.controller;

import ifce.edu.br.clientes.model.Client;
import ifce.edu.br.clientes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping ("/list")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    // ============ BUSCAR POR ID ============
    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Optional<Client> client = clientService.findById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ============ ATUALIZAR ============
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client newClient) {
        try {
            Client updatedClient = clientService.update(id, newClient);
            return ResponseEntity.ok(updatedClient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // ============ DELETAR POR ID ============
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            clientService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ============ DELETAR POR CPF ============
    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> deleteByCpf(@PathVariable String cpf) {
        try {
            clientService.deletarPorCpf(cpf);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ============ FILTRAR POR NOME ============
    @GetMapping("/filterByName")
    public ResponseEntity<List<Client>> filterByName(@RequestParam String name) {
        try {
            List<Client> clients = clientService.filtrarPorNome(name);
            return ResponseEntity.ok(clients);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // ============ FILTRAR POR CPF ============
    @GetMapping("/filterByCpf")
    public ResponseEntity<List<Client>> filterByCpf(@RequestParam String cpf) {
        try {
            List<Client> clients = clientService.filtrarPorCpfContains(cpf);
            return ResponseEntity.ok(clients);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // ============ FILTRAR POR EMAIL ============
    @GetMapping("/filterByEmail")
    public ResponseEntity<List<Client>> filterByEmail(@RequestParam String email) {
        try {
            List<Client> clients = clientService.filtrarPorEmailContains(email);
            return ResponseEntity.ok(clients);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // ============ FILTRAR POR NOME E EMAIL ============
    @GetMapping("/filterByNameAndEmail")
    public ResponseEntity<List<Client>> filterByNameAndEmail(@RequestParam String name, @RequestParam String email) {
        try {
            List<Client> clients = clientService.filtrarPorNomeEEmail(name, email);
            return ResponseEntity.ok(clients);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // ============ FILTRAR POR NOME E CPF ============
    @GetMapping("/filterByNameAndCpf")
    public ResponseEntity<List<Client>> filterByNameAndCpf(@RequestParam String name, @RequestParam String cpf) {
        try {
            List<Client> clients = clientService.filtrarPorNomeECpf(name, cpf);
            return ResponseEntity.ok(clients);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // ============ FILTROS ADICIONAIS ============
    @GetMapping("/count")
    public ResponseEntity<Long> countClients() {
        long count = clientService.contarClientes();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/findAsc")
    public ResponseEntity<List<Client>> findAsc() {
        List<Client> clients = clientService.findAsc();
        return ResponseEntity.ok(clients);
    }

    // ============ FILTRO COM LIMITE ============
    @GetMapping("/filterByNameWithLimit")
    public ResponseEntity<List<Client>> filterByNameWithLimit(@RequestParam String name, @RequestParam int limit) {
        try {
            List<Client> clients = clientService.filterByNameWithLimit(name, limit);
            return ResponseEntity.ok(clients);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
