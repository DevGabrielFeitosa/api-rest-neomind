package br.com.api.neomind.apirestneomind.repository;

import br.com.api.neomind.apirestneomind.entity.Fornecedor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FornecedorRepository {

    @PersistenceUnit(unitName = "testPU")
    private EntityManagerFactory entityManagerFactory;

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public List<Fornecedor> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Fornecedor.findAll", Fornecedor.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Fornecedor> findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            Fornecedor fornecedor = em.find(Fornecedor.class, id);
            return Optional.ofNullable(fornecedor);
        } finally {
            em.close();
        }
    }

    public Optional<Fornecedor> findByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Fornecedor> query = em.createNamedQuery("Fornecedor.findByEmail", Fornecedor.class);
            query.setParameter("email", email);

            List<Fornecedor> results = query.getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } finally {
            em.close();
        }
    }

    public Optional<Fornecedor> findByCnpj(String cnpj) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Fornecedor> query = em.createNamedQuery("Fornecedor.findByCnpj", Fornecedor.class);
            query.setParameter("cnpj", cnpj);

            List<Fornecedor> results = query.getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } finally {
            em.close();
        }
    }

    public Fornecedor save(Fornecedor fornecedor) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(fornecedor);
            em.getTransaction().commit();
            return fornecedor;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Fornecedor update(Fornecedor fornecedor) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Fornecedor updated = em.merge(fornecedor);
            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Fornecedor fornecedor = em.find(Fornecedor.class, id);
            if (fornecedor != null) {
                em.remove(fornecedor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean existsByEmailAndIdNot(String email, Long id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(f) FROM Fornecedor f WHERE f.email = :email AND f.id != :id", Long.class);
            query.setParameter("email", email);
            query.setParameter("id", id);

            return query.getSingleResult() > 0;
        } finally {
            em.close();
        }
    }

    public boolean existsByCnpjAndIdNot(String cnpj, Long id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(f) FROM Fornecedor f WHERE f.cnpj = :cnpj AND f.id != :id", Long.class);
            query.setParameter("cnpj", cnpj);
            query.setParameter("id", id);

            return query.getSingleResult() > 0;
        } finally {
            em.close();
        }
    }
}
