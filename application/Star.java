package application;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Star extends VBox {

    private final ImageView image;

    public Star(Ball ball) throws FileNotFoundException {
        setPrefHeight(20);
        setPrefWidth(20);
        image = new ImageView(new Image(new FileInputStream("src/view/homeView/resources/giphy.gif"), 50, 50, false, false));
        Circle colorSwitcher_ball = new Circle();
        colorSwitcher_ball.setCenterX(0.0f);
        colorSwitcher_ball.setCenterY(0.0f);
        colorSwitcher_ball.setRadius(1.0f);
        colorSwitcher_ball.setFill(Color.DARKSLATEGREY);
        getChildren().add(image);
        getChildren().add(colorSwitcher_ball);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(!Shape.intersect(colorSwitcher_ball, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setStar_collision();
                    ball.setScore(ball.getScore() + 1);
                    eatStar();

                    getChildren().remove(1);
                    ball.setStar_collision();
                }
            }
        };
        timer.start();

    }

    private void eatStar() {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setByAngle(180);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.setDuration(Duration.seconds(0.5));
        rotateTransition.setNode(image);
        TranslateTransition outTransition =  new TranslateTransition();
        outTransition.setToX(+400);
        outTransition.setToY(-500);
        outTransition.setDuration(Duration.seconds(0.5));
        outTransition.setNode(image);
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.3);
        fadeTransition.setDuration(Duration.seconds(0.5));
        fadeTransition.setNode(image);
        rotateTransition.play();
        outTransition.play();
        fadeTransition.play();
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            image.setVisible(false);
                        });

                    }
                },
                500
        );
    }

}