# 🐉 API de Gerenciamento de RPG - Iniciative 1.0

Este projeto tem como objetivo criar uma API em Java puro para registrar **jogadores** 
de uma mesa de RPG, permitir a criação de múltiplos **personagens** para cada jogador, 
e disponibilizar essas informações através de **endpoints HTTP**.

> Projeto desenvolvido com foco em estrutura limpa e boas práticas, 
> utilizando apenas bibliotecas Java, com exceção do [Gson](https://github.com/google/gson) da Google.

---

## 📌 Funcionalidades

- ✅ Cadastro de jogadores de RPG
- ✅ Associação de múltiplos personagens a um jogador
- ✅ Gerenciamento de status de personagens (em construção)
- ✅ API REST acessível via `HttpServer` embutido
- ✅ Estrutura modular com boas práticas de arquitetura

---

## 🧱 Tecnologias e Bibliotecas

- **Java 17**
- [Gson](https://github.com/google/gson) – Serialização/deserialização JSON
- [JUnit](https://junit.org/junit5/) – Testes unitários

---

## 🎯 Padrões de Projeto Utilizados

- **MVC (Model-View-Controller)**  
  Separação clara entre dados, regras de negócio e controle da aplicação.

- **Singleton**  
  Usado para garantir instância única de alguns serviços.

- **Domain-Driven Design (DDD)**  
  Foco na modelagem do domínio com entidades e lógica orientada ao negócio.

- **Repository Pattern**  
  Abstração da lógica de persistência e acesso aos dados.

---

## 🗂️ Estrutura do Projeto

```
📦 src/
├── com.bugred.API
│   ├── StartServer.java         # Inicializador do servidor HTTP
│   ├── handler/                 # Handlers responsáveis pelos endpoints
│   ├── model/                   # Entidades do domínio (Player, Person, etc.)
│   ├── service/                 # Regras de negócio e lógica de serviço
│   ├── enums/                   # Classes enumaredas
│   ├── repository/              # Camada de acesso a dados (mockado por enquanto)
│   ├── dto/                     # Camada de transformação de dados
│   ├── db/                      # Classe para carregar os dados
│   └── db/                      # Loader de dados mock
└─── resources
     └── mockData.json/          # Banco de dados temporario
```

---

## 🚀 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   cd nome-do-repositorio
   ```

2. Compile o projeto:
   ```bash
   javac -d out $(find ./src -name "*.java")
   ```

3. Execute o servidor:
   ```bash
   java -cp out com.bugred.API.StartServer
   ```

4. Acesse via navegador ou ferramenta como Postman:
   ```
   GET http://localhost:8080/players
   ```

---

## 🛠️ Próximos Passos

- [ ] Adicionar persistência com arquivos ou banco leve (H2 ou SQLite)
- [ ] Adicionar autenticação básica
- [ ] Criar testes de integração
- [ ] Documentar endpoints com Swagger ou README

---

## 🤝 Contribuição

Sinta-se à vontade para abrir _issues_, sugerir melhorias ou enviar _pull requests_! 
Toda ajuda é bem-vinda para evoluir este projeto.

---

## 📄 Licença

Este projeto está sob a licença MIT – veja o arquivo [LICENSE](LICENSE) para mais detalhes.