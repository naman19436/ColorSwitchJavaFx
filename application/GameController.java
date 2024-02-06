package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import model.CustomButton;
import javafx.geometry.Point2D;
import view.ViewManager;

public class GameController implements Initializable{
	@FXML
	transient AnchorPane mPane;
	@FXML
	transient AnchorPane dialogBox;
	@FXML
	transient Label score;
	@FXML
	transient CustomButton home;
	@FXML
	transient CustomButton resume;
	@FXML
	transient CustomButton restart;
	@FXML
	transient CustomButton save;
	@FXML
	transient CustomButton exit;


	private final Ball ball = new Ball();
	private Point2D playerVelocity = new Point2D(0,0);
	private boolean isKeyPressed = false;
	private boolean canJump = true;
	private final ArrayList<Obstacle> obstacleArray = new ArrayList<>();
	private AnimationTimer gameLoop;
	private String currentTheme;
	private HomeController homeController;
	private int maxScore;
	private int totalStars;
	private boolean gameOver;
	private boolean continued;

	public GameController() {
		this.gameOver = false;
		this.continued = false;
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		try {
			initializeObstacles();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mPane.getChildren().add(ball);
		createGameLoop();
	}

	public void addObstacleToPane(Obstacle obstacle){
			mPane.getChildren().add(obstacle);
	}

	public void initializeObstacles() throws FileNotFoundException {
		CircularObstacle obs1 = new CircularObstacle(500, 200, ball);
		obstacleArray.add(obs1);
		mPane.getChildren().add(obs1);

		SquareObstacle obs2 = new SquareObstacle(520, -500, ball);
		obstacleArray.add(obs2);
		mPane.getChildren().add(obs2);

		SpinnerObstacle obs3 = new SpinnerObstacle(500, -1200, ball);
		obstacleArray.add(obs3);
		mPane.getChildren().add(obs3);

	}

	public void moveCamera(double d){
		for (Obstacle obj : obstacleArray) {
			obj.setTranslateY(obj.getTranslateY() + d);
			obj.setY(obj.getY() + d);
		}

	}

	public void rotateObstacles(){
		for (Obstacle obstacle : obstacleArray) {
			obstacle.getGroup().setRotate(obstacle.getGroup().getRotate() + obstacle.getSpeed());
		}
	}

	public void updateScore() {
		score.setText(Integer.toString(ball.getScore()));
	}

	public void update() throws FileNotFoundException {
		updateScore();
		rotateObstacles();
		updateObstacles();
		checkCollision();
		moveComponents();
	}

	private void moveComponents() {
		if(isKeyPressed){
			jumpBall();
		}

		else if (playerVelocity.getY() < 4) {
			playerVelocity = playerVelocity.add(0, 1);
		}

		if(ball.getTranslateY() < -300 && playerVelocity.getY() < 0){
			moveCamera((-playerVelocity.getY()));
		}
		else {
			movePlayerY((int) playerVelocity.getY());
		}
	}

	private void updateObstacles() throws FileNotFoundException {
		if (obstacleArray.get(0).getY() > 1000) {
			obstacleArray.remove(0);
			int k = new Random().nextInt(3);
			Obstacle newObs;
			if (k == 0) {
				newObs = new SquareObstacle(520, obstacleArray.get(obstacleArray.size() - 1).getY() - 700, ball);
			} else if (k == 1){
				newObs = new CircularObstacle(500, obstacleArray.get(obstacleArray.size() - 1).getY() - 700, ball);
			}
			else {
				newObs = new SpinnerObstacle(500, obstacleArray.get(obstacleArray.size() - 1).getY() - 700, ball);
			}
			obstacleArray.add(newObs);
			addObstacleToPane(newObs);
		}
	}

	private void checkCollision() {
		if(ball.getCollision()){
			this.gameOver = true;
			mPane.getChildren().forEach(c->c.setVisible(false));
			dialogBox.setVisible(true);
			home.setVisible(true);
			totalStars = totalStars + ball.getScore();
			if(totalStars < 100) {
				resume.setDisable(true);
			}
			if(maxScore < ball.getScore()){
				maxScore = ball.getScore();
			}
			save.setDisable(true);
			gameLoop.stop();
		}
	}

	public void movePlayerY(int v) {
		boolean movingDown = v > 0;
		for (int i = 0; i < Math.abs(v); i++) {
			if (!isKeyPressed && canJump) {
				if (ball.getTranslateY() == 0 || continued) {
					return;
				}
			}

			ball.setTranslateY(ball.getTranslateY() + (movingDown ? 1 : -1));
		}
	}

	private void jumpBall() {
		playerVelocity = playerVelocity.add(0, -16);
		isKeyPressed = false;
	}

	public void createGameLoop(){
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long l) {
				try {
					update();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		};
		gameLoop.start();
	}

	public void initializeListeners() {
		mPane.getScene().setOnKeyReleased(keyEvent -> {
			KeyCode keycode = keyEvent.getCode();
			if(keycode.equals(KeyCode.W)){
				ball.getBallBounceEffect().setVisible(false);
			}
		});
		mPane.getScene().setOnKeyPressed(ke ->{
			KeyCode keycode = ke.getCode();
			if(keycode.equals(KeyCode.W)){
				isKeyPressed = true;
				canJump = false;
				ball.getBallBounceEffect().setVisible(true);
			}
			if(keycode.equals(KeyCode.ESCAPE)){
				mPane.getChildren().forEach(c->c.setVisible(false));
				dialogBox.setVisible(true);
				home.setVisible(true);
				gameLoop.stop();
			}
		});
		home.setOnMouseClicked(e -> {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/homeView/home.fxml"));
			try {
				ViewManager.setCurrentFxmlLoader(fxmlLoader);
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			fxmlLoader.<HomeController>getController().initializeData(maxScore, totalStars);

		});
		restart.setOnMouseClicked(e -> {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/gameView/game.fxml"));
			try {
				ViewManager.setCurrentFxmlLoader(fxmlLoader);
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			fxmlLoader.<GameController>getController().initializeListeners();
			homeController.initializeData(maxScore, totalStars);
			fxmlLoader.<GameController>getController().initializeData(currentTheme, homeController);
		});
		resume.setOnMouseClicked(e -> {
			mPane.getChildren().forEach(c->c.setVisible(!c.isVisible()));
			if (gameOver) {
				totalStars -= (100 + ball.getScore());
				ball.setTranslateY(ball.getTranslateY() + 100);
				ball.setCollision(false);
				isKeyPressed = false;
				canJump = true;
				playerVelocity = new Point2D(0, 0);
				this.continued = true;
				save.setDisable(false);
				this.gameOver = false;
			}
			home.setVisible(true);
			gameLoop.start();
		});
		save.setOnMouseClicked(e -> {
			if (LoadSave.getLoader1() == null) {
				LoadSave.setLoader1(this);
			}
			else if (LoadSave.getLoader2() == null) {
				LoadSave.setLoader2(this);
			}
			else if (LoadSave.getLoader3() == null) {
				LoadSave.setLoader3(this);
			}
			else if (LoadSave.getLoader4() == null) {
				LoadSave.setLoader4(this);
			}

			else {
				System.out.println("Kindly delete a saved game first!");
			}

			try {
				ViewManager.setCurrentFxmlLoader(new FXMLLoader(getClass().getResource("/view/homeView/home.fxml")));
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});
		exit.setOnMouseClicked(e -> System.exit(0));

	}

	public void initializeData(String sprite, HomeController homeController) {
		this.homeController = homeController;
		maxScore = homeController.getHighestPoints();
		totalStars = homeController.getTotalPoints();
		currentTheme = sprite;
		dialogBox.setStyle("-fx-background-size: 100% 100%; -fx-background-image: url(/"+ currentTheme + ");");

	}
}