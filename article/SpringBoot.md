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

## running it
To first run the spring boot implementation, we'll compile it to a jar:
`./mvnw clean package`
`java -jar spring-boot-0.0.1-SNAPSHOT.jar`

First, note the startup time (from the startup logs):
`Started Application in 8.332 seconds (JVM running for 8.758)`

Then, we'll look at the memory consumption (in kb):
`ps x -o rss,vsz,command | grep spring`
`544984 25725776 java -jar spring-boot-0.0.1-SNAPSHOT.jar`
`419288 25692868 java -jar target/reactive-spring-boot-0.0.1-SNAPSHOT.jar`
` 76068  5844720 node /Users/rfj/code/blog/a-case-for-reactive/article/framework/simple-next-app/node_modules/.bin/next start`

That are 500MB of RAM taken for the simplest possible MVC application I can think of!