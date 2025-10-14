package ifce.edu.br.clientes.repository;

import ifce.edu.br.clientes.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.cpf = :cpf")
    Client findByCpf(@Param("cpf") String cpf);

    @Query("SELECT c FROM Client c WHERE c.email = :email")
    Client findByEmail(@Param("email") String email);

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Client> filterByName(@Param("name") String name);

    @Query("SELECT c FROM Client c WHERE c.cpf LIKE CONCAT('%', :cpf, '%')")
    List<Client> filterByCpfContains(@Param("cpf") String cpf);

    @Query("SELECT c FROM Client c WHERE LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%'))")
    List<Client> filterByEmailContains(@Param("email") String email);

    @Query("SELECT COUNT(c) FROM Client c")
    long countClients();

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE c.cpf = :cpf")
    boolean existsByCpf(@Param("cpf") String cpf);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE c.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT c FROM Client c WHERE c.address IS NOT NULL")
    List<Client> findClientsWithAddress();

    @Query("SELECT c FROM Client c WHERE c.address IS NULL")
    List<Client> findClientsWithoutAddress();

    @Query("SELECT c FROM Client c WHERE SIZE(c.phone) > 0")
    List<Client> findClientsWithPhone();

    @Query("SELECT c FROM Client c WHERE SIZE(c.phone) = 0")
    List<Client> findClientsWithoutPhone();

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) AND LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%'))")
    List<Client> filterByNameAndEmail(@Param("name") String name, @Param("email") String email);

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) AND c.cpf LIKE CONCAT('%', :cpf, '%')")
    List<Client> filterByNameAndCpf(@Param("name") String name, @Param("cpf") String cpf);

    @Query("SELECT c FROM Client c WHERE c.address IS NOT NULL AND SIZE(c.phone) > 0")
    List<Client> findClientsWithAddressAndPhone();

    @Query("SELECT c FROM Client c ORDER BY c.name ASC")
    List<Client> findAllOrderedByName();

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) LIMIT :limit")
    List<Client> filterByNameWithLimit(@Param("name") String name, @Param("limit") int limit);



}
