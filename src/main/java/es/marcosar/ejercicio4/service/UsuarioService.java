package es.marcosar.ejercicio4.service;

import es.marcosar.ejercicio4.dto.UsuarioDto;
import es.marcosar.ejercicio4.model.Usuario;
import es.marcosar.ejercicio4.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioDto mapToDto(Usuario usuario) {
        return new UsuarioDto(usuario.getNombre(), usuario.getCorreo_electronico());
    }

    public List<UsuarioDto> findAll() {
        List<UsuarioDto> usuarios = new ArrayList<>();
        usuarioRepository.findAll().forEach(u -> usuarios.add(mapToDto(u)));
        return usuarios;
    }

    public UsuarioDto add(Usuario usuario) {
        return mapToDto(usuarioRepository.save(usuario));
    }

    public UsuarioDto update(Usuario usuario) {
        Usuario user = usuarioRepository.findById(usuario.getId()).orElseThrow();
        user.setNombre(usuario.getNombre());
        user.setDireccion(usuario.getDireccion());
        user.setCorreo_electronico(usuario.getCorreo_electronico());
        user.setPassword(usuario.getPassword());
        return mapToDto(usuarioRepository.save(user));
    }

    public UsuarioDto delete(Long id) {
        Usuario user = usuarioRepository.findById(id).orElseThrow();
        usuarioRepository.delete(user);
        return mapToDto(user);
    }
}
