#!/bin/bash

# Get the directory of the script
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Assume the JavaFX SDK is in the same directory as the script
JAVA_FX_SDK_DIR="$SCRIPT_DIR/javafx-sdk/lib"

# Check if JavaFX SDK directory exists
if [ -d "$JAVA_FX_SDK_DIR" ]; then
    # Set up the classpath with JavaFX dependencies
    CLASSPATH="$JAVA_FX_SDK_DIR/javafx.controls.jar:$JAVA_FX_SDK_DIR/javafx.fxml.jar"

    # Run the JAR file with the configured classpath
    java -cp "$SCRIPT_DIR/target/ChessApplication.jar:$CLASSPATH" Chess.App
else
    echo "Error: JavaFX SDK directory not found."
    exit 1
fi
