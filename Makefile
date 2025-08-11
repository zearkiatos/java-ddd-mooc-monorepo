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
	SPRING_PROFILES_ACTIVE=local ./gradlew :run

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
	make docker-mysql-up
	sleep 5
	docker exec -t mysql sh /docker/mysql-entrypoint.sh -d
	make run
	sleep 5
	docker-mysql-down

