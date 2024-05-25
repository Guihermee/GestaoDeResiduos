package br.com.fiap.GestaoDeResiduos.controller;

import br.com.fiap.GestaoDeResiduos.dto.CaminhaoCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.CaminhaoExibicaoDto;
import br.com.fiap.GestaoDeResiduos.service.CaminhaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CaminhaoController {

    @Autowired
    private CaminhaoService caminhaoService;

    @PostMapping("/caminhoes")
    @ResponseStatus(HttpStatus.CREATED)
    public CaminhaoExibicaoDto saveCaminhao(@RequestBody @Valid CaminhaoCadastroDto cadastroDto) {
        return caminhaoService.saveCaminhao(cadastroDto);
    }

    @DeleteMapping("/caminhoes/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCaminhao(@PathVariable Long id) {
        caminhaoService.deleteCaminhao(id);
    }

    @PutMapping("/caminhoes")
    @ResponseStatus(HttpStatus.OK)
    public CaminhaoExibicaoDto updateCaminhao(@RequestBody CaminhaoCadastroDto cadastroDto) {
        return caminhaoService.updateCaminhao(cadastroDto);
    }

    @GetMapping("/caminhoes/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CaminhaoExibicaoDto findCaminhaoById(@PathVariable Long id) {
        return caminhaoService.findCaminhaoById(id);
    }

    @GetMapping("/caminhoes")
    @ResponseStatus(HttpStatus.OK)
    public List<CaminhaoExibicaoDto> findAllCaminhao() {
        return caminhaoService.findAllCaminhao();
    }

    @GetMapping("/caminhoes/ok")
    @ResponseStatus(HttpStatus.OK)
    public List<CaminhaoExibicaoDto> findByQtdAtualGreaterThanVlCapacidade() {
        return caminhaoService.findByQtdAtualGreaterThanVlCapacidade();
    }

}
