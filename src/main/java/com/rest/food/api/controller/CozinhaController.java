package com.rest.food.api.controller;

import com.rest.food.domain.model.Cozinha;
import com.rest.food.domain.repository.CozinhaRepository;
import com.rest.food.domain.service.CozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping("/listar")
    public List<Cozinha> listar() {

        return cozinhaRepository.findAll();

    }

    @GetMapping("buscar/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {

        return cozinhaService.buscarOufalhar(cozinhaId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha cadastrar(@Valid @RequestBody Cozinha cozinha) {
        return cozinhaService.salvar(cozinha);
    }


    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {

        Cozinha cozinhaAtual = cozinhaService.buscarOufalhar(cozinhaId);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cozinhaService.salvar(cozinhaAtual);

    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long cozinhaId) {

        cozinhaService.excluir(cozinhaId);
    }

}