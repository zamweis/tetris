
# Tetris Game

This repository contains a Java-based implementation of the classic Tetris game, complete with a graphical user interface and multiple utility classes to handle the different shapes, board state, and game logic.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Game](#running-the-game)
- [Game Controls](#game-controls)
- [Project Structure](#project-structure)
- [Source Code Overview](#source-code-overview)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Block Movement and Rotation:** Supports basic movements (left, right, down) and rotation for each Tetris piece.
- **Collision Detection:** Ensures that pieces do not overlap and remain within the game boundaries.
- **Line Clearing and Scoring:** Automatically clears full lines, updates the score, and progresses to higher levels.
- **Graphical User Interface:** Built using Java Swing, the UI includes panels for game area, score, and preview of upcoming shapes.
- **Custom Shape Classes:** Individual classes for each Tetris piece, including 'I', 'J', 'L', 'O', 'S', 'T', and 'Z' shapes.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 1.8 or higher

### Installation

1. Clone the repository to your local machine:
    ```bash
    git clone https://github.com/zamweis/tetris.git
    ```

2. Navigate to the project directory:
    ```bash
    cd tetris-master
    ```

3. Compile the project using the provided build script or manually compile the source files in the `src` directory:
    ```bash
    javac -d classes src/**/*.java
    ```

### Running the Game

Once compiled, you can run the game with the following command:

```bash
java -cp classes gui.MainFrame
```

## Game Controls

- **Arrow Keys**: Move the current piece left, right, or down.
- **Up Arrow**: Rotate the piece.
- **Space Bar**: Hard drop the piece to the bottom.
- **P**: Pause/Resume the game.
- **R**: Restart the game.

## Project Structure

```
tetris-master/
│
├── src/                  # Source code files
│   ├── META-INF/         # Manifest file for metadata
│   ├── gui/              # Graphical user interface classes
│   ├── shapes/           # Definitions for each Tetris piece shape
│   └── utils/            # Utility classes for game logic
│
├── classes/              # Compiled class files (generated after building)
│
├── build.xml             # Ant build script for automating build process
├── manifest.mf           # Manifest file for running the application
├── README.md             # Project README file
└── nbproject/            # NetBeans project configuration files
```

## Source Code Overview

- **`gui`**: Contains all the user interface components, including the main game window (`MainFrame.java`), panels for displaying the game board (`BlockMapPanel.java`), and panels for showing next shapes (`NextShapePanel.java`).
- **`shapes`**: Defines the classes for each Tetris piece, such as `ShapeI.java`, `ShapeJ.java`, etc., and handles their rotation and movement.
- **`utils`**: Contains helper classes like `Point.java` for representing coordinates and `Utils.java` for common utility functions.
- **`META-INF`**: Stores metadata and manifest files for the application.

## Contributing

Contributions are welcome! Please fork the repository and create a new branch for your feature or bug fix. Then, create a pull request with a description of your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
