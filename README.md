# Spring-Boot-Project
I created this simple project representing a sales platform (client, product, sale) to play around with Spring Boot. It is essentially a REST API with CRUD (almost) functionality.

## Entities
### Client
- id
- firstName
- lastName
- mobile

### Product
- id
- name
- description
- category
- creationDate

### Sale
- id
- creationDate
- buyerId
- sellerId
- productId
- quantity
- price

## API Endpoints
- /{entity} **(GET, POST)**
- /{entity}/{id} **(GET, PUT)**
