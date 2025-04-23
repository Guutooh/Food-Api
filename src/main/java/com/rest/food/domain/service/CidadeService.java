package com.rest.food.domain.service;

import com.rest.food.domain.exception.CidadeNaoEncontradaException;
import com.rest.food.domain.exception.EntidadeEmUsoException;
import com.rest.food.domain.model.Cidade;
import com.rest.food.domain.model.Estado;
import com.rest.food.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    public static final String Msg_Cidade_Em_Uso = "Cidade de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public Cidade salvar(Cidade cidade) {

        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoService.bucarOufalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);

        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(Msg_Cidade_Em_Uso, cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }
}
