# Projeto Mar Aberto

## Descrição

O projeto Mar Aberto é uma iniciativa que visa fornecer informações e dados oceanográficos sobre a qualidade da água dos
oceanos de uma forma simples e prática, o projeto surgiu como uma forma de tornarmos as pessoas concientes sobre a
importância da preservação dos ocêanos e os impactos que as mudanças nos ecossistemas marinhos podem causar na
sociedade, ao utilizar dados históricos podemos de forma clara observar as mudanças na natureza dos oceanos que
geralmente ocorrem lentamente e de forma gradual.

Além do objetivo anterior temos como finalidade estimular o debate e despertar a curiosidade das pessoas para incentivar
e servir de berço a novas iniciativas e projetos de preservação dos ecossistemas marinhos.

O nome do projeto nasceu numa alusão ao termo “código aberto”, onde utilizamos dados oceanográficos abertos compilados
em formas de fácil acesso ao público.

## Dados

Os dados utilizados estão abertamente disponíveis no World Ocean Database (WOD), uma iniciativa do National Centers for
Environmental Information um orgão do NOAA (National Oceanic and Atmospheric Administration), o WOD é a maior coleção do
mundo de dados de perfis oceânicos, uniformemente formatados, com qualidade controlada e disponíveis publicamente.

É uma ferramenta poderosa para pesquisas oceanográficas, climáticas e ambientais, e o resultado de mais de 20 anos de
esforços coordenados para incorporar dados de instituições, agências, pesquisadores individuais e iniciativas de
recuperação de dados em um único banco de dados.

Os dados WOD abrangem desde a viagem do Capitão Cook em 1772 até o período Argo contemporâneo, tornando-os um recurso
valioso para análises históricas e de longo prazo do clima oceânico.

## Tecnologias

O projeto Mar Aberto foi desenvolvido utilizando as seguintes tecnologias:

### Backend

- Java 21
- Spring Boot 3.3.0
- Postgres 16

### Frontend

- Vite 5.2.0
- React 18.2
- Typescript 5.4
- Highcharts 11.4
- Highmaps 11.4
- MUI 5.15

## Instruções

Para rodar o projeto localmente, uma imagem docker foi disponibilizada para facilitar a execução do projeto, basta
informar as variáveis de ambiente no arquivo `.env.sample` e executar o comando:

```bash
docker run --rm \
    -p 8080:8080 \
    -e DB_HOST=<Host do banco utilizado> \
    -e DB_PORT=<Porta do banco utilizado> \
    -e DB_NAME=<Nome do banco utilizado> \
    -e DB_USER=<Usuário do banco utilizado> \
    -e DB_PASS=<Senha do banco utilizado> \
    .
```

### Alternativa

Caso prefira rodar o projeto localmente, basta seguir os passos abaixo:

1. Clone o repositório:

```bash
git clone
```

2. Instale as dependências:

```bash
mvn install
```

3. Crie uma cópia do arquivo `.env.sample` e informe as variáveis de ambiente:

```bash
cp .env.sample .env
```

4Execute o projeto:

```bash
mvn spring-boot:run
```

Obs: O projeto foi desenvolvido utilizando o Java 21, é necessário ter o Java 21 instalado na máquina para rodar o
projeto.

## Endpoints

Uma coleção do Postman foi disponibilizada no
arquivo [OpenSea Project.postman_collection.json](/OpenSea Project.postman_collection.json) para facilitar a execução
dos
endpoints.

O projeto Mar Aberto disponibiliza os seguintes endpoints:

### POST /auth/signup

Endpoint para cadastro de usuários, é necessário informar um email e senha para realizar o cadastro.

#### Request

```bash
curl --location 'http://localhost:8080/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "user",
    "password": "password",
    "email": "user@mail.com"
}'
```

#### Response

```json
{
  "id": 1,
  "username": "user",
  "email": "user@mail.com"
}
```

### GET /water-data

Endpoint para buscar os dados de qualidade da água, é obrigatório informar o ano e a profundidade para realizar a busca
via query params como o exemplo abaixo além de informar o usuário via autenticação Basic.

#### Request

```bash
curl --location 'http://localhost:8080/water-data?year=1966&depth=0' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
```

#### Response - 200 OK

```json
{
  "totalPages": 8228,
  "totalElements": 8228,
  "pageable": {
    "pageNumber": 0,
    "pageSize": 1,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "size": 1,
  "content": [
    {
      "id": 7502731,
      "latitude": -39.9984,
      "longitude": -32.8328,
      "depth": 0,
      "year": 2018,
      "temperature": 16.2,
      "salinity": 35.259,
      "oxygen": null,
      "phosphate": null,
      "silicate": null,
      "ph": null
    }
  ],
  "number": 0,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "first": true,
  "last": false,
  "numberOfElements": 1,
  "empty": false
}
```

### GET /water-data/{id}

Endpoint para buscar um dado específico de qualidade da água, é obrigatório informar o id do dado via path param e o
usuário via autenticação Basic.

#### Request

```bash
curl --location 'http://localhost:8080/water-data/1' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
```

#### Response - 200 OK

```json
{
  "id": 1,
  "latitude": -39.9984,
  "longitude": -32.8328,
  "depth": 0,
  "year": 2018,
  "temperature": 16.2,
  "salinity": 35.259,
  "oxygen": null,
  "phosphate": null,
  "silicate": null,
  "ph": null
}
```

### POST /water-data

Endpoint para cadastrar um dado de qualidade da água, é necessário informar os dados de latitude, longitude,
profundidade, ano, temperatura e salinidade via body e o usuário via autenticação Basic.

#### Request

```bash
curl --location 'http://localhost:8080/water-data' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--data '{
    "latitude": 50,
    "longitude": 60,
    "depth": 0,
    "year": 2025,
    "temperature": 16.2,
    "salinity": 35.259,
    "oxygen": null,
    "phosphate": null,
    "silicate": null,
    "ph": null
}'
```

#### Response - 201 Created

```json
{
  "id": 1,
  "latitude": 50,
  "longitude": 60,
  "depth": 0,
  "year": 2025,
  "temperature": 16.2,
  "salinity": 35.259,
  "oxygen": null,
  "phosphate": null,
  "silicate": null,
  "ph": null
}
```

### PUT /water-data/{id}

Endpoint para atualizar um dado de qualidade da água, é necessário informar o id do dado via path param e os dados de
latitude, longitude, profundidade, ano, temperatura e salinidade via body e o usuário via autenticação Basic.

#### Request - 200 OK

```bash
curl --location --request PUT 'http://localhost:8080/water-data/1' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--data '{
    "latitude": 50,
    "longitude": 60,
    "depth": 0,
    "year": 2026,
    "temperature": 16.2,
    "salinity": 35.259,
    "oxygen": null,
    "phosphate": null,
    "silicate": null,
    "ph": null
}'
```

#### Response - 200 OK

```json
{
  "id": 1,
  "latitude": 50,
  "longitude": 60,
  "depth": 0,
  "year": 2026,
  "temperature": 16.2,
  "salinity": 35.259,
  "oxygen": null,
  "phosphate": null,
  "silicate": null,
  "ph": null
}
```

### DELETE /water-data/{id}

Endpoint para deletar um dado de qualidade da água, é necessário informar o id do dado via path param e o usuário via
autenticação Basic.

#### Request

```bash
curl --location --request DELETE 'http://localhost:8080/water-data/1' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
```

#### Response - 204 No Content
