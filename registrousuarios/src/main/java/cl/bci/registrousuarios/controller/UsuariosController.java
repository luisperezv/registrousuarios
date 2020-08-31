package cl.bci.registrousuarios.controller;

import cl.bci.registrousuarios.model.RegistroUsuarioRequest;
import cl.bci.registrousuarios.model.RegistroUsuarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    public ResponseEntity<RegistroUsuarioResponse> registrarUsuario(@RequestBody RegistroUsuarioRequest usuario) {

        RegistroUsuarioResponse response = new RegistroUsuarioResponse();

        response.setId("uuid");
        response.setCreated(new Date());
        response.setModified(new Date());
        response.setLastLogin(new Date());
        response.setToken("token");
        response.setActive(true);

        return ResponseEntity.ok(response);
    }
}
