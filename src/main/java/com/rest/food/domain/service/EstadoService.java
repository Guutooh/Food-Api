package com.rest.food.domain.service;

import com.rest.food.domain.exception.EntidadeEmUsoException;
import com.rest.food.domain.exception.EstadoNaoEncontradoException;
import com.rest.food.domain.model.Estado;
import com.rest.food.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    public static final String Msg_Estado_Em_Uso = "Estado de código %d não pode ser removido, pois está em uso";
    @Autowired
    EstadoRepository estadoRepository;



    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(Msg_Estado_Em_Uso, estadoId));
        }
    }

    public Estado bucarOufalhar(long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }
}
