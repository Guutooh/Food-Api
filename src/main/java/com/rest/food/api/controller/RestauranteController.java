package com.rest.food.api.controller;

import com.rest.food.domain.exception.CozinhaNaoEncontradaException;
import com.rest.food.domain.exception.NegocioException;
import com.rest.food.domain.model.Restaurante;
import com.rest.food.domain.repository.RestauranteRepository;
import com.rest.food.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {



    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    RestauranteService restauranteService;


    @GetMapping("/listar")
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }


    @GetMapping("buscar/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {

        return  restauranteService.buscarOuFalhar(restauranteId);

    }

    @PostMapping("/adicionar")
    public Restaurante adicionar( @RequestBody Restaurante restaurante) {

        try {
            return restauranteService.salvar(restaurante);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @PutMapping("atualizar/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId,
                                 @RequestBody Restaurante restaurante) {

        try {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

            return restauranteService.salvar(restauranteAtual);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

}
