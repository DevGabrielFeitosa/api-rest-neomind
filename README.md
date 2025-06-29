# API REST Neomind

Projeto JavaEE com servidor embutido - **sem necessidade de instalação externa de Maven ou Tomcat**.

## 🚀 Como Executar

### **📖 Manuais Completos**
- **[📋 Manual Completo de Instalação](MANUAL-INSTALACAO.md)** - Guia detalhado com configuração do JAVA_HOME

### **🎯 Teste Rápido**

**Pré-requisito:** Java 11+ e JAVA_HOME configurado

**Testar:** http://localhost:8080/api/hello-world

## 📋 Requisitos

- ✅ **Java 11+** (único requisito)
- ✅ **Maven** (embutido via Maven Wrapper)
- ✅ **Servidor** (embutido via Payara Micro)

## 🌐 Endpoints

- **Aplicação**: http://localhost:8080
- **API Base**: http://localhost:8080/api/
- **Hello World**: http://localhost:8080/api/hello-world
- **Fornecedores**: http://localhost:8080/api/fornecedores
- **Documentação Completa**: [API-DOCUMENTATION.md](API-DOCUMENTATION.md)

## 🛠️ Tecnologias

- **JavaEE/Jakarta EE** - Framework principal
- **JAX-RS** - REST APIs
- **JPA/Hibernate** - Persistência de dados
- **CDI** - Injeção de dependência
- **Bean Validation** - Validação de dados
- **Payara Micro** - Servidor embutido
- **H2** - Banco em memória para desenvolvimento
- **Maven** - Gerenciamento de dependências

## 📁 Estrutura do Projeto

```
api-rest-neomind/
├── 📄 pom.xml                          # Configuração Maven

├── 📄 API-DOCUMENTATION.md             # Documentação completa da API
├── 📄 MANUAL-INSTALACAO.md             # Manual de instalação
├── 🔧 run.cmd / run.sh                 # Scripts de execução
├── 🔧 mvnw / mvnw.cmd                  # Maven Wrapper
│
├── 📂 src/main/
│   ├── 📂 java/br/com/api/neomind/apirestneomind/
│   │   ├── 📄 HelloApplication.java     # Configuração JAX-RS
│   │   ├── 📄 HelloResource.java        # Endpoint de teste
│   │   ├── 📄 PayaraMicroApp.java       # Classe principal
│   │   │
│   │   ├── 📂 config/
│   │   │   └── 📄 DataInitializer.java  # Inicialização de dados
│   │   │
│   │   ├── 📂 dto/
│   │   │   └── 📄 FornecedorDTO.java    # Data Transfer Object
│   │   │
│   │   ├── 📂 entity/
│   │   │   └── 📄 Fornecedor.java       # Entidade JPA
│   │   │
│   │   ├── 📂 repository/
│   │   │   └── 📄 FornecedorRepository.java # Acesso a dados
│   │   │
│   │   ├── 📂 resource/
│   │   │   └── 📄 FornecedorResource.java   # REST Controller
│   │   │
│   │   └── 📂 service/
│   │       └── 📄 FornecedorService.java    # Lógica de negócio
│   │
│   ├── 📂 resources/
│   │   ├── 📂 META-INF/
│   │   │   ├── 📄 beans.xml             # Configuração CDI
│   │   │   ├── 📄 persistence.xml       # Configuração JPA
│   │   │   └── 📄 payara-resources.xml  # Recursos Payara
│   │   └── 📄 data.sql                  # Dados iniciais
│   │
│   └── 📂 webapp/
│       └── 📂 WEB-INF/
│           └── 📄 web.xml               # Configuração Web
│
└── 📂 src/test/                         # Testes unitários
    ├── 📂 java/
    └── 📂 resources/
```