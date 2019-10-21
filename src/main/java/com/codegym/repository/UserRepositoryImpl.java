package com.codegym.repository;

import com.codegym.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
       return null;
    }

    @Override
    public void save(User model) {
        if (model.getId() != null) {
            em.merge(model);
        }else {
            em.persist(model);
        }
    }
}
