# vanilla-examples
A collection of example projects demonstrating various technologies.

## Projects:
### SqliteSpringBootDataExample
  - A simple standalone [Spring Boot](http://projects.spring.io/spring-boot/) project that demonstrates [Spring Data JPA](https://spring.io/guides/gs/accessing-data-jpa/) over seperate test and prod [Sqlite](https://www.sqlite.org/) DBs
    - Additional References used:
      - https://gist.github.com/virasak/54436
      - http://stackoverflow.com/questions/15397677/hibernate-sqlite-not-creating-database

### SqliteGenericDaoJpaExample
  - A simple standalone JPA/Hibernate project that demonstrates the use of a GenericDao pattern over a prod [Sqlite](https://www.sqlite.org/) DB.
    - GenericDao implementation is based loosely on the [org.springframework.data.jpa.repository.support.SimpleJpaRepository](http://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/support/SimpleJpaRepository.html) class found in spring-data-commons-1.11.4.RELEASE.jar
    - Additional References used:
      - http://www.codeproject.com/Articles/251166/The-Generic-DAO-pattern-in-Java-with-Spring-3-and
      - http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
