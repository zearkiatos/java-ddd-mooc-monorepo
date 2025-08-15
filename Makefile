.PONY: all build test

all: build

build:
	@./gradlew build --warning-mode all

rebuild:
	@./gradlew clean build

test:
	export SPRING_PROFILES_ACTIVE=test
	@./gradlew check --warning-mode all

run:
	SPRING_PROFILES_ACTIVE=local ./gradlew :run --args='${APP}'

set-env:
	sdk env

install-env:
	sdk env install

docker-mysql-up:
	docker-compose -f docker-compose.mysql.yaml up --build -d

docker-mysql-down:
	docker-compose -f docker-compose.mysql.yaml down

podman-mysql-up:
	podman compose -f docker-compose.mysql.yaml up --build -d

podman-mysql-down:
	podman compose -f docker-compose.mysql.yaml down

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
	make docker-mysql-up
	sleep 5
# 	docker exec -t mysql sh /docker/mysql-entrypoint.sh -d
	make run APP='$(APP)'
	sleep 5
	make docker-mysql-down

