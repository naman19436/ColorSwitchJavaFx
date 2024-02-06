package application;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class ColorSwitcher extends VBox {

    private final ImageView image;

    public ColorSwitcher(Ball ball) throws FileNotFoundException {

        setPrefHeight(20);
        setPrefWidth(20);
        Circle colorSwitcher_ball = new Circle();
        colorSwitcher_ball.setCenterX(0.0f);
        colorSwitcher_ball.setCenterY(0.0f);
        colorSwitcher_ball.setRadius(1.0f);
        colorSwitcher_ball.setFill(Color.DARKSLATEGREY);
        image = new ImageView(new Image(new FileInputStream("src/view/homeView/resources/switcher.png"), 30, 30, false, false));
        getChildren().add(colorSwitcher_ball);
        getChildren().add(image);
        final ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.AQUAMARINE);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(!Shape.intersect(colorSwitcher_ball, ball.getSprite()).getBoundsInLocal().isEmpty()){

                    Color newColor = colors.get(new Random().nextInt(4));
                    ball.getSprite().setFill(newColor);
                    ball.setColor(newColor);
                    getChildren().remove(0);
                    image.setVisible(false);
                }
            }
        };
        timer.start();

    }
}