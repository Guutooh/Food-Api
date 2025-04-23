package com.rest.food.domain.service;

import com.rest.food.domain.exception.RestauranteNaoEncontradoException;
import com.rest.food.domain.model.Cozinha;
import com.rest.food.domain.model.Restaurante;
import com.rest.food.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {


    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaService cozinhaService;

    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaService.buscarOufalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }


    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }


}
