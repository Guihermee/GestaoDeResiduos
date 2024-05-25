package br.com.fiap.GestaoDeResiduos.controller;

import br.com.fiap.GestaoDeResiduos.dto.RotaCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.RotaExibicaoDto;
import br.com.fiap.GestaoDeResiduos.service.RotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RotaController {

    @Autowired
    private RotaService rotaService;

    @PostMapping("/rotas")
    @ResponseStatus(HttpStatus.CREATED)
    public RotaExibicaoDto saveRota(@RequestBody @Valid RotaCadastroDto cadastroDto) {
        return rotaService.saveRota(cadastroDto);
    }

    @DeleteMapping("/rotas/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRota(@PathVariable Long id) {
        rotaService.deleteRota(id);
    }

    @PutMapping("/rotas")
    @ResponseStatus(HttpStatus.OK)
    public RotaExibicaoDto updateRota(@RequestBody RotaCadastroDto cadastroDto) {
        return rotaService.updateRota(cadastroDto);
    }

    @GetMapping("/rotas/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RotaExibicaoDto findRotaById(@PathVariable Long id) {
        return rotaService.findRotaById(id);
    }

    @GetMapping("/rotas")
    @ResponseStatus(HttpStatus.OK)
    public List<RotaExibicaoDto> findAllRota() {
        return rotaService.findAllRota();
    }


}
