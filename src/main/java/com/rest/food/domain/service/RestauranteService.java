package com.rest.food.domain.service;

import com.rest.food.domain.exception.EntidadeNaoEncontradaException;
import com.rest.food.domain.model.Cozinha;
import com.rest.food.domain.model.Restaurante;
import com.rest.food.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe um cadastro de restaurante com código %d";

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaService cozinhaService;

    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaService.bucarOufalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }


    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
    }


}
