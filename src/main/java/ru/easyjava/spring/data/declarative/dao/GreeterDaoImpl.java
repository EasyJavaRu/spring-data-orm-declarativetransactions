package ru.easyjava.spring.data.declarative.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.easyjava.spring.data.declarative.entity.Greeter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * JPA based implementation of GreeterDao.
 */
@Repository
public class GreeterDaoImpl implements GreeterDao {
    /**
     * JPA EM factory, provided by Spring.
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public final void addGreet(final Greeter g) {
        em.persist(g);
    }

    @Override
    @Transactional(rollbackFor = NotImplementedException.class)
    public final void updateGreet(final Greeter g, final String newTarget) {
        Greeter greet = em.merge(g);
        greet.setTarget(newTarget);

        throw new NotImplementedException();
    }

    @Override
    @Transactional(readOnly = true)
    public final List<Greeter> getGreetings() {
        return em.createQuery("from Greeter", Greeter.class)
                .getResultList();
    }
}
