.DEFAULT_GOAL := help

APP_NAME := taskr
JAR      := target/taskr.jar
MVN      ?= ./mvnw
JAVA     ?= java

PROJECT_DIR := $(abspath .)
INSTALL_DIR ?= $(HOME)/.local/bin
LAUNCHER    := $(INSTALL_DIR)/$(APP_NAME)

.PHONY: help build compile verify clean run install uninstall

help: ## Show available commands
	@awk 'BEGIN {FS = ":.*## "} /^[a-zA-Z_-]+:.*## / {printf "  \033[36m%-10s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

build: ## Clean and build the executable JAR
	$(MVN) clean package

compile: ## Compile the project
	$(MVN) compile

verify: ## Run Maven verification
	$(MVN) verify

clean: ## Clean Maven build artifacts
	$(MVN) clean

run: build ## Build and run the application locally
	$(JAVA) -jar $(JAR)

install: build ## Install the taskr command
	@mkdir -p "$(INSTALL_DIR)"
	@printf '%s\n' \
		'#!/usr/bin/env sh' \
		'set -e' \
		'cd "$(PROJECT_DIR)"' \
		'exec "$(JAVA)" -jar "$(PROJECT_DIR)/$(JAR)" "$$@"' \
		> "$(LAUNCHER)"
	@chmod +x "$(LAUNCHER)"
	@echo "Installed $(APP_NAME) at $(LAUNCHER)"
	@echo "Make sure $(INSTALL_DIR) is in your PATH."

uninstall: ## Remove the installed taskr command
	@rm -f "$(LAUNCHER)"
	@echo "Removed $(APP_NAME) from $(LAUNCHER)"