
# Tetris Game

This repository contains a comprehensive Java-based implementation of the classic Tetris game, featuring various shape configurations, graphical interface components, and utility functions. The project uses object-oriented design principles to manage game logic and the user interface.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Game](#running-the-game)
- [Game Controls](#game-controls)
- [Project Structure](#project-structure)
- [Source Code Overview](#source-code-overview)
  - [Graphical User Interface (`gui`)](#graphical-user-interface-gui)
  - [Tetris Shapes (`shapes`)](#tetris-shapes-shapes)
  - [Utility Classes (`utils`)](#utility-classes-utils)
  - [Metadata (`META-INF`)](#metadata-meta-inf)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Tetris Block Movement and Rotation:** Includes left/right movement, clockwise and counterclockwise rotation (using A and D keys), and fast dropping functionality.
- **Collision Detection and Line Clearing:** Prevents overlapping of pieces, clears complete lines, and increases the score.
- **GUI Integration:** Utilizes Java Swing components for an interactive graphical user interface, with different panels for game area and next shape previews.
- **Score Tracking and Level Progression:** Automatically adjusts difficulty as the game progresses, keeping track of the player's score and level.

## Getting Started

### Prerequisites

Make sure you have the following installed on your machine:

- [Java Development Kit (JDK) 1.8 or higher](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### Installation

1. Clone this repository to your local machine:
    ```bash
    git clone https://github.com/yourusername/tetris.git
    ```

2. Navigate to the project directory:
    ```bash
    cd tetris-master
    ```

3. Compile the source code using the provided build script or compile manually:
    ```bash
    javac -d classes src/**/*.java
    ```

### Running the Game

After compiling, you can run the game using the following command:

```bash
java -cp classes gui.MainFrame
```

## Game Controls

- **Left/Right Arrow Keys**: Move the Tetris piece left or right.
- **Down Arrow Key**: Soft drop the piece (move down faster).
- **Up Arrow Key**: Rotate the piece.
- **Space Bar**: Hard drop the piece to the bottom instantly.
- **A**: Rotate the piece counterclockwise.
- **D**: Rotate the piece clockwise.
- **P**: Pause or resume the game.
- **N**: Start a new game.
- **Escape**: Exit the game.

## Project Structure

```
tetris-master/
│
├── src/                  # Source code files
│   ├── META-INF/         # Manifest file for metadata
│   ├── gui/              # Graphical user interface classes
│   ├── shapes/           # Tetris shape definitions and logic
│   └── utils/            # Utility classes for game operations
│
├── classes/              # Compiled class files (generated after building)
│
├── build.xml             # Ant build script for automating build process
├── manifest.mf           # Manifest file for running the application
├── README.md             # Project README file
└── nbproject/            # NetBeans project configuration files
```

## Source Code Overview

### Graphical User Interface (`gui`)

- **`MainFrame.java`**: The main game window, including setup and layout management.
- **`BlockMapPanel.java`**: Handles rendering of the Tetris game area.
- **`NextShapePanel.java`**: Displays a preview of the upcoming Tetris piece.
- **`ShapePanel.java`**: Manages graphical rendering of individual Tetris shapes.

### Tetris Shapes (`shapes`)

- **Individual Shape Classes**: Each Tetris piece (I, J, L, O, S, T, Z) has its own class (`ShapeI.java`, `ShapeJ.java`, etc.), handling its unique shape and rotation logic.
- **`Shape.java`**: The base class for all Tetris pieces, defining common properties and methods.
- **`Block.java`**: Represents a single unit block that makes up the Tetris shapes.
- **`BlockMap.java`**: Manages the game grid, piece placement, and collision detection.

### Utility Classes (`utils`)

- **`Point.java`**: A simple class to represent x, y coordinates.
- **`Utils.java`**: Contains common utility functions for game logic and calculations.

### Metadata (`META-INF`)

- **`MANIFEST.MF`**: Metadata and configuration information for the project.

## Contributing

Contributions are welcome! Please fork the repository, create a new branch, and submit a pull request with your changes. Make sure to include a detailed description of your updates.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.


## Note on Highscore Feature

The highscore tracking feature has been temporarily removed in the current version of the game. However, it will be reintroduced in future updates. Stay tuned for new releases!
