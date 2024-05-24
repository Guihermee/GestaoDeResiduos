package br.com.fiap.GestaoDeResiduos.controller;

import br.com.fiap.GestaoDeResiduos.dto.AterroCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.AterroExibicaoDto;
import br.com.fiap.GestaoDeResiduos.service.AterroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AterroController {

    @Autowired
    private AterroService aterroService;

    @PostMapping("/aterro")
    @ResponseStatus(HttpStatus.CREATED)
    public AterroExibicaoDto saveAterro(@RequestBody @Valid AterroCadastroDto cadastroDto) {
        return aterroService.saveAterro(cadastroDto);
    }

    @DeleteMapping("/aterro/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAterro(@PathVariable Long id) {
        aterroService.deleteAterro(id);
    }

    @PutMapping("/aterro")
    @ResponseStatus(HttpStatus.OK)
    public AterroExibicaoDto updateAterro(@RequestBody AterroCadastroDto aterroDto) {
        return aterroService.updateAterro(aterroDto);
    }

    @GetMapping("/aterro/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AterroExibicaoDto findAterroById(@PathVariable Long id) {
        return aterroService.findAterroById(id);
    }

    @GetMapping("/aterro")
    @ResponseStatus(HttpStatus.OK)
    public List<AterroExibicaoDto> findAllAterro() {
        return aterroService.findAllAterro();
    }

    @GetMapping("/aterro/status")
    @ResponseStatus(HttpStatus.OK)
    public List<AterroExibicaoDto> findByStatusTrue() {
        return aterroService.findByStatusTrue();
    }

}
