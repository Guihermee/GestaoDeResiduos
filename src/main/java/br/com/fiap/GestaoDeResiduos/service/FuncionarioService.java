package br.com.fiap.GestaoDeResiduos.service;

import br.com.fiap.GestaoDeResiduos.dto.CaminhaoCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.CaminhaoExibicaoDto;
import br.com.fiap.GestaoDeResiduos.dto.FuncionarioCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.FuncionarioExibicaoDto;
import br.com.fiap.GestaoDeResiduos.exception.CaminhaoNotFoundException;
import br.com.fiap.GestaoDeResiduos.exception.FuncionarioNotFoundException;
import br.com.fiap.GestaoDeResiduos.model.Caminhao;
import br.com.fiap.GestaoDeResiduos.model.Funcionario;
import br.com.fiap.GestaoDeResiduos.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioExibicaoDto gravarFuncionario(FuncionarioCadastroDto funcionarioDto){
        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDto, funcionario);
        return new FuncionarioExibicaoDto(funcionarioRepository.save(funcionario));
    }

    public FuncionarioExibicaoDto buscarFuncionarioPorId(Long idFuncionario){

        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(idFuncionario);

        if(funcionarioOptional.isPresent()){
            return new FuncionarioExibicaoDto(funcionarioOptional.get());
        } else {
            throw new FuncionarioNotFoundException("404 - Funcionario não encontrado!");
        }
    }

    public List<FuncionarioExibicaoDto> listarTodosOsFuncionarios(){
        return funcionarioRepository.findAll().stream().map(FuncionarioExibicaoDto::new).toList();
    }

    public void deletarFuncionario(Long idFuncionario){

        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(idFuncionario);

        if(funcionarioOptional.isPresent()){
            funcionarioRepository.delete(funcionarioOptional.get());
        }else {
            throw new FuncionarioNotFoundException("404 - Funcionario não encontrado!");
        }
    }

    public FuncionarioExibicaoDto atualizarFuncionario(FuncionarioCadastroDto funcionarioDto){

        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(funcionarioDto.idFuncionario());

        if(funcionarioOptional.isPresent()){
            Funcionario funcionario = new Funcionario();
            BeanUtils.copyProperties(funcionarioDto, funcionario);
            return new FuncionarioExibicaoDto(funcionarioRepository.save(funcionario));
        }else {
            throw new FuncionarioNotFoundException("404 - Funcionario não encontrado!");
        }
    }

    public FuncionarioExibicaoDto buscarFuncionarioPeloNome(String nomeFuncionario){

        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByNomeFuncionario(nomeFuncionario);

        if(funcionarioOptional.isPresent()){
            return new FuncionarioExibicaoDto(funcionarioOptional.get());
        } else {
            throw new FuncionarioNotFoundException("404 - Funcionario não encontrado!");
        }
    }

}
