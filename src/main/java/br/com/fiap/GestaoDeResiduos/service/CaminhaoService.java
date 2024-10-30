package br.com.fiap.GestaoDeResiduos.service;

import br.com.fiap.GestaoDeResiduos.dto.CaminhaoCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.CaminhaoExibicaoDto;
import br.com.fiap.GestaoDeResiduos.exception.CaminhaoNotFoundException;
import br.com.fiap.GestaoDeResiduos.model.Caminhao;
import br.com.fiap.GestaoDeResiduos.repository.CaminhaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoService {

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    public CaminhaoExibicaoDto saveCaminhao(CaminhaoCadastroDto caminhaoDto) {
        Caminhao caminhao = new Caminhao();
        BeanUtils.copyProperties(caminhaoDto, caminhao);

        return new CaminhaoExibicaoDto(caminhaoRepository.save(caminhao));
    }

    public void deleteCaminhao(Long id) {
        Optional<Caminhao> caminhao = caminhaoRepository.findById(id);

        if (caminhao.isPresent()) {
            caminhaoRepository.delete(caminhao.get());
        } else {
            throw new CaminhaoNotFoundException("404 - Caminhao não encontrado!");
        }

    }

    public CaminhaoExibicaoDto updateCaminhao(CaminhaoCadastroDto caminhaoDto) {
        Optional<Caminhao> caminhaoOptional = caminhaoRepository.findById(caminhaoDto.idCaminhao());

        if (caminhaoOptional.isPresent()) {
            Caminhao caminhao = new Caminhao();
            BeanUtils.copyProperties(caminhaoDto, caminhao);
            return new CaminhaoExibicaoDto(caminhaoRepository.save(caminhao));
        } else {
            throw new CaminhaoNotFoundException("404 - Caminhao não encontrado!");
        }
    }

    public CaminhaoExibicaoDto findCaminhaoById(Long id) {
        Optional<Caminhao> caminhao = caminhaoRepository.findById(id);

        if (caminhao.isPresent()) {
            return new CaminhaoExibicaoDto(caminhao.get());
        } else {
            throw new CaminhaoNotFoundException("404 - Caminhão não encontrado!");
        }
    }

    public List<CaminhaoExibicaoDto> findAllCaminhao() {
        return caminhaoRepository.findAll().stream().map(CaminhaoExibicaoDto::new).toList();
    }

    public List<CaminhaoExibicaoDto> findByQtdAtualGreaterThanVlCapacidade() {
        return caminhaoRepository.findByQtdAtualLessThanVlCapacidade().stream().map(CaminhaoExibicaoDto::new).toList();
    }

}
