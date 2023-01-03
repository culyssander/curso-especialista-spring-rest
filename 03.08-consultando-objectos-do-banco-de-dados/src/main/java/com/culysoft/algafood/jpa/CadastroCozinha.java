package com.culysoft.algafood.jpa;

import com.culysoft.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Component;

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
}
