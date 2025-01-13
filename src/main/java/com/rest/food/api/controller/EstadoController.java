package com.rest.food.api.controller;

import com.rest.food.domain.exception.EntidadeEmUsoException;
import com.rest.food.domain.exception.EntidadeNaoEncontradaException;
import com.rest.food.domain.model.Estado;
import com.rest.food.domain.repository.EstadoRepository;
import com.rest.food.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listar")
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/buscar/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {


        return estadoService.bucarOufalhar(estadoId);


    }

    @PostMapping("/adicionar")
    public Estado adicionar(@RequestBody Estado estado) {


        return estadoService.salvar(estado);

    }

    @PutMapping("atualizar/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {

        Estado estadoAtual = estadoService.bucarOufalhar(estadoId);

        BeanUtils.copyProperties(estado, estadoAtual, "id");

        return estadoService.salvar(estadoAtual);

    }


    @DeleteMapping("deletar/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId) {
        try {
            estadoService.excluir(estadoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

}
