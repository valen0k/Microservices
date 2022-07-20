# Microservices

###### Set of microservices for obtaining COVID-statistic in different countries

## Getting started
```shell
mvn clean install
```

### with IDE
In root (ex00) folder:
```shell
java -jar {SERVICE_NAME}/target/{*.jar}
```
Microservices order:
1. service-discovery
2. covid
3. countries
4. aggregator
5. api-gateway

### with Docker
In root folder:
```shell
docker-compose up -d
```
Stop and down containers with images removing:
```shell
docker-compose stop
docker-compose down --rmi all
```
## Usage
Eureka dashboard: `localhost:8761`
### API

```shell
curl -X GET http://localhost:8080/information_management/countries/RU
```
where **RU** is any country code

To see the visualization of JSON, follow `localhost:8080/information_management/countries/RU/overview` in browser
