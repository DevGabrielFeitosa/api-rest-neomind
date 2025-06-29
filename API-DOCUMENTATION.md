# 📋 API REST Neomind - Documentação dos Endpoints

Esta documentação apresenta todos os endpoints disponíveis na API REST para gerenciamento de fornecedores.

## 🌐 Base URL

```
http://localhost:8080/api
```

## 📊 Códigos de Status HTTP

| Código | Descrição |
|--------|-----------|
| `200 OK` | Sucesso (GET, PUT) |
| `201 Created` | Recurso criado com sucesso (POST) |
| `204 No Content` | Recurso deletado com sucesso (DELETE) |
| `400 Bad Request` | Dados inválidos ou malformados |
| `404 Not Found` | Recurso não encontrado |
| `500 Internal Server Error` | Erro interno do servidor |

## 🔧 Headers Obrigatórios

Para todas as requisições que enviam dados (POST, PUT):

```
Content-Type: application/json
Accept: application/json
```

---

## 📋 Modelo de Dados - Fornecedor

### Estrutura do FornecedorDTO

```json
{
  "id": 1,
  "name": "Nome da Empresa Ltda",
  "email": "contato@empresa.com.br",
  "comment": "Comentário opcional sobre o fornecedor",
  "cnpj": "12.345.678/0001-90"
}
```

### Validações

| Campo | Tipo | Obrigatório | Validação |
|-------|------|-------------|-----------|
| `id` | Long | Não | Gerado automaticamente |
| `name` | String | Sim | 2-100 caracteres |
| `email` | String | Sim | Formato de email válido, máx 150 caracteres |
| `comment` | String | Não | Máximo 500 caracteres |
| `cnpj` | String | Sim | Formato: XX.XXX.XXX/XXXX-XX |

---

## 🔗 Endpoints

### 1. 👋 Hello World (Teste de Conectividade)

**GET** `/hello-world`

Endpoint simples para testar se a API está funcionando.

#### Request
```bash
curl -X GET http://localhost:8080/api/hello-world
```

#### Response
```
Status: 200 OK
Content-Type: text/plain

Hello, World!
```

---

### 2. 📋 Listar Todos os Fornecedores

**GET** `/fornecedores`

Retorna uma lista com todos os fornecedores cadastrados.

#### Request
```bash
curl -X GET \
  http://localhost:8080/api/fornecedores \
  -H "Accept: application/json"
```

#### Response - Sucesso
```json
Status: 200 OK
Content-Type: application/json

[
  {
    "id": 1,
    "name": "Empresa ABC Ltda",
    "email": "contato@empresaabc.com.br",
    "comment": "Fornecedor de materiais de escritório",
    "cnpj": "12.345.678/0001-90"
  },
  {
    "id": 2,
    "name": "Fornecedor XYZ S.A.",
    "email": "vendas@fornecedorxyz.com.br",
    "comment": "Especializada em equipamentos de informática",
    "cnpj": "98.765.432/0001-10"
  }
]
```

#### Response - Lista Vazia
```json
Status: 200 OK
Content-Type: application/json

[]
```

#### Response - Erro
```json
Status: 500 Internal Server Error
Content-Type: application/json

{
  "error": "Erro interno do servidor: [mensagem do erro]"
}
```

---

### 3. 🔍 Buscar Fornecedor por ID

**GET** `/fornecedores/{id}`

Retorna os dados de um fornecedor específico pelo ID.

#### Request
```bash
curl -X GET \
  http://localhost:8080/api/fornecedores/1 \
  -H "Accept: application/json"
```

#### Response - Sucesso
```json
Status: 200 OK
Content-Type: application/json

{
  "id": 1,
  "name": "Empresa ABC Ltda",
  "email": "contato@empresaabc.com.br",
  "comment": "Fornecedor de materiais de escritório",
  "cnpj": "12.345.678/0001-90"
}
```

#### Response - Não Encontrado
```json
Status: 404 Not Found
Content-Type: application/json

{
  "error": "Fornecedor não encontrado com ID: 1"
}
```

---

### 4. ➕ Criar Novo Fornecedor

**POST** `/fornecedores`

Cria um novo fornecedor no sistema.

#### Request
```bash
curl -X POST \
  http://localhost:8080/api/fornecedores \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "name": "Nova Empresa Ltda",
    "email": "contato@novaempresa.com.br",
    "comment": "Fornecedor de serviços de limpeza",
    "cnpj": "11.222.333/0001-44"
  }'
```

#### Response - Sucesso
```json
Status: 201 Created
Content-Type: application/json

{
  "id": 3,
  "name": "Nova Empresa Ltda",
  "email": "contato@novaempresa.com.br",
  "comment": "Fornecedor de serviços de limpeza",
  "cnpj": "11.222.333/0001-44"
}
```

#### Response - Dados Inválidos
```json
Status: 400 Bad Request
Content-Type: application/json

{
  "error": "Nome é obrigatório"
}
```

#### Exemplos de Erros de Validação
```json
{
  "error": "Email deve ter formato válido"
}
```

```json
{
  "error": "CNPJ deve estar no formato XX.XXX.XXX/XXXX-XX"
}
```

```json
{
  "error": "Nome deve ter entre 2 e 100 caracteres"
}
```

---

### 5. ✏️ Atualizar Fornecedor

**PUT** `/fornecedores/{id}`

Atualiza os dados de um fornecedor existente.

#### Request
```bash
curl -X PUT \
  http://localhost:8080/api/fornecedores/1 \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "name": "Empresa ABC Ltda - Atualizada",
    "email": "novo-contato@empresaabc.com.br",
    "comment": "Fornecedor de materiais de escritório e limpeza",
    "cnpj": "12.345.678/0001-90"
  }'
```

#### Response - Sucesso
```json
Status: 200 OK
Content-Type: application/json

{
  "id": 1,
  "name": "Empresa ABC Ltda - Atualizada",
  "email": "novo-contato@empresaabc.com.br",
  "comment": "Fornecedor de materiais de escritório e limpeza",
  "cnpj": "12.345.678/0001-90"
}
```

#### Response - Não Encontrado
```json
Status: 404 Not Found
Content-Type: application/json

{
  "error": "Fornecedor não encontrado com ID: 999"
}
```

#### Response - Dados Inválidos
```json
Status: 400 Bad Request
Content-Type: application/json

{
  "error": "Email deve ter formato válido"
}
```

---

### 6. 🗑️ Deletar Fornecedor

**DELETE** `/fornecedores/{id}`

Remove um fornecedor do sistema.

#### Request
```bash
curl -X DELETE \
  http://localhost:8080/api/fornecedores/1 \
  -H "Accept: application/json"
```

#### Response - Sucesso
```
Status: 204 No Content
```

#### Response - Não Encontrado
```json
Status: 404 Not Found
Content-Type: application/json

{
  "error": "Fornecedor não encontrado com ID: 999"
}
```

---

## 🧪 Exemplos de Teste Completo

### Cenário: CRUD Completo

```bash
# 1. Testar conectividade
curl http://localhost:8080/api/hello-world

# 2. Listar fornecedores (inicialmente vazio)
curl http://localhost:8080/api/fornecedores

# 3. Criar primeiro fornecedor
curl -X POST http://localhost:8080/api/fornecedores \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Empresa Teste Ltda",
    "email": "teste@empresa.com.br",
    "comment": "Fornecedor para testes",
    "cnpj": "12.345.678/0001-90"
  }'

# 4. Buscar fornecedor criado
curl http://localhost:8080/api/fornecedores/1

# 5. Atualizar fornecedor
curl -X PUT http://localhost:8080/api/fornecedores/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Empresa Teste Atualizada Ltda",
    "email": "novo@empresa.com.br",
    "comment": "Fornecedor atualizado",
    "cnpj": "12.345.678/0001-90"
  }'

# 6. Listar todos (deve mostrar o fornecedor atualizado)
curl http://localhost:8080/api/fornecedores

# 7. Deletar fornecedor
curl -X DELETE http://localhost:8080/api/fornecedores/1

# 8. Verificar se foi deletado (deve retornar 404)
curl http://localhost:8080/api/fornecedores/1
```

---

## 🔧 Testando com Postman

### Configuração da Collection

1. **Base URL**: `http://localhost:8080/api`
2. **Headers Globais**:
   - `Content-Type: application/json`
   - `Accept: application/json`

### Requests para Importar

Crie as seguintes requests no Postman:

1. **GET Hello World**: `{{baseUrl}}/hello-world`
2. **GET All Fornecedores**: `{{baseUrl}}/fornecedores`
3. **GET Fornecedor by ID**: `{{baseUrl}}/fornecedores/1`
4. **POST Create Fornecedor**: `{{baseUrl}}/fornecedores`
5. **PUT Update Fornecedor**: `{{baseUrl}}/fornecedores/1`
6. **DELETE Fornecedor**: `{{baseUrl}}/fornecedores/1`

---

## 🚀 Como Iniciar a API

1. **Compilar o projeto**:
   ```bash
   mvn clean package -DskipTests
   ```

2. **Executar no IntelliJ**:
   - Execute a classe `PayaraMicroApp.main()`

3. **Ou executar via linha de comando**:
   ```bash
   java -cp target/classes br.com.api.neomind.apirestneomind.PayaraMicroApp
   ```

4. **Verificar se está funcionando**:
   ```bash
   curl http://localhost:8080/api/hello-world
   ```

---

## 📞 Suporte

Para dúvidas ou problemas, consulte:
- [Manual de Instalação](MANUAL-INSTALACAO.md)
- [Testes da API](TESTE-API-FORNECEDORES.md)
- [README Principal](README.md)
