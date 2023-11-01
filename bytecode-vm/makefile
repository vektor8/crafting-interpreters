CC = gcc
CFLAGS = -Wall -I./include

SRC_DIR = src
OBJ_DIR = obj
BIN_DIR = bin

# List all source files
SRC_FILES = $(wildcard $(SRC_DIR)/*.c)

# Generate corresponding object file names
OBJ_FILES = $(patsubst $(SRC_DIR)/%.c, $(OBJ_DIR)/%.o, $(SRC_FILES))

# Specify the name of your executable
TARGET = $(BIN_DIR)/myproj

all: $(TARGET)

# Rule to compile object files
$(OBJ_DIR)/%.o: $(SRC_DIR)/%.c
	@mkdir -p $(@D)
	$(CC) $(CFLAGS) -c $< -o $@

# Rule to link object files into the executable
$(TARGET): $(OBJ_FILES)
	@mkdir -p $(@D)
	$(CC) $^ -o $@

clean:
	rm -rf $(OBJ_DIR) $(BIN_DIR)

.PHONY: all clean
