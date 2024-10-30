package br.com.fiap.GestaoDeResiduos.service;

import br.com.fiap.GestaoDeResiduos.dto.RotaCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.RotaExibicaoDto;
import br.com.fiap.GestaoDeResiduos.dto.RotaCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.RotaExibicaoDto;
import br.com.fiap.GestaoDeResiduos.exception.RotaNotFoundException;
import br.com.fiap.GestaoDeResiduos.model.Rota;
import br.com.fiap.GestaoDeResiduos.repository.RotaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RotaService {

    @Autowired
    private RotaRepository rotaRepository;

    public RotaExibicaoDto saveRota(RotaCadastroDto RotaDto) {
        Rota rota = new Rota();
        BeanUtils.copyProperties(RotaDto, rota);

        return new RotaExibicaoDto(rotaRepository.save(rota));
    }

    public void deleteRota(Long id) {
        Optional<Rota> rota = rotaRepository.findById(id);

        if (rota.isPresent()) {
            rotaRepository.delete(rota.get());
        } else {
            throw new RotaNotFoundException("404 - Rota não encontrado!");
        }

    }

    public RotaExibicaoDto updateRota(RotaCadastroDto rotaDto) {
        Optional<Rota> rotaOptional = rotaRepository.findById(rotaDto.idRota());

        if (rotaOptional.isPresent()) {
            Rota rota = new Rota();
            BeanUtils.copyProperties(rotaDto, rota);
            return new RotaExibicaoDto(rotaRepository.save(rota));
        } else {
            throw new RotaNotFoundException("404 - Rota não encontrado!");
        }
    }

    public RotaExibicaoDto findRotaById(Long id) {
        Optional<Rota> rota = rotaRepository.findById(id);

        if (rota.isPresent()) {
            return new RotaExibicaoDto(rota.get());
        } else {
            throw new RotaNotFoundException("404 - Rota não encontrado!");
        }
    }

    public List<RotaExibicaoDto> findAllRota() {
        return rotaRepository.findAll().stream().map(RotaExibicaoDto::new).toList();
    }



}
