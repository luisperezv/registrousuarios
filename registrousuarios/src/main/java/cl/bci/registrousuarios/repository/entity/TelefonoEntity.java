package cl.bci.registrousuarios.repository.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "telefonos", schema="default")
public class TelefonoEntity {

    @Column
    private String numero;

    @Column
    private String codigoCiudad;

    @Column
    private String codigoPais;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuarioEntity;
}
