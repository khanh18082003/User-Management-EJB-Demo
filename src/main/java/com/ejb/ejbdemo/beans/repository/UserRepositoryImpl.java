package com.ejb.ejbdemo.beans.repository;

import com.ejb.ejbdemo.entity.User;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
@Local(UserRepository.class)
public class UserRepositoryImpl implements UserRepository {

  @PersistenceContext
  private EntityManager em;

  @Transactional
  @Override
  public User save(User user) {
    em.persist(user);
    return user;
  }

  @Transactional
  @Override
  public void update(User user) {
    em.merge(user);
  }

  @Transactional
  @Override
  public void delete(User user) {
    em.remove(user);
  }

  @Override
  public User findById(String id) {
    return em.find(User.class, id);
  }

  @Override
  public List<User> findAll() {
    CriteriaQuery<User> cq = em.getCriteriaBuilder().createQuery(User.class);
    cq.select(cq.from(User.class));
    return em.createQuery(cq).getResultList();
  }

  @Override
  public boolean existsByEmail(String email) {
    CriteriaQuery<User> cq = em.getCriteriaBuilder().createQuery(User.class);
    cq.select(cq.from(User.class));
    cq.where(em.getCriteriaBuilder().equal(cq.from(User.class).get("email"), email));
    return !em.createQuery(cq).getResultList().isEmpty();
  }

  @Override
  public boolean existsByUsername(String username) {
    CriteriaQuery<User> cq = em.getCriteriaBuilder().createQuery(User.class);
    cq.select(cq.from(User.class));
    cq.where(em.getCriteriaBuilder().equal(cq.from(User.class).get("username"), username));
    return !em.createQuery(cq).getResultList().isEmpty();
  }
}
