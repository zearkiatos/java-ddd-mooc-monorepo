.PONY: all build test

all: build

build:
	@./gradlew build --warning-mode all

rebuild:
	@./gradlew clean build

test:
	make docker-test-up
	sleep 15
	export SPRING_PROFILES_ACTIVE=test
	@./gradlew check --warning-mode all
	make docker-test-down

run:
	SPRING_PROFILES_ACTIVE=local ./gradlew :run --args='${APP}'

set-env:
	sdk env

install-env:
	sdk env install

docker-dependencies-up:
	docker compose -f docker-compose.yaml up --build -d

docker-dependencies-down:
	docker compose -f docker-compose.yaml down

podman-dependencies-up:
	podman compose -f docker-compose.yaml up --build -d

podman-dependencies-down:
	podman compose -f docker-compose.yaml down

docker-test-up:
	docker compose -f docker-compose.test.yaml up --build -d

docker-test-down:
	docker compose -f docker-compose.test.yaml down

podman-test-up:
	podman compose -f docker-compose.test.yaml up --build -d

podman-test-down:
	podman compose -f docker-compose.test.yaml down

run-local:
ifndef APP
	@echo "❌ Error: APP argument is required"
	@echo "📋 Usage: make run-local APP=your-app-name"
	@echo "📝 Example: make run-local APP="mooc_backend api"
	@exit 1
endif
ifeq ($(strip $(APP)),)
	@echo "❌ Error: APP argument cannot be empty"
	@echo "📋 Usage: make run-local APP=your-app-name"
	@exit 1
endif
	@echo "🚀 Starting application with APP=$(APP)"
	make docker-dependencies-up
	sleep 15
# 	docker exec -t mysql sh /docker/mysql-entrypoint.sh -d
	make run APP='$(APP)'
	sleep 15
	make docker-dependencies-down
