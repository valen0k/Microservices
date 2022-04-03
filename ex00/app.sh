#!/bin/bash

if [[ $1 = run ]]
then
  docker build -t service-discovery-application:v1 ./service-discovery-application
  docker build -t covid-application:v1 ./covid-application
  docker build -t countries-application:v1 ./countries-application
  docker build -t aggregator-application:v1 ./aggregator-application
  docker build -t api-gateway-application:v1 ./api-gateway-application

  docker-compose -f docker-compose-local.yml build
  docker-compose -f docker-compose-local.yml up -d
elif [[ $1 = stop ]]
then
  docker-compose -f docker-compose-local.yml down -v
  docker-compose -f docker-compose-local.yml down -v --rmi all
elif [[ -z $1 ]]; then
  printf "\033[1m./app run\033[0m - \033[3mrun application\033[\n0m\033[1m./app stop\033[0m - \033[3mstop application\033[0m\n"
fi