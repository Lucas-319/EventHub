# 📌 NLW Connect 19 - EventHub

🚀 Projeto desenvolvido na **Next Level Week Connect (NLW 19)** da **Rocketseat**, trilha **Java**. O foco desta edição foi a criação de uma **API para controle de eventos e inscrições**, utilizando tecnologias modernas para garantir escalabilidade e eficiência.

---

## 🛠 Tecnologias Utilizadas
- **Java 21** ⚡  
- **Spring Boot** 🌱  
- **MySQL** 🐬  
- **Docker** 🐳 (Banco de dados rodando em um container)  
- **JPA/Hibernate** 🗄️  
- **Swagger/OpenAPI** 📜 *(Para documentação da API)*  

---

## 🚀 Como Rodar o Projeto

### 🔹 Pré-requisitos
Antes de iniciar, certifique-se de ter instalado na sua máquina:
- **JDK 21**
- **Docker**
- **Maven**

### 🔹 Passo a Passo

1. **Clone o repositório**  
```bash
  git clone https://github.com/Lucas-319/EventHub
```

2. **Suba o container do banco de dados**  
```bash
  docker compose up -d
```

3. **Configure o banco no `application.properties` ou `application.yml`**  
Exemplo:
```properties
spring.datasource.url= jdbc:mysql://localhost:3306/db_events
spring.datasource.username=root
spring.datasource.password=mysql
spring.jpa.hibernate.ddl-auto=update
```

4. **Execute a aplicação**  
```bash
  mvn spring-boot:run
```

5. **Acesse a API** via Swagger UI:  
📌 [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)  

---

## 📌 Funcionalidades
✅ Cadastro de evento  
✅ Consulta de eventos  
✅ Inscrição e inscrição por indicação  
✅ Ranking de indicações


---

## ✨ Endpoints Principais

| Método | Endpoint                                | Descrição |
|---------|----------------------------------------|------------|
| `POST`  | `/events`                              | Cadastra um novo evento |
| `GET`   | `/events`                              | Lista todos os eventos |
| `GET`   | `/events/{id}=`                 | Lista um evento específico pelo ID |
| `GET`   | `/events/name/{prettyName}`                 | Lista um evento específico pelo prettyName do evento |
| `POST`  | `/subscription/{prettyname}`           | Inscrição para o evento |
| `POST`  | `/subscription/{prettyname}/{indicatorId}` | Inscrição por indicação |
| `GET`   | `/subscription/{prettyname}/ranking`   | Retorna o ranking geral de indicações para um evento específico |
| `GET`   | `/subscription/{prettyname}/ranking/{indicatorId}`   | Retorna a posição de um usuário específico no ranking do evento |
---

## 📄 Exemplos de Requisições

### 🔹 **Cadastrar Evento** (`POST /events`)
**Body:**
```json
{
	"title": "CodeCraft Summit 2025",
	"location": "Online",
	"price": 0.0,
	"startDate": "2025-03-16",
	"endDate": "2025-03-18",
	"startTime": "19:00:00",
	"endTime": "21:00:00"
}
```

### 🔹 **Inscrição de Usuário** (`POST /subscription/{prettyname}`)
**Body:**
```json
{
	"name": "Tony Stark",
	"email": "tony@stark.com"
}
```

---

## 📜 Licença
Este projeto está sob a **MIT License**. Sinta-se livre para usá-lo e modificá-lo como quiser!


