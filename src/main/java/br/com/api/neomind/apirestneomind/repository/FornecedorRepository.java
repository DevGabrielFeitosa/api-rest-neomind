package br.com.api.neomind.apirestneomind.repository;

import br.com.api.neomind.apirestneomind.entity.Fornecedor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FornecedorRepository {

    @PersistenceContext(unitName = "fornecedorPU")
    private EntityManager entityManager;

    public List<Fornecedor> findAll() {
        return entityManager.createNamedQuery("Fornecedor.findAll", Fornecedor.class)
                .getResultList();
    }

    public Optional<Fornecedor> findById(Long id) {
        Fornecedor fornecedor = entityManager.find(Fornecedor.class, id);
        return Optional.ofNullable(fornecedor);
    }

    public Optional<Fornecedor> findByEmail(String email) {
        TypedQuery<Fornecedor> query = entityManager.createNamedQuery("Fornecedor.findByEmail", Fornecedor.class);
        query.setParameter("email", email);
        
        List<Fornecedor> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<Fornecedor> findByCnpj(String cnpj) {
        TypedQuery<Fornecedor> query = entityManager.createNamedQuery("Fornecedor.findByCnpj", Fornecedor.class);
        query.setParameter("cnpj", cnpj);
        
        List<Fornecedor> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Transactional
    public Fornecedor save(Fornecedor fornecedor) {
        entityManager.persist(fornecedor);
        entityManager.flush();
        return fornecedor;
    }

    @Transactional
    public Fornecedor update(Fornecedor fornecedor) {
        return entityManager.merge(fornecedor);
    }

    @Transactional
    public void delete(Long id) {
        Fornecedor fornecedor = entityManager.find(Fornecedor.class, id);
        if (fornecedor != null) {
            entityManager.remove(fornecedor);
        }
    }

    public boolean existsByEmailAndIdNot(String email, Long id) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(f) FROM Fornecedor f WHERE f.email = :email AND f.id != :id", Long.class);
        query.setParameter("email", email);
        query.setParameter("id", id);
        
        return query.getSingleResult() > 0;
    }

    public boolean existsByCnpjAndIdNot(String cnpj, Long id) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(f) FROM Fornecedor f WHERE f.cnpj = :cnpj AND f.id != :id", Long.class);
        query.setParameter("cnpj", cnpj);
        query.setParameter("id", id);
        
        return query.getSingleResult() > 0;
    }
}
