## Hyperledger Fabric 2.2 - Rede Teste

Follow the instructions in the Fabric docs to install the Fabric Binaries:
https://hyperledger-fabric.readthedocs.io/en/latest/install.html

## Ambiente

Criar um arquivo .env na raiz do /var/hyperledger/fabric com o conteúdo abaixo:

IMAGE_TAG=2.2
COMPOSE_PROJECT_NAME=backend

## Iniciando
./network.sh up 

## Criar o canal principal do blockchain
./network.sh createChannel -c main

Caso o canal já tenha sido criado, apenas:

./network.sh up

## Instalar:
    - Gradle (https://gradle.org) 
    - Java 11 (sdkman.io)

## Instalando o smart contract (chaincode) (1a vez)
./network.sh deployCC

## Atualizando chaincode (Enviar nova versão)
./network.sh deployCC -ccv 1.1 -ccs 2

./network.sh deployCC -ccv 1.2 -ccs 3

...

## Parar os containers 
./network.sh stop

## Limpar a rede (remove todos os dados)

./network.sh down
rm -Rf /var/hyperledger/couchdbteste0/

## Acessar diretamente o CouchDB

TDC

- URL: http://localhost:5984/_utils/
- User: admin
- Pass: adminpw


## Testando
docker exec -it cli bash

- peer lifecycle chaincode queryinstalled
- peer chaincode invoke -o orderer.teste.com.br:7050 --tls --cafile ${PWD}/organizations/ordererOrganizations/teste.com.br/orderers/orderer.teste.com.br/msp/tlscacerts/tlsca.teste.com.br-cert.pem -C main -n basic -c '{"function":"InitLedger","Args":[]}'
- peer chaincode query -C main -n basic -c '{"Args":["GetAll"]}'

# Diretórios importantes do sistema

- Volume do CouchDB: /var/hyperledger/*