# spring-data-jpa
- Jpa(Java Persistence Api) - specifications for object relational mapping
- Hibernate(ORM Framework) - jpa implementation

## Repository
- Abstraction and Encapsulation layer for database operations
- Default implementation for 
- CrudRepository
  - Extends Repository
  - provides CRUD functions

- PagingAndSortingRepository
  - Extends CrudRepository
  - provides methods to do pagination and sort records

- JpaRepository
  - Extends PagingandSortingRepository
  - Provides Methods such as flushing the persistence context and delete records in a batch
  - Querying methods return List's instead of Iterable’s
