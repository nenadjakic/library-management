# country-service

The **country-Service** is a microservice developed using Kotlin and Spring, integrated with Hibernate for data persistence. It is responsible for managing country information within the library management system.

## Functionality
The main functionalities of the Country Service include:

1. **Country Management:** Allows for the creation, retrieval, updating, and deletion (CRUD) operations of country entities.
2. **Country Information:** Stores details about countries, such as name, code, population, and any other relevant information.
3. **Country Search:** Provides endpoints for searching and retrieving country information based on various criteria, such as name or code.
   
## Data Model
The **country-Service** utilizes a data model defined using JPA annotations, which maps country entities to the underlying relational database. This allows for efficient storage and retrieval of country information.

## REST APIs
The **country-Service** exposes RESTful APIs that allow clients to interact with country data. These APIs adhere to standard REST principles and provide endpoints for performing CRUD operations on countries.

- **GET /countries**
  - **_Description:_** Retrieves a list of all countries.
- **POST /countries**
  - **_Description:_** Creates a new country in the system.
  - **_Request Body:_** JSON object representing the details of the new country to create.
- **PUT /countries/{id}**
  - **_Description:_** Updates the details of an existing country.
  - **_Request Body:_** JSON object representing the updated details of the country.
- **GET /countries/{id}**
  - **_Description:_** Retrieves the details of a specific country by its unique identifier.
  - **_Parameters:_**
    - id: Path-variable. The unique identifier of the country to retrieve.
- **DELETE /countries/{id}**
  - **_Description:_** Deletes a country from the system.
  - **_Parameters:_**
    - id: Path-variable. The unique identifier of the country to delete.
- **GET /countries/page**
  - **_Description:_** Retrieves a paginated list of countries.
  - **_Parameters:_**
    - pageNumber: Query-parameter. The page number to retrieve (required).
    - pageSize: Query-parameter. The number of countries per page (optional).
- **GET /countries/page/{name}**
  - **_Description:_** Retrieves a paginated list of countries filtered by name.
  - **_Parameters:_**
    - name: Path-variable. The name of the country to filter by (required).
    - pageNumber: Query-parameter. The page number to retrieve (required).
    - pageSize: Query-parameter. The number of countries per page (optional).
- **GET /countries/code/{code}**
  - **_Description:_** Retrieves the details of a specific country by its code.
  - **_Parameters:_** 
    - code: Path-variable. The code of the country to retrieve.