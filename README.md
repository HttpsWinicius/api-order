
# Order API

A Order API permite consultar os pedidos de um cliente e calculo do valor total somando todos os pedidos cadastrados.


## Pré-requisitos
Antes de rodar a aplicação, certifique-se de ter instalado:\

- Java 21+

- Maven 3+

- Docker (para rodar o MongoDB)


## Como Rodar a Aplicação

Clone o projeto

```bash
  git clone https://github.com/seu-repositorio/order-api.git
```

Entre no diretório do projeto

```bash
  cd order-api
```

Inicie os containers do MongoDB e RabbitMQ com Docker

```bash
  docker-compose up -d
```

Compile e rode a aplicação

```bash
  mvn spring-boot:run
```

A aplicação estará disponível em http://localhost:8080.

## Documentação da API

#### Listar Pedidos de um Cliente

Retorna uma lista paginada de pedidos de um determinado cliente.

```http
  GET /customer/{customerId}/orders
```

| Parâmetro    | Tipo       | Descrição                           |
| :----------  | :--------- | :---------------------------------- |
| `customerId` | `Long`     | **Obrigatório**. ID do cliente      |
| `page`       | `Integer`  | Página (padrão: 0)                  |
| `pageSize`   | `Integer`  | Tamanho da página (padrão: 10)      |

Exemplo de Resposta:
```json
{
  "data": [
    {
      "orderId": 1001,
      "customerId": 1,
      "total": 150.00,
      "items": [
        { "product": "caderno", "quantity": 2, "price": 75.00 }
      ]
    }
  ],
  "pagination": {
    "currentPage": 0,
    "totalPages": 1,
    "totalItems": 1
  }
}
```

#### Consultar Valor Total dos Pedidos de um Cliente

Retorna o valor total de todos os pedidos de um cliente.

```http
  GET /customer/{customerId}/orders/value/total
```

| Parâmetro    | Tipo       | Descrição                           |
| :----------  | :--------- | :---------------------------------- |
| `customerId` | `Long`     | **Obrigatório**. ID do cliente      |

Exemplo de Resposta:

```json
  {
    "totalValue": 1500.75
  }

```



## Roadmap

- Configurar swagger para documentação

- Adicionar Logger para rastreabilidade e monitoramento

- Configurar agent datadog para observabilidade da API


## Licença

[MIT](https://choosealicense.com/licenses/mit/)