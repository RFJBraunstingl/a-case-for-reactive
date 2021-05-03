# Spring Boot implementation of Jokr

## Data persistence
Because we want to use the simplest form of persistence, we'll advise Spring Boot to
setup an in-memory H2 database, where we can store our data:

`application.properties`
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

