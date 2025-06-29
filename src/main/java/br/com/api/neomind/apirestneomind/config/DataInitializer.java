package br.com.api.neomind.apirestneomind.config;

import br.com.api.neomind.apirestneomind.entity.Fornecedor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class DataInitializer {

    private static final Logger LOGGER = Logger.getLogger(DataInitializer.class.getName());

    @PersistenceUnit(unitName = "testPU")
    private EntityManagerFactory entityManagerFactory;

    public void initializeData(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            Thread.sleep(2000); // 2 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try {
            LOGGER.info("🚀 Iniciando população do banco de dados...");

            if (!findAll().isEmpty()) {
                LOGGER.info("📊 Banco já possui dados. Pulando inicialização.");
                return;
            }

            createInitialSuppliers();

            LOGGER.info("✅ Banco de dados populado com sucesso!");

        } catch (Exception e) {
            LOGGER.severe("❌ Erro ao popular banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<Fornecedor> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createNamedQuery("Fornecedor.findAll", Fornecedor.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    private void save(Fornecedor fornecedor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(fornecedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    private void createInitialSuppliers() {
        LOGGER.info("📝 Criando fornecedores iniciais...");

        Fornecedor fornecedor1 = new Fornecedor(
            "Fornecedor Tech Solutions",
            "contato@techsolutions.com",
            "Empresa especializada em soluções tecnológicas",
            "12.345.678/0001-90"
        );
        save(fornecedor1);
        LOGGER.info("✅ Fornecedor 1 criado: " + fornecedor1.getName());

        Fornecedor fornecedor2 = new Fornecedor(
            "Distribuidora Alpha",
            "vendas@alpha.com.br",
            "Distribuidora de materiais de escritório",
            "23.456.789/0001-01"
        );
        save(fornecedor2);
        LOGGER.info("✅ Fornecedor 2 criado: " + fornecedor2.getName());

        Fornecedor fornecedor3 = new Fornecedor(
            "Indústria Beta Ltda",
            "comercial@beta.ind.br",
            "Indústria de componentes eletrônicos",
            "34.567.890/0001-12"
        );
        save(fornecedor3);
        LOGGER.info("✅ Fornecedor 3 criado: " + fornecedor3.getName());

        Fornecedor fornecedor4 = new Fornecedor(
            "Serviços Gamma",
            "atendimento@gamma.com",
            "Prestadora de serviços de manutenção",
            "45.678.901/0001-23"
        );
        save(fornecedor4);
        LOGGER.info("✅ Fornecedor 4 criado: " + fornecedor4.getName());

        Fornecedor fornecedor5 = new Fornecedor(
            "Comércio Delta",
            "pedidos@delta.com.br",
            "Comércio de produtos alimentícios",
            "56.789.012/0001-34"
        );
        save(fornecedor5);
        LOGGER.info("✅ Fornecedor 5 criado: " + fornecedor5.getName());

        Fornecedor fornecedor6 = new Fornecedor(
            "Fornecedor Epsilon",
            "contato@epsilon.net",
            "Fornecedor de materiais de construção",
            "67.890.123/0001-45"
        );
        save(fornecedor6);
        LOGGER.info("✅ Fornecedor 6 criado: " + fornecedor6.getName());

        Fornecedor fornecedor7 = new Fornecedor(
            "Empresa Zeta Corp",
            "comercial@zeta.corp",
            "Corporação multinacional de tecnologia",
            "78.901.234/0001-56"
        );
        save(fornecedor7);
        LOGGER.info("✅ Fornecedor 7 criado: " + fornecedor7.getName());

        Fornecedor fornecedor8 = new Fornecedor(
            "Fornec LoremIpsum",
            "fornec@loripsom.com",
            "loreipsum",
            "00.000.000/0000-00"
        );
        save(fornecedor8);
        LOGGER.info("✅ Fornecedor 8 criado: " + fornecedor8.getName());

        LOGGER.info("🎉 8 fornecedores criados com sucesso!");
    }
}
