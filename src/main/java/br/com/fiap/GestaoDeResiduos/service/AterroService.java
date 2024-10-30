package br.com.fiap.GestaoDeResiduos.service;

import br.com.fiap.GestaoDeResiduos.dto.AterroCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.AterroExibicaoDto;
import br.com.fiap.GestaoDeResiduos.exception.AterroNotFoundException;
import br.com.fiap.GestaoDeResiduos.model.Aterro;
import br.com.fiap.GestaoDeResiduos.repository.AterroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AterroService {

    @Autowired
    public AterroRepository aterroRepository;

    public AterroExibicaoDto saveAterro(AterroCadastroDto aterroDto) {
        Aterro aterro = new Aterro();
        BeanUtils.copyProperties(aterroDto, aterro);

        return new AterroExibicaoDto(aterroRepository.save(aterro));
    }

    public void deleteAterro(Long id) {
        Optional<Aterro> aterro = aterroRepository.findById(id);

        if (aterro.isPresent()) {
            aterroRepository.delete(aterro.get());
        } else {
            throw new AterroNotFoundException("404 - Aterro não encontrado!");
        }

    }

    public AterroExibicaoDto updateAterro(AterroCadastroDto aterroDto) {
        Optional<Aterro> aterroOptional = aterroRepository.findById(aterroDto.idAterro());

        if (aterroOptional.isPresent()) {
            Aterro aterro = new Aterro();
            BeanUtils.copyProperties(aterroDto, aterro);
            return new AterroExibicaoDto(aterroRepository.save(aterro));
        } else {
            throw new AterroNotFoundException("404 - Aterro não encontrado!");
        }
    }

    public AterroExibicaoDto findAterroById(Long id) {
        Optional<Aterro> aterro = aterroRepository.findById(id);

        if (aterro.isPresent()) {
            return new AterroExibicaoDto(aterro.get());
        } else {
            throw new AterroNotFoundException("404 - Aterro não encontrado!");
        }
    }

    public List<AterroExibicaoDto> findAllAterro() {
        return aterroRepository.findAll().stream().map(AterroExibicaoDto::new).toList();
    }

    public List<AterroExibicaoDto> findByStatusTrue() {
        return aterroRepository.findByStatusTrue().stream().map(AterroExibicaoDto::new).toList();
    }

}
