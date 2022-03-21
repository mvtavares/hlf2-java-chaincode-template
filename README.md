## Passo a passo para instalação completa:

Instalar:

- docker
- docker-compose (1.22+)
- Java 11+
- Maven (3+)

- Criar a pasta /var/hyperledger/stack/ e copiar todo o fonte.

- Entrar na pasta /var/hyperledger/stack/fabric/ e executar os procedimentos 1, 2 e 3 do README.

- Copiar os certificados TLS do peer e da CA no arquivo: /var/hyperledger/stack/broker-api/core/etc/connection.yaml

- Executar em /var/hyperledger/stack/broker-api/
    1. mvn package
    2. docker-compose -f ./docker/docker-compose.yml up -d

- Abrir a página: http://localhost:8080/swagger-ui.html e verificar se a API está funcionando.