package br.com.fiap.GestaoDeResiduos.controller;

import br.com.fiap.GestaoDeResiduos.dto.CaminhaoCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.CaminhaoExibicaoDto;
import br.com.fiap.GestaoDeResiduos.dto.FuncionarioCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.FuncionarioExibicaoDto;
import br.com.fiap.GestaoDeResiduos.model.Funcionario;
import br.com.fiap.GestaoDeResiduos.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/funcionarios")
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioExibicaoDto gravarFuncionario(@RequestBody @Valid FuncionarioCadastroDto funcionarioCadastroDto){
        return funcionarioService.gravarFuncionario(funcionarioCadastroDto);
    }

    @GetMapping("/funcionarios")
    @ResponseStatus(HttpStatus.OK)
    public List<FuncionarioExibicaoDto> listarTodosOsFuncionarios(){
        return funcionarioService.listarTodosOsFuncionarios();
    }

    @DeleteMapping("/funcionarios/id/{idFuncionario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirFuncionario(@PathVariable Long idFuncionario){
        funcionarioService.deletarFuncionario(idFuncionario);
    }

    @PutMapping("/funcionarios")
    @ResponseStatus(HttpStatus.OK)
    public FuncionarioExibicaoDto atualizarFuncionario(@RequestBody FuncionarioCadastroDto funcionarioCadastroDto){
        return funcionarioService.atualizarFuncionario(funcionarioCadastroDto);
    }

    @GetMapping("/funcionarios/nome/{nomeFuncionario}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FuncionarioExibicaoDto> buscarFuncionarioPeloNome(@PathVariable String nomeFuncionario){
        try {
            return ResponseEntity.ok(funcionarioService.buscarFuncionarioPeloNome(nomeFuncionario));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/funcionarios/id/{idFuncionario}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FuncionarioExibicaoDto> buscarFuncionarioPeloId(@PathVariable Long idFuncionario){
        try {
            return ResponseEntity.ok(funcionarioService.buscarFuncionarioPorId(idFuncionario));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

