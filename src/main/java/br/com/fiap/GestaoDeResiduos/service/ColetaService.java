package br.com.fiap.GestaoDeResiduos.service;

import br.com.fiap.GestaoDeResiduos.dto.ColetaCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.ColetaExibicaoDto;
import br.com.fiap.GestaoDeResiduos.exception.CaminhaoNotFoundException;
import br.com.fiap.GestaoDeResiduos.exception.ColetaNotFoundException;
import br.com.fiap.GestaoDeResiduos.model.Coleta;
import br.com.fiap.GestaoDeResiduos.repository.ColetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    //Agendamento de Coleta
    public ColetaExibicaoDto saveColeta(ColetaCadastroDto coletaDto){
        Coleta coleta = new Coleta();
        BeanUtils.copyProperties(coletaDto, coleta);

        return new ColetaExibicaoDto(coletaRepository.save(coleta));
    }

    //Cancelamento de Coleta
    public void deleteColeta(Long id){
        Optional<Coleta> coleta = coletaRepository.findById(id);

        if (coleta.isPresent()) {
            coletaRepository.delete(coleta.get());
        } else {
            throw new ColetaNotFoundException("404 - Coleta não encontrada!");
        }
    }

    // Atualizar Coleta
    public ColetaExibicaoDto updateColeta(ColetaCadastroDto coletaDto){
        Optional<Coleta> coletaOptional = coletaRepository.findById(coletaDto.idColeta());

        if (coletaOptional.isPresent()) {
            Coleta coleta = new Coleta();
            BeanUtils.copyProperties(coletaDto, coleta);
            return new ColetaExibicaoDto(coletaRepository.save(coleta));
        } else {
            throw new ColetaNotFoundException("404 - Coleta não encontrada!");
        }
    }

    //Buscar Coleta pelo ID
    public ColetaExibicaoDto findColetaById(Long id) {
        Optional<Coleta> coleta = coletaRepository.findById(id);

        if (coleta.isPresent()) {
            return new ColetaExibicaoDto(coleta.get());
        } else {
            throw new CaminhaoNotFoundException("404 - Coleta não encontrada!");
        }
    }

    //Listar todas as coletas agendadas
    public List<ColetaExibicaoDto> findAllColeta() {
        return coletaRepository.findAll().stream().map(ColetaExibicaoDto::new).toList();
    }

    //Listar coleta por localização
    public ColetaExibicaoDto findColetaByLocalizacao(String nmLocalizacao){
        Optional<Coleta> coleta = coletaRepository.findByNomeLocalizacao(nmLocalizacao);

        if (coleta.isPresent()) {
            return new ColetaExibicaoDto(coleta.get());
        } else {
            throw new CaminhaoNotFoundException("404 - Coleta não encontrada!");
        }
    }

}
