# 🚀 API REST Neomind - Manual de Instalação e Execução

Projeto JavaEE com servidor embutido - **sem necessidade de instalação externa de Maven ou Tomcat**.

---

## 📋 **PRÉ-REQUISITOS**

### ✅ **Único Requisito: Java 11 ou superior**

**Verificar se o Java está instalado:**
1. Abra o terminal (CMD ou PowerShell)
2. Execute: `java -version`
3. Deve aparecer algo como: `openjdk version "17.0.11"`

**Se não tiver Java instalado:**
- Baixe em: https://www.oracle.com/br/java/technologies/downloads/
- Instale a versão **Java 17 LTS** (recomendado)

---

## ⚙️ **CONFIGURAÇÃO DO JAVA_HOME**

### **🪟 Windows - Método 1: Interface Gráfica (Recomendado)**

1. **Pressione `Windows + R`**
2. **Digite `sysdm.cpl`** e pressione Enter
3. **Clique em "Variáveis de Ambiente"**
4. **Em "Variáveis do Sistema", clique "Novo"**
5. **Configure:**
   - **Nome da variável:** `JAVA_HOME`
   - **Valor da variável:** `C:\Program Files\Java\jdk-17`
   
   > ⚠️ **Importante:** Verifique se o caminho existe! Pode ser diferente como:
   > - `C:\Program Files\Java\jdk-17.0.11`
   > - `C:\Program Files\Eclipse Adoptium\jdk-17.0.11.9-hotspot`

6. **Clique "OK" em todas as janelas**
7. **Feche e reabra o terminal**

# Verificar Java
java -version
```

---

## 🚀 **COMO EXECUTAR A APLICAÇÃO**

### **📁 Passo 1: Navegar até a pasta do projeto**

```bash
cd C:\Projetos\api-rest-neomind
```

### **🎯 Passo 2: Executar a aplicação**

#### **Método 1: Script Automático (Mais Fácil)**

**No PowerShell:**
```powershell
Vá até a pasta do projeto que você importou, aperte SHITF no teclado e clique com o botão
direito dentro do diretório do projeto e selecione a opção "Abrir janela do PowerShell aqui".
Após isso digite o comando:
.\run.cmd
```

#### **Método 2: Comandos Manuais**

```bash
# 1. Compilar o projeto
mvnw.cmd clean package -DskipTests

# 2. Executar (substitua pelo seu caminho do Java)
set JAVA_HOME=C:\Program Files\Java\jdk-17
java -jar %USERPROFILE%\.m2\repository\fish\payara\extras\payara-micro\6.2024.2\payara-micro-6.2024.2.jar --deploy target\api.war --contextroot /api --port 8080
```

---

## ⏱️ **O QUE ESPERAR DURANTE A EXECUÇÃO**

### **1️⃣ Compilação (10-30 segundos)**
```
[INFO] Scanning for projects...
[INFO] Building api-rest-neomind 1.0-SNAPSHOT
[INFO] BUILD SUCCESS
```

### **2️⃣ Download do Payara Micro (primeira vez - 2-5 minutos)**
```
Downloading payara-micro-6.2024.2.jar (100 MB)
```

### **3️⃣ Inicialização do Servidor (5-15 segundos)**
```
[INFO] Payara Micro 6.2024.2 ready in 12.436 (ms)
Payara Micro URLs:
http://LAPTOP-SEU-PC:8080/api
```

### **4️⃣ Aplicação Pronta! ✅**
```
'api' REST Endpoints:
GET     /api/api/hello-world
```

---

## 🌐 **TESTANDO A APLICAÇÃO**

### **🌍 No Navegador:**
Acesse: http://localhost:8080/api/api/hello-world

### **📱 Resposta esperada:**
```
Hello, World!
```

### **🔧 Usando cURL:**
```bash
curl http://localhost:8080/api/api/hello-world
```

---

## ⏹️ **PARANDO A APLICAÇÃO**

- **Pressione `Ctrl + C`** no terminal
- Ou **feche a janela do terminal**

---

## 📊 **URLs DA APLICAÇÃO**

- **🏠 Aplicação**: http://localhost:8080/api
- **🔗 API Hello World**: http://localhost:8080/api/api/hello-world

---

## 🛠️ **TECNOLOGIAS UTILIZADAS**

- **JavaEE/Jakarta EE** - Framework principal
- **JAX-RS** - REST APIs
- **JPA** - Persistência de dados
- **CDI** - Injeção de dependência
- **Payara Micro** - Servidor embutido
- **PostgreSQL** - Banco de dados
- **H2** - Banco para testes

---

## 📞 **SUPORTE**

Se encontrar problemas:
1. Verifique se o Java está instalado: `java -version`
2. Verifique se o JAVA_HOME está configurado: `echo %JAVA_HOME%`
3. Certifique-se de estar na pasta correta do projeto
4. Use `.\run.cmd` no PowerShell ou `run.cmd` no CMD

---

## 🎯 **RESUMO RÁPIDO**

```bash
# 1. Navegar para o projeto
cd C:\Projetos\api-rest-neomind

# 2. Executar (CMD)
run.cmd

# 2. Executar (PowerShell)  
.\run.cmd

# 3. Acessar no navegador
http://localhost:8080/api/api/hello-world
```

**🎉 Pronto! Sua aplicação JavaEE está rodando!**
