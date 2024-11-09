package es.marcosar.ejercicio4.controller;

import es.marcosar.ejercicio4.dto.UsuarioDto;
import es.marcosar.ejercicio4.model.Usuario;
import es.marcosar.ejercicio4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        List<UsuarioDto> usuarios = usuarioService.findAll();
        return usuarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuarios);
    }

    @PutMapping("/add")
    public ResponseEntity<UsuarioDto> add(@RequestBody Usuario usuario) {
        UsuarioDto user = usuarioService.add(usuario);
        return user == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioDto> update(@RequestBody Usuario usuario) {
        UsuarioDto user = usuarioService.update(usuario);
        return user == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UsuarioDto> delete(@RequestParam Long id) {
        UsuarioDto user = usuarioService.delete(id);
        return user == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(user);
    }
}
