
# Tetris Game

This repository contains a Java-based implementation of the classic Tetris game. It provides the basic functionality of the game, with a focus on object-oriented design and clear code structure.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Game](#running-the-game)
- [Game Controls](#game-controls)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Block Movement and Rotation:** Includes left/right movement, rotation, and soft drop for Tetris pieces.
- **Collision Detection:** Prevents pieces from overlapping and moving outside the game boundaries.
- **Line Clearing:** Automatically clears full lines and updates the score.
- **Graphical Interface:** Built using Java Swing, providing a simple and clean UI.
- **Score Tracking:** Keeps track of the player's score.

## Getting Started

### Prerequisites

Before running the game, ensure you have the following installed:

- [Java Development Kit (JDK) 1.8+](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)

### Installation

1. Clone the repository to your local machine using the following command:
    ```bash
    git clone https://github.com/zamweis/tetris.git
    ```

2. Navigate to the project directory:
    ```bash
    cd tetris
    ```

3. Compile the source code using the Java compiler:
    ```bash
    javac -d build src/*.java
    ```

### Running the Game

After compiling, you can run the game using the following command:

```bash
java -cp build Tetris
```

## Game Controls

- **Arrow Keys**: Move the piece left, right, or down.
- **Up Arrow**: Rotate the piece.
- **Space Bar**: Instant drop to the bottom.
- **P**: Pause/Resume the game.
- **R**: Restart the game.

## Project Structure

```
tetris/
│
├── src/                  # Source code files
│   ├── Board.java        # Manages the Tetris board and piece placement
│   ├── Piece.java        # Represents individual Tetris pieces
│   ├── Game.java         # Main game logic
│   └── Tetris.java       # Game entry point with main method
│
├── build/                # Compiled class files (generated after building)
│
├── nbproject/            # NetBeans project files (for IDE configuration)
│
└── manifest.mf           # Manifest file for running the application
```

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
