.PONY: all build test

all: build

build:
	@./gradlew build --warning-mode all

rebuild:
	@./gradlew clean build

test:
	@./gradlew check --warning-mode all

run:
	@./gradlew :run

set-env:
	sdk env

install-env:
	sdk env install
