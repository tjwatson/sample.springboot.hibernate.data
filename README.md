# Sample Spring Boot application using Spring Data with Hibernate running on Open Liberty
To build and run on Liberty:
```
./mvnw liberty:run
```

The current application has an issue with the Hibernate version used with Spring Boot.
To override the version edit the pom.xml and use the `hibernate.version` to use the last
known version of Hibernate that works (version 6.5.3.Final)

