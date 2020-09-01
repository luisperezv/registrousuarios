package cl.bci.registrousuarios.controller;

import cl.bci.registrousuarios.Util.PasswordValidator;
import cl.bci.registrousuarios.exception.ErrorNegocioException;
import cl.bci.registrousuarios.model.MensajeError;
import cl.bci.registrousuarios.model.RegistroUsuarioRequest;
import cl.bci.registrousuarios.model.RegistroUsuarioResponse;
import cl.bci.registrousuarios.model.Telefono;
import cl.bci.registrousuarios.repository.UsuarioRepository;
import cl.bci.registrousuarios.repository.entity.TelefonoEntity;
import cl.bci.registrousuarios.repository.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class UsuariosController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @RequestMapping("helloworld")
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping(value = "usuario/registro2", method = RequestMethod.POST)
    public ResponseEntity<?> registrarUsuario2(@RequestParam String nombre) {

        return ResponseEntity.ok(new String("Usuario registrado: " + nombre));
    }

    @RequestMapping(value = "usuario/registro", method = RequestMethod.POST)
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioRequest usuarioRequest) {

        try {
            //TODO Validar formato correo electronico

            //TODO Validar formato password
            PasswordValidator passwordValidator = new PasswordValidator();
            boolean passwordValido = passwordValidator.validate(usuarioRequest.getContrasena());

        /*
        if (!passwordValido) {
            MensajeError mensajeError = new MensajeError();
            mensajeError.setMensaje("El password utilizado no cumple con el formato requerido (1 " +
                    "mayúscula, el resto minísculas y 2 dígitos).");

            return ResponseEntity.ok(mensajeError);
        }
         */

            //TODO Validar existencia previa de correo electornico registrado
            UsuarioEntity usuarioPrevio = usuarioRepository.findByCorreo(usuarioRequest.getCorreo());

            if (usuarioPrevio != null) {
                MensajeError mensajeError = new MensajeError();
                mensajeError.setMensaje("El usuario ya se encuentra registrado. Imposible continuar.");

                return ResponseEntity.ok(mensajeError);
            }

            //Registrar nuevo usuario
            //ir.save(new Income(formatter.parse("01/01/2018"), "first income", 1000.0));

            Set<TelefonoEntity> telefonoEntities = new HashSet<TelefonoEntity>();
            for (Telefono telefono : usuarioRequest.getTelefonos()) {
                TelefonoEntity telefonoEntity = TelefonoEntity.builder()
                        .numero(telefono.getNumero())
                        .codigoCiudad(telefono.getCodigoCiudad())
                        .codigoPais(telefono.getCodigoPais())
                        .build();

                telefonoEntities.add(telefonoEntity);
            }

            UsuarioEntity usuarioNuevo = UsuarioEntity.builder()
                    .nombre(usuarioRequest.getNombre())
                    .correo(usuarioRequest.getCorreo())
                    .contrasena(usuarioRequest.getContrasena())
                    .telefonos(telefonoEntities)
                    .build();

            usuarioRepository.save(usuarioNuevo);
            //TODO Obtener UUID de nuevo usuario
            String nuevoUUID = "0";

            //Generar respuesta de servicio
            RegistroUsuarioResponse response = new RegistroUsuarioResponse();

            //final String uuid = UUID.randomUUID().toString().replace("-", "");
            //System.out.println("uuid = " + uuid);

            response.setId(nuevoUUID); //TODO generar desde la BD
            response.setCreated(new Date());
            response.setModified(new Date());
            response.setLastLogin(new Date());
            response.setToken(nuevoUUID);
            response.setActive(true);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            throw e;
        }
    }
}
