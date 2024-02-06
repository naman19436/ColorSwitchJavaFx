package application;

import java.net.URL;
import java.util.ResourceBundle;



import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.CustomButton;
import model.CustomItemPicker;
import model.CustomSubscene;
import view.ViewManager;

public class HomeController implements Initializable, Controller {
    @FXML
    transient CustomButton start;
    @FXML
    transient CustomButton exit;
    @FXML
    transient CustomButton help;
    @FXML
    transient CustomButton load;
    @FXML
    transient CustomButton yes;
    @FXML
    transient CustomButton no;
    @FXML
    transient CustomButton play;
    @FXML
    transient CustomSubscene startSubscene;
    @FXML
    transient CustomSubscene exitSubscene;
    @FXML
    transient CustomSubscene helpSubscene;
    @FXML
    transient CustomSubscene loadSubscene;
	@FXML
	transient CustomItemPicker picker;
    @FXML
    transient ImageView logo;
    @FXML
    transient ImageView bye;
    @FXML
    transient ImageView hi;
    @FXML
    transient VBox logoCircle1;
    @FXML
    transient VBox logoCircle2;
    @FXML
    transient VBox logoCircle3;
    @FXML
	transient AnchorPane ap;
    @FXML
	transient Label total;
	@FXML
	transient Label high;
	@FXML
	transient CustomButton load1;
	@FXML
	transient CustomButton load2;
	@FXML
	transient CustomButton load3;
	@FXML
	transient CustomButton load4;


	private int totalPoints;
	private  int highestPoints;

	transient CustomSubscene currentSubscene;
    transient CustomButton currentButton;

	public int getTotalPoints() {
		return totalPoints;
	}
	public int getHighestPoints() {
		return highestPoints;
	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rotateLogo(logoCircle1, 0, 360);
		rotateLogo(logoCircle2, 180, -360);
		rotateLogo(logoCircle3, 0, 360);
		if (LoadSave.getLoader1() == null) {
			load1.setDisable(true);
		}
		if (LoadSave.getLoader2() == null) {
			load2.setDisable(true);
		}
		if (LoadSave.getLoader3() == null) {
			load3.setDisable(true);
		}
		if (LoadSave.getLoader4() == null) {
			load4.setDisable(true);
		}
	}


	public void initializeData(int highP, int totalP) {
		total.setText(totalP+"");
		high.setText(highP+"");
		totalPoints = totalP;
		highestPoints = highP;

	}




	private void rotateLogo(VBox vBox, double start, double angle) {
		RotateTransition rotateTransition = new RotateTransition();
		rotateTransition.setFromAngle(start);
		rotateTransition.setToAngle(angle);
		rotateTransition.setByAngle(180);
		rotateTransition.setAutoReverse(true);
		rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
		rotateTransition.setDuration(Duration.seconds(5));
		rotateTransition.setNode(vBox);
		rotateTransition.play();
	}


	@FXML
	private void onHomeButtonClicked(ActionEvent e) {
		
		CustomButton sourceButton = (CustomButton) e.getSource();
		if (currentSubscene != null) {
			currentSubscene.getOutTransition().play();
			if (currentButton==sourceButton) {	
				currentSubscene = null;
				currentButton = null;
				return;
				
			}
		}
		
		
		currentButton = sourceButton;
		String sourceButtonId = sourceButton.getId();
		switch (sourceButtonId) {
			case "start":
				startSubscene.getTransition().play();
				currentSubscene = startSubscene;
				break;
			case "exit":
				exitSubscene.getTransition().play();
				currentSubscene = exitSubscene;
				break;
			case "help":
				helpSubscene.getTransition().play();
				currentSubscene = helpSubscene;
				break;
			case "load":
				loadSubscene.getTransition().play();
				currentSubscene = loadSubscene;
				break;
		}
	}


	@FXML
	private void onSubsceneButtonClicked(ActionEvent e) throws Exception {
		
		CustomButton sourceButton = (CustomButton) e.getSource(); 
		String sourceButtonId = sourceButton.getId();
		switch (sourceButtonId) {
			case "play":
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/gameView/game.fxml"));
				ViewManager.setCurrentFxmlLoader(fxmlLoader);
				fxmlLoader.<GameController>getController().initializeListeners();
				fxmlLoader.<GameController>getController().initializeData(picker.getSelectedSprite().getSpriteUrl(), this);
				break;
			case "yes":
				System.exit(0);
				break;
			case "no":
				exitSubscene.getOutTransition().play();
				currentSubscene = null;
				break;
			case "load1":
			case "load2":
			case "load3":
			case "load4":
				FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/gameView/game.fxml"));
				ViewManager.setCurrentFxmlLoader(fxml);
				System.out.println("loaded");
				break;

		}
	}
	
	@FXML
	private void logoEnter() {
		logo.setEffect(new DropShadow(10, 4, 4, Color.ORANGE));
	}
	
	@FXML
	private void logoExit() {
		logo.setEffect(null);
	}
	
	@FXML
	private void showExitAnimation(MouseEvent event) {
		CustomButton enteredButton = (CustomButton) event.getSource();
		enteredButton.setEffect(new DropShadow(10, 5, 5, Color.BEIGE));
		if (enteredButton.getText().equals("Yes")) {
			bye.setVisible(true);
		}
		else {
			hi.setVisible(true);
		}
	}
	
	@FXML
	private void hideExitAnimation(MouseEvent event) {
		CustomButton exitedButton = (CustomButton) event.getSource();
		exitedButton.setEffect(null);
		if (exitedButton.getText().equals("Yes")) {
			bye.setVisible(false);
		}
		else {
			hi.setVisible(false);
		}
	}

    
}