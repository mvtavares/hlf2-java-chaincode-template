# spring-boot-boilerplate
Maven multi module project with springboot using java 11.

### Project structure
```
* spring-boot-boilerplate
  - model
  - core
```

### Artifacts
```
spring-boot-boilerplate-model-{version}.jar
spring-boot-boilerplate-{version}.jar (core will be named as parent)
```

### Dependencies
```
* model
  - lombok (provided)
  - jackson
    - jdk8
    - jsr310
    - money (org.zalando)
    
* core
  - model
  - moneta
  - log4j (slf4j)
  - spring boot
    - web
    - actuator
    - jetty
    - tests
  - lombok
```

### Build
```
mvn clean install
```

### Running
From withing core folder
```
mvn package spring-boot:run
```
Application will start using Jetty on port **8080** and actuator on **8081**, both without context-path modifier (on root /)

## Rodando localmente

** Criar entrada no /etc/hosts

  127.0.0.1       localhost peer0.tdc.teste.com.br orderer.teste.com.br

