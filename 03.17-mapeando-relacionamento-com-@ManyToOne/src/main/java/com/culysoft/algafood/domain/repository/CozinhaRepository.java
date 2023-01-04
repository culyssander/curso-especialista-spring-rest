package com.culysoft.algafood.domain.repository;

import com.culysoft.algafood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> todasCozinhas();
    Cozinha buscaPeloId(Long id);

    Cozinha salva(Cozinha cozinha);

    void remover(Cozinha cozinha);
}
