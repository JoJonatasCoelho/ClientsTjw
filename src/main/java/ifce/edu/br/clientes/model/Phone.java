package ifce.edu.br.clientes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="phone_tb")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String Type;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false, unique = true)
    private Client client;
}
