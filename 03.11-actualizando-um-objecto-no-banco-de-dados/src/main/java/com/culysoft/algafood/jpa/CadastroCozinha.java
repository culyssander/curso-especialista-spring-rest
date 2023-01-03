package com.culysoft.algafood.jpa;

import com.culysoft.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> list() {
        TypedQuery<Cozinha> typedQuery = entityManager.createQuery("FROM Cozinha c", Cozinha.class);

        return typedQuery.getResultList();
    }

    public Cozinha buscarPeloId(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    public Cozinha salva(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }
}
