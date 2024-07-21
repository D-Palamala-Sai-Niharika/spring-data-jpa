# spring-data-jpa
- Jpa(Java Persistence Api) - specifications for object relational mapping
- Hibernate(ORM Framework) - jpa implementation

## Repository
- Abstraction and Encapsulation layer for database operations
- Default implementation
- CrudRepository
  - Extends Repository
  - provides CRUD functions
  - Optional<T> findById(ID primaryKey);
 ```
<S extends T> S save(S entity); 
<S extends T> Iterable<S> saveAll(Iterable<S> entities);
Optional<T> findById(ID primaryKey);
boolean existsById(ID id);
Iterable<T> findAll();
Iterable<T> findAllById(Iterable<ID> ids);
long count();
void deleteById(ID id);
void delete(T entity);
void deleteAllById(Iterable<? extends ID> ids);
void delete(Iterable<? extends T> entities);
void deleteAll();
```

- PagingAndSortingRepository
  - Extends CrudRepository
  - provides methods to do pagination and sort records
 ```
findAll(Pageable pageable)
Pageable object with following properties 
Page size
Current page number
Sorting
findAll(Sort sort)
Sort Object with the Property on Which the sorting is to be done
Sort.by(propName)
```
- JpaRepository
  - Extends PagingandSortingRepository
  - Provides Methods such as flushing the persistence context and delete records in a batch
  - Querying methods return List's instead of Iterable’s
