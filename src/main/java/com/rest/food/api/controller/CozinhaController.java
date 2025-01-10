package com.rest.food.api.controller;

import com.rest.food.domain.model.Cozinha;
import com.rest.food.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

//    @Autowired
//    private CozinhaService cozinhaService;

    @GetMapping("/listar")
    public List<Cozinha> listar() {

        return cozinhaRepository.findAll();

    }
}