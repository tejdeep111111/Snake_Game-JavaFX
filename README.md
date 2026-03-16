# 🐍 Snake Game

A classic Snake game built with **Java** and **JavaFX**, featuring smooth animations, collision detection, and a clean retro aesthetic.

---

## 📸 Preview

```
┌─────────────────────────────┐
│  Score: 5                   │
│                             │
│        🟩🟩🟩              │
│              🟩             │
│         🔴                  │
│                             │
└─────────────────────────────┘
```

---

## 🚀 Features

- Smooth 130ms game loop using JavaFX `Timeline`
- Collision detection — walls and self
- Dynamic food spawning (never spawns on the snake)
- Score tracking
- Restart on `SPACE` after game over

---

## 🛠️ Tech Stack

| Layer      | Technology        |
|------------|-------------------|
| Language   | Java 17+          |
| UI / Graphics | JavaFX         |
| Build Tool | Maven / Gradle    |

---

## 📁 Project Structure

```
snake-game/
├── src/
│   └── main/
│       └── java/
│           └── com/snake/
│               ├── SnakeGame.java      # Main application & game loop
│               ├── Direction.java      # Enum for movement directions
│               └── Point.java          # Record for grid coordinates
├── pom.xml                             # Maven build file
└── README.md
```

---

## ⚙️ Prerequisites

- Java **17** or higher
- JavaFX SDK **17+**
- Maven or Gradle

---

## 🔧 Setup & Run

### Using Maven

```bash
# Clone the repository
git clone https://github.com/your-username/snake-game.git
cd snake-game

# Run the application
mvn javafx:run
```

### Using Gradle

```bash
./gradlew run
```

### Manual Compilation

```bash
javac --module-path /path/to/javafx-sdk/lib \
      --add-modules javafx.controls \
      -d out src/main/java/com/snake/*.java

java --module-path /path/to/javafx-sdk/lib \
     --add-modules javafx.controls \
     -cp out com.snake.SnakeGame
```

---

## 🎮 Controls

| Key          | Action                    |
|--------------|---------------------------|
| `↑` Arrow    | Move Up                   |
| `↓` Arrow    | Move Down                 |
| `←` Arrow    | Move Left                 |
| `→` Arrow    | Move Right                |
| `SPACE`      | Restart (after Game Over) |

> **Note:** You cannot reverse direction directly (e.g., going LEFT while moving RIGHT).

---

## 🧠 How It Works

The game runs on a **130ms JavaFX Timeline loop**. Each tick:

1. Calculates the new head position based on current direction
2. Checks for **wall** or **self-collision** → Game Over
3. If head reaches food → score increases, snake grows, new food spawns
4. Otherwise → tail is removed (snake moves forward)
5. Canvas is redrawn every tick

See [Program Flow](docs/FLOW.md) for a detailed breakdown. *(optional)*

---

## 📐 Game Constants

| Constant    | Value  | Description              |
|-------------|--------|--------------------------|
| `WIDTH`     | 600px  | Canvas width             |
| `HEIGHT`    | 600px  | Canvas height            |
| `TILE_SIZE` | 25px   | Size of each grid cell   |
| `COLUMNS`   | 24     | Grid columns             |
| `ROWS`      | 24     | Grid rows                |
| Game Speed  | 130ms  | Tick interval            |

---

## 🐛 Known Issues / Limitations

- No high score persistence between sessions
- Fixed game speed (no difficulty scaling)
- Single player only

---

## 🔮 Possible Improvements

- [ ] Add difficulty levels (speed scaling)
- [ ] Persist high scores to a file
- [ ] Add sound effects
- [ ] Add a start screen / main menu
- [ ] Mobile-friendly version (JavaFX touch events)

---


## 🙌 Acknowledgements

- Inspired by the classic **Nokia Snake** game
- Built with [JavaFX](https://openjfx.io/)
