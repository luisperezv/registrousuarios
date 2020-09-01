package cl.bci.registrousuarios.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios", schema="default")
public class UsuarioEntity {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Column
        private String nombre;

        @Id
        @Column
        private String correo;

        @Column
        private String contrasena;

        @OneToMany(mappedBy = "usuarioEntity", cascade = CascadeType.ALL)
        private Set<TelefonoEntity> telefonos = new HashSet<>();
}
