# API REST Neomind

Projeto JavaEE com servidor embutido - **sem necessidade de instalação externa de Maven ou Tomcat**.

## 🚀 Como Executar

### **📖 Manuais Completos**
- **[📋 Manual Completo de Instalação](MANUAL-INSTALACAO.md)** - Guia detalhado com configuração do JAVA_HOME

### **🎯 Execução Rápida**

**Pré-requisito:** Java 11+ e JAVA_HOME configurado

**Terminal do Intellij:**
```powershell
Após configurar corretamente a variável de ambiente, vá até o terminal do Intellij
e rode este comando:
.\run.cmd
```

**Testar:** http://localhost:8080/api/api/hello-world

## 📋 Requisitos

- ✅ **Java 11+** (único requisito)
- ✅ **Maven** (embutido via Maven Wrapper)
- ✅ **Servidor** (embutido via Payara Micro)

## 🌐 Endpoints

- **Aplicação**: http://localhost:8080
- **API Base**: http://localhost:8080/api/api/
- **Hello World**: http://localhost:8080/api/api/hello-world

## 🛠️ Tecnologias

- **JavaEE/Jakarta EE** - Framework principal
- **JAX-RS** - REST APIs
- **JPA** - Persistência de dados
- **CDI** - Injeção de dependência
- **Payara Micro** - Servidor embutido
- **PostgreSQL** - Banco de dados
- **H2** - Banco para testes

## 📁 Estrutura

```
src/
├── main/
│   ├── java/
│   │   └── br/com/api/neomind/apirestneomind/
│   │       ├── HelloApplication.java
│   │       └── HelloResource.java
│   └── resources/
│       └── META-INF/
│           └── beans.xml
└── test/
```

## 🔧 Comandos Úteis

```bash
# Compilar apenas
mvnw.cmd compile

# Executar testes
mvnw.cmd test

# Limpar projeto
mvnw.cmd clean

# Gerar WAR
mvnw.cmd package
```
