package cl.bci.registrousuarios.controller;

import cl.bci.registrousuarios.Util.PasswordValidator;
import cl.bci.registrousuarios.exception.ErrorNegocioException;
import cl.bci.registrousuarios.model.MensajeError;
import cl.bci.registrousuarios.model.RegistroUsuarioRequest;
import cl.bci.registrousuarios.model.RegistroUsuarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.UUID;

@RestController
public class UsuariosController {

    @RequestMapping("helloworld")
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping(value = "usuario/registro2", method = RequestMethod.POST)
    public ResponseEntity<?> registrarUsuario2(@RequestParam String nombre) {

        return ResponseEntity.ok(new String("Usuario registrado: " + nombre));
    }

    @RequestMapping(value = "usuario/registro", method = RequestMethod.POST)
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioRequest usuario) {

        PasswordValidator passwordValidator = new PasswordValidator();
        boolean passwordValido = passwordValidator.validate(usuario.getContrasena());

        /*
        //Validar formato password
        if (!passwordValido) {

            MensajeError mensajeError = new MensajeError();
            mensajeError.setMensaje("El password utilizado no cumple con el formato requerido (1 mayúscula, el resto minísculas y 2 dígitos)");

            return ResponseEntity.ok(mensajeError);
        }
         */

        RegistroUsuarioResponse response = new RegistroUsuarioResponse();

        final String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println("uuid = " + uuid);

        response.setId("uuid"); //TODO generar desde la BD
        response.setCreated(new Date());
        response.setModified(new Date());
        response.setLastLogin(new Date());
        response.setToken(uuid);
        response.setActive(true);

        return ResponseEntity.ok(response);
    }
}
