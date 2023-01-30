package com.example.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pong extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_RADIUS = 10;

    private double ballX = WIDTH / 2 - BALL_RADIUS;
    private double ballY = HEIGHT / 2 - BALL_RADIUS;
    private double ballXSpeed = 6;
    private double ballYSpeed = 6;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);

        Rectangle leftPaddle = new Rectangle(0, HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        leftPaddle.setFill(Color.WHITE);
        root.getChildren().add(leftPaddle);

        Rectangle rightPaddle = new Rectangle(WIDTH - PADDLE_WIDTH, HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle.setFill(Color.WHITE);
        root.getChildren().add(rightPaddle);

        Circle ball = new Circle(BALL_RADIUS, Color.WHITE);
        ball.relocate(ballX, ballY);
        root.getChildren().add(ball);

    //movimiento rectangulos
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.UP || code == KeyCode.W) {
                rightPaddle.setY(Math.max(0, rightPaddle.getY() - 18));
            } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                rightPaddle.setY(Math.min(HEIGHT - PADDLE_HEIGHT, rightPaddle.getY() + 18));
            } else if (code == KeyCode.Q) {
                leftPaddle.setY(Math.max(0, leftPaddle.getY() - 18));
            } else if (code == KeyCode.A) {
                leftPaddle.setY(Math.min(HEIGHT - PADDLE_HEIGHT, leftPaddle.getY() + 18));
            }
        });
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            ballX += ballXSpeed;
            ballY += ballYSpeed;
            ball.relocate(ballX, ballY);
            if (ballX <= 0) {
                ballXSpeed = -ballXSpeed;
            }
            if (ballX + BALL_RADIUS * 2 >= WIDTH) {
                ballXSpeed = -ballXSpeed;
            }
            if (ballY <= 0 || ballY + BALL_RADIUS * 2 >= HEIGHT) {
                ballYSpeed = -ballYSpeed;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}