# 📌 NLW Connect 19 - API de Eventos e Inscrições

![NLW Connect 19](<img src="https://yt3.googleusercontent.com/SkyQrFYa4v7vVMpC4nqsdlOaIHWw1HvRUxf4940GcJfRWgGUxtMJ9WdTTJOCj0a1ddJYPKxe=s160-c-k-c0x00ffffff-no-rj" alt="Rocketseat Logo" width="48">
)

🚀 Projeto desenvolvido na **Next Level Week Connect (NLW 19)** da **Rocketseat**, trilha **Java**. O foco desta edição foi a criação de uma **API para controle de eventos e inscrições**, utilizando tecnologias modernas para garantir escalabilidade e eficiência.

---

## 🛠 Tecnologias Utilizadas
- **Java 23** ⚡  
- **Spring Boot** 🌱  
- **MySQL** 🐬  
- **Docker** 🐳 (Banco de dados rodando em um container)  
- **JPA/Hibernate** 🗄️  
- **Swagger/OpenAPI** 📜 *(Para documentação da API)*  

---

## 🚀 Como Rodar o Projeto

### 🔹 Pré-requisitos
Antes de iniciar, certifique-se de ter instalado na sua máquina:
- **JDK 23+**
- **Docker e Docker Compose**
- **Maven**

### 🔹 Passo a Passo

1. **Clone o repositório**  
```bash
  git clone https://github.com/Lucas-319/Agenda-Evento
  cd events
```

2. **Suba o container do banco de dados**  
```bash
  docker-compose up -d
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
📌 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  

---

## 📌 Funcionalidades
✅ Cadastro de evento  
✅ Consulta de eventos  
✅ Inscrição e inscrição por indicação  

---

## ✨ Endpoints Principais

| Método | Endpoint                                | Descrição |
|---------|----------------------------------------|------------|
| `POST`  | `/events`                              | Cadastra um novo evento |
| `GET`   | `/events`                              | Lista todos os eventos |
| `GET`   | `/events/id/{id}`                      | Lista um evento específico pelo ID |
| `GET`   | `/events/{prettyName}`                 | Lista um evento específico pelo prettyName do evento |
| `POST`  | `/subscription/{prettyname}`           | Inscrição para o evento |
| `POST`  | `/subscription/{prettyname}/{idIndicador}` | Inscrição por indicação |

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


