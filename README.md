# Customer Service

### Requisitos

1. JDK 8
1. Maven 3

### Rodando

1. Clone o projeto: `https://github.com/MurilloMarquesSantos/practical-test1`
1. Entre na pasta `practical-test` e execute: `mvn spring-boot:run`
1. Acesse: `http://localhost:8080/customers`

### Features

1. ✅ Endpoints para criar, editar e excluir customer
2. ✅ Dados validados antes de salvar e editar um customer
3. ✅ Criação de DTOs para formulários POST/PUT
   - Validação para regex com email e restrições para nome e e-mail não vazios usando padrão de projeto Strategy.
4. ✅ Customer paginados
5. ✅ Adição do campo "Address" para o Customer e relação de `@OneToMany` entre Customer e Address 
6. ✅ Tratamento de exceções com `@RestControllerAdvice` e exceções personalizadas para melhor resposta
7. ✅ Resposta dos endpoints com ResponseEntity
8. ✅ Testes unitários garantindo 100% de coverage em classes de serviço e controller
9. ✅ Documentação da API via Swagger (`http://localhost:8080/swagger-ui/index.html`)
10. Endpoints:
    - GET /customers → lista de customers paginados
    - GET /customers/{id} → busca por id
    - POST /customers → cria um novo customer
    - PUT /customers/{id} → atualiza um customer
    - DELETE /customers/{id} → remove um customer
### Exemplo de requisição POST/PUT
```json
{
  "name": "Murillo Marques",
  "email": "murillo@gmail.com",
  "addresses": [
    {
      "street": "Rua 1",
      "city": "São Paulo"
    },
    {
      "street": "Rua 2",
      "city": "Rio de Janeiro"
    }
  ]
}
```


