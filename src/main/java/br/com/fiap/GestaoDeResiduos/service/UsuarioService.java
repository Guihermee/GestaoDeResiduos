package br.com.fiap.GestaoDeResiduos.service;

import br.com.fiap.GestaoDeResiduos.dto.UsuarioCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.UsuarioExibicaoDto;
import br.com.fiap.GestaoDeResiduos.exception.UsuarioNotFoundException;
import br.com.fiap.GestaoDeResiduos.model.Usuario;
import br.com.fiap.GestaoDeResiduos.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto saveUsuario(UsuarioCadastroDto usuarioCadastroDTO) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        return new UsuarioExibicaoDto(usuarioRepository.save(usuario));
    }

    public UsuarioExibicaoDto getUsuarioById(Long id) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioOptional.get());
        } else {
            throw new UsuarioNotFoundException("User not found");
        }
    }

    public Page<UsuarioExibicaoDto> getAllUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(UsuarioExibicaoDto::new);
    }

    public void deleteUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public UsuarioExibicaoDto updateUsuario(UsuarioCadastroDto usuarioCadastroDTO) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDTO, usuario);

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getIdUsuario());

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioRepository.save(usuario));
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
