package application;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.io.FileNotFoundException;

abstract public class Obstacle extends VBox {

    private double y;
    private double speed;

    private final ColorSwitcher colorSwitcher;
    private final Star star;

    private AnimationTimer timer;

    private StackPane stackPane;
    private Group group;

    public Obstacle(double x, double y, Ball ball) throws FileNotFoundException {
        this.y = y;
        this.speed = 1;

        setLayoutX(x);
        setLayoutY(y);

        star = new Star(ball);
        colorSwitcher = new ColorSwitcher(ball);

        getChildren().add(colorSwitcher);

        setSpacing(100);
    }

    public void addObstacleToPane() {
        setStackPane(new StackPane(getGroup(), getStar()));
        getStackPane().setAlignment(Pos.CENTER);
        getStar().setAlignment(Pos.CENTER);
        getColorSwitcher().setAlignment(Pos.CENTER);
        getChildren().add(getStackPane());
    }

    public Group getGroup() {
        return group;
    }

    public Star getStar() {
        return star;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ColorSwitcher getColorSwitcher() {
        return colorSwitcher;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getCenter_x() {
        return 0;
    }

    public double getCenter_y() {
        return 0;
    }

    public double getRadius() {
        return 140.0f;
    }

    public AnimationTimer getTimer() {
        return timer;
    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

}


class CircularObstacle extends Obstacle{

    private final Arc arc1;
    private final Arc arc2;
    private final Arc arc3;
    private final Arc arc4;

    public CircularObstacle(double x, double y, Ball ball) throws FileNotFoundException {
        super(x,y,ball);
        arc1 = new Arc();
        arc2 = new Arc();
        arc3 = new Arc();
        arc4 = new Arc();
        generateEdges();
        initializeTimer(ball);
        getTimer().start();
        assembleShape();
    }

    private void assembleShape() {
        setGroup(new Group(arc1, arc2, arc3, arc4));
        this.addObstacleToPane();

    }

    private void initializeTimer(Ball ball) {
        setTimer(new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(arc1.getStroke() != ball.getColor() && !Shape.intersect(arc1, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);
                }

                if(arc2.getStroke() != ball.getColor() && !Shape.intersect(arc2, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);

                }
                if(arc3.getStroke() != ball.getColor() && !Shape.intersect(arc3, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);

                }
                if(arc4.getStroke() != ball.getColor() && !Shape.intersect(arc4, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);

                }
            }
        });
    }

    private void generateEdges() {
        arc1.setCenterX(getCenter_x());
        arc1.setCenterY(getCenter_y());
        arc1.setRadiusX(getRadius());
        arc1.setRadiusY(getRadius());
        arc1.setStartAngle(45.0f);
        arc1.setLength(90.0f);
        arc1.setType(ArcType.OPEN);
        arc1.setFill(Color.DARKSLATEGREY);
        arc1.setStrokeWidth(15);
        arc1.setStroke(Color.AQUAMARINE);

        arc2.setCenterX(getCenter_x());
        arc2.setCenterY(getCenter_y());
        arc2.setRadiusX(getRadius());
        arc2.setRadiusY(getRadius());
        arc2.setStartAngle(135.0f);
        arc2.setLength(90.0f);
        arc2.setType(ArcType.OPEN);
        arc2.setFill(Color.DARKSLATEGREY);
        arc2.setStrokeWidth(15);
        arc2.setStroke(Color.YELLOW);

        arc3.setCenterX(getCenter_x());
        arc3.setCenterY(getCenter_y());
        arc3.setRadiusX(getRadius());
        arc3.setRadiusY(getRadius());
        arc3.setStartAngle(225.0f);
        arc3.setLength(90.0f);
        arc3.setType(ArcType.OPEN);
        arc3.setFill(Color.DARKSLATEGREY);
        arc3.setStrokeWidth(15);
        arc3.setStroke(Color.RED);

        arc4.setCenterX(getCenter_x());
        arc4.setCenterY(getCenter_y());
        arc4.setRadiusX(getRadius());
        arc4.setRadiusY(getRadius());
        arc4.setStartAngle(315.0f);
        arc4.setLength(90.0f);
        arc4.setType(ArcType.OPEN);
        arc4.setFill(Color.DARKSLATEGREY);
        arc4.setStrokeWidth(15);
        arc4.setStroke(Color.BLUE);
    }

}

class SquareObstacle extends Obstacle{
    private final Line l1;
    private final Line l2;
    private final Line l3;
    private final Line l4;
    public SquareObstacle(double x, double y, Ball ball) throws FileNotFoundException {
        super(x,y, ball);
        setSpeed(0.8);
        l1 = new Line();
        l2 = new Line();
        l3 = new Line();
        l4 = new Line();
        generateEdges();
        initializeTimer(ball);
        getTimer().start();
        assembleShape();

    }

    private void assembleShape() {
        setGroup(new Group(l1,l2,l3,l4));
        this.addObstacleToPane();
    }

    private void initializeTimer(Ball ball) {
        setTimer(new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(l1.getStroke() != ball.getColor() && !Shape.intersect(l1, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);
                }
                if(l2.getStroke() != ball.getColor() && !Shape.intersect(l2, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);
                }
                if(l3.getStroke() != ball.getColor() && !Shape.intersect(l3, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);
                }
                if(l4.getStroke() != ball.getColor() && !Shape.intersect(l4, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);
                }
            }
        });
    }

    private void generateEdges() {
        double size = 240.0f;
        l1.setStartX(0.0f);
        l1.setStartY(0.0f);
        l1.setEndY(size);
        l1.setEndX(0.0f);
        l1.setStrokeWidth(15);
        l1.setStroke(Color.AQUAMARINE);

        l2.setStartX(0.0f);
        l2.setStartY(0.0f);
        l2.setEndX(size);
        l2.setEndY(0.0f);
        l2.setStrokeWidth(15);
        l2.setStroke(Color.YELLOW);

        l3.setStartX(0.0f);
        l3.setStartY(size);
        l3.setEndY(size);
        l3.setEndX(size);
        l3.setStrokeWidth(15);
        l3.setStroke(Color.RED);

        l4.setStartX(size);
        l4.setStartY(0.0f);
        l4.setEndX(size);
        l4.setEndY(size);
        l4.setStrokeWidth(15);
        l4.setStroke(Color.BLUE);
    }
}



class SpinnerObstacle extends Obstacle{
    private final Line l1;
    private final Line l2;
    private final Line l3;
    private final Line l4;
    public SpinnerObstacle(double x, double y, Ball ball) throws FileNotFoundException {
        super(x,y, ball);
        setSpeed(0.5);
        l1 = new Line();
        l2 = new Line();
        l3 = new Line();
        l4 = new Line();
        generateEdges();
        initializeTimer(ball);
        getTimer().start();
        assembleShape();

    }

    private void assembleShape() {
        setGroup(new Group(l1,l2,l3,l4));
        this.addObstacleToPane();
    }

    private void initializeTimer(Ball ball) {
        setTimer(new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(l1.getStroke() != ball.getColor() && !Shape.intersect(l1, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);
                }
                if(l2.getStroke() != ball.getColor() && !Shape.intersect(l2, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);
                }
                if(l3.getStroke() != ball.getColor() && !Shape.intersect(l3, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);
                }
                if(l4.getStroke() != ball.getColor() && !Shape.intersect(l4, ball.getSprite()).getBoundsInLocal().isEmpty()){
                    ball.setCollision(true);
                }
            }
        });
    }

    private void generateEdges() {
        double size = 280.0f;
        l1.setStartX(0.0f);
        l1.setStartY(size/4);
        l1.setEndY(size/2);
        l1.setEndX(0.0f);
        l1.setStrokeWidth(15);
        l1.setStroke(Color.AQUAMARINE);

        l2.setStartX(size/4);
        l2.setStartY(0.0f);
        l2.setEndX(size/2);
        l2.setEndY(0.0f);
        l2.setStrokeWidth(15);
        l2.setStroke(Color.YELLOW);

        l3.setStartX(-size/4);
        l3.setStartY(0.0f);
        l3.setEndX(-size/2);
        l3.setEndY(0.0f);
        l3.setStrokeWidth(15);
        l3.setStroke(Color.RED);

        l4.setStartX(0.0f);
        l4.setStartY(-size/4);
        l4.setEndX(0.0f);
        l4.setEndY(-size/2);
        l4.setStrokeWidth(15);
        l4.setStroke(Color.BLUE);
    }
}
