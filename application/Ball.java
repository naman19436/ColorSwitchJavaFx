package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ball extends VBox{

    private final Circle sprite = new Circle();
    private Color color;
    private boolean collision;
    private int score;
    private ImageView ballBounceEffect;


    public Ball() {
        try {
            ballBounceEffect = new ImageView(new Image(new FileInputStream("src/resources/ballBounce.png"), 20, 20, false, false));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setLayoutX(640);
        setLayoutY(800);
        sprite.setCenterX(500);
        sprite.setCenterY(500);
        sprite.setRadius(10.0f);
        sprite.setFill(Color.RED);
        getChildren().addAll(sprite, ballBounceEffect);
        color = Color.RED;
        collision = false;
        score = 0;
        ballBounceEffect.setVisible(false);


    }

    public Circle getSprite() {
        return sprite;
    }

    public Color getColor() {
        return color;
    }


    public void setColor(Color color) {
        this.color = color;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }


    public void setStar_collision() {
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean getCollision() {
        return collision;
    }


    public int getScore() {
        return score;
    }


    public ImageView getBallBounceEffect() {
        return ballBounceEffect;
    }
}