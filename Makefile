.PONY: all build test

all: build

build:
	@./gradlew assemble --warning-mode all

test:
	@./gradlew check --warning-mode all

run:
	@./gradlew :run

set-env:
	sdk env

install-env:
	sdk env install
