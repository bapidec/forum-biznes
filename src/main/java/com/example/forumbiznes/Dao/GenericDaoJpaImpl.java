package com.example.forumbiznes.Dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class GenericDaoJpaImpl<T, K> implements GenericDao<T, K> {

    private final Class<T> type;
    @PersistenceContext(unitName = "PU")
    protected EntityManager em;

    public GenericDaoJpaImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public void save(T t) {
        em.persist(t);
    }

    @Override
    public void delete(T t) {
        em.remove(t);
    }

    @Override
    public void update(T t) {
        em.merge(t);
    }

    @Override
    public Optional<T> findById(K id) {
        T dto = em.find(type, id);
        em.close();
        return Optional.ofNullable(dto);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> rootEntry = cq.from(type);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}