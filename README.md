# Documentation du Projet Mediatheque

Le projet est construit sur une architecture microservices, divisé en deux API distinctes : la première API gère les produits, catégories, commandes et utilisateurs, tandis que la seconde API se concentre sur la gestion et la recherche de produits.

## Choix Techniques
 
1. JPA (Java Persistence API): Gère la persistance des données dans des bases de données SQL.
2. Lombok: Simplifie la création de modèles en générant automatiquement des getters, setters et autres méthodes.
3. MapStruct: Pour mapper les entités JPA vers des DTOs et vice versa.
4. PostgreSQL: Base de données choisie pour la persistance des données.
5. RestAssured: Utilisé pour tester les points de terminaison des API REST.


## Requetes


### Product

- Get all product : `http://localhost:8080/products/list`
- Get product by ID : `http://localhost:8080/products/getBy/{id}`
- Get products filtered by price range : `http://localhost:8080/products/filterByPrice?minPrice={minPrice}&maxPrice={maxPrice}`
- Post a new product : `http://localhost:8080/products/save`
- Delete a product : `http://localhost:8080/products/delete/{id}`

**Call the second API from the first one**
- Get all product : `http://localhost:8080/callSecondApi/products`
- Search products by ID and availability : `http://localhost:8080/callSecondApi/products/search?id={id}&available={available}`

**Using the second API**
- Get all product : `http://localhost:8081/products`
- Search products by ID and availability : `http://localhost:8081/products/search?id={id}&available={available}`
- Post a new product with availability : `http://localhost:8081/products/save`

### Category

- Get all categories : `http://localhost:8080/category/list`
- Get category by ID : `http://localhost:8080/category/getBy/{id}`
- Post a new category : `http://localhost:8080/category/save`
- Delete a category : `http://localhost:8080/category/delete/{id}`

### Order

- Get all orders : `http://localhost:8080/orders/list`
- Get order by ID : `http://localhost:8080/orders/getBy/{id}`
- Post a new order : `http://localhost:8080/orders/save`
- Delete a order : `http://localhost:8080/orders/delete/{id}`

### User

- Get all users : `http://localhost:8080/users/list`
- Get user by ID : `http://localhost:8080/users/getBy/{id}`
- Post a new user : `http://localhost:8080/users/save`
- Delete a user : `http://localhost:8080/users/delete/{id}`



