package br.com.fiap.GestaoDeResiduos.controller;

import br.com.fiap.GestaoDeResiduos.dto.ColetaCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.ColetaExibicaoDto;
import br.com.fiap.GestaoDeResiduos.service.ColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    //Gravar coleta
    @PostMapping("/coletas")
    @ResponseStatus(HttpStatus.CREATED)
    public ColetaExibicaoDto saveColeta(@RequestBody ColetaCadastroDto coletaDto){
        return coletaService.saveColeta(coletaDto);
    }

    //Cancelar coleta
    @DeleteMapping("/coletas/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteColeta(@PathVariable Long id){
        coletaService.deleteColeta(id);
    }

    //Atualizar coletar
    @PutMapping("/coletas")
    @ResponseStatus(HttpStatus.OK)
    public ColetaExibicaoDto updateColeta(@RequestBody ColetaCadastroDto coletaDto){
        return coletaService.updateColeta(coletaDto);
    }

    //Listar coletas por ID
    @GetMapping("/coletas/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ColetaExibicaoDto> findColetaById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(coletaService.findColetaById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Listar todas as coletas
    @GetMapping("/coletas")
    @ResponseStatus(HttpStatus.OK)
    public List<ColetaExibicaoDto> findAllColeta(){
        return coletaService.findAllColeta();
    }

    @GetMapping("coletas/localizacao/{nmLocalizacao}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ColetaExibicaoDto> findColetaByLocalizacao (@PathVariable String nmLocalizacao){
        try {
            return ResponseEntity.ok(coletaService.findColetaByLocalizacao(nmLocalizacao));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }





}
