package com.snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.LinkedList;
import java.util.Random;



public class SnakeGame extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int TILE_SIZE = 25;
    private static final int COLUMNS = WIDTH / TILE_SIZE;
    private static final int ROWS = WIDTH / TILE_SIZE;

    private final LinkedList<Point> snake = new LinkedList<>();
    private Point food;
    private Direction currentDirection = Direction.RIGHT;
    private boolean gameOver = false;
    private int score = 0;

    private final Random random = new Random();





    static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        resetGame();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> {
            updateLogic();
            renderGraphics(gc);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Scene scene = new Scene(new StackPane(canvas));
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> { if(currentDirection != Direction.DOWN) currentDirection = Direction.UP; }
                case DOWN -> { if(currentDirection != Direction.UP) currentDirection = Direction.DOWN; }
                case LEFT -> { if(currentDirection != Direction.RIGHT) currentDirection = Direction.LEFT; }
                case RIGHT -> { if(currentDirection != Direction.LEFT) currentDirection = Direction.RIGHT; }
            }
            if(gameOver && event.getCode() == KeyCode.SPACE) {
                resetGame();
            }
        });


        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.show();


    }

    private void resetGame() {
        snake.clear();

        snake.add(new Point(5, 10));
        snake.add(new Point(4, 10));
        snake.add(new Point(3, 10));
        currentDirection = Direction.RIGHT;
        score = 0;
        gameOver = false;
        spawnFood();
    }

    private void spawnFood() {
        while(true) {
            int x = random.nextInt(COLUMNS);
            int y = random.nextInt(ROWS);
            Point p = new Point(x, y);
            if(!snake.contains(p)) {
                food = p;
                break;
            }
        }
    }

    private void updateLogic() {
        if(gameOver) {
            return;
        }

        Point head = snake.getFirst();
        Point newHead = new Point(head.x() + currentDirection.getxOffset(), head.y() + currentDirection.getyOffset());

        if(newHead.x() < 0 || newHead.y() < 0 || newHead.x() >= COLUMNS || newHead.y() >= ROWS) {
            gameOver = true;
            return;
        }

        if(snake.contains(newHead)) {
            gameOver = true;
            return;
        }

        snake.addFirst(newHead);

        if(newHead.equals(food)) {
            score++;
            spawnFood();
        } else {
            snake.removeLast();
        }
    }

    private void renderGraphics(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.RED);
        gc.fillOval(food.x()*TILE_SIZE, food.y()*TILE_SIZE, TILE_SIZE, TILE_SIZE);

        for(int i=0; i<snake.size(); i++) {
            Point p = snake.get(i);
            gc.setFill(i == 0 ? Color.LIGHTGREEN : Color.GREEN);
            gc.fillRect(p.x()*TILE_SIZE, p.y()*TILE_SIZE, TILE_SIZE-1, TILE_SIZE-1);
        }

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 20));
        gc.fillText("Score: " + score, 10, 30);

        if(gameOver) {
            gc.setFill(Color.web("rgba(0, 0, 0, 0.5)"));
            gc.fillRect(0, 0, WIDTH, HEIGHT);
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("Arial", 40));
            gc.fillText("GAME OVER", WIDTH/2.0 - 100, HEIGHT/2.0 + 40);
        }
    }


}