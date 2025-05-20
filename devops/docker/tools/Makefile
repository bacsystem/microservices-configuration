# Variables
PROJECT_NAME=keycloak-auth
COMPOSE=docker-compose --env-file .env

up:
	$(COMPOSE) up -d


down:
	$(COMPOSE) down

mongo-up:
	$(COMPOSE) -p tools up -d mongo-db

.PHONY: up down mongo-up