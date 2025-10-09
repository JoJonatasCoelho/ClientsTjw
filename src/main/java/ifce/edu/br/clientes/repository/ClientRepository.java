package ifce.edu.br.clientes.repository;

import ifce.edu.br.clientes.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client findByCpf(String cpf);
}
