package model;


import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class CustomSubscene extends SubScene{
	
	private TranslateTransition transition;
	private TranslateTransition outTransition;
	
	
	
	public CustomSubscene() {
		super(new AnchorPane(), 750, 500);
		prefWidth(750);
		prefHeight(500);
		setTransition();
		setOutTransition();
	}
	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}

	

	private void setTransition() {
		transition = new TranslateTransition();
		transition.setToX(-900);
		transition.setDuration(Duration.seconds(0.5));
		transition.setNode(this);
	}
	
	private void setOutTransition() {
		outTransition = new TranslateTransition();
		outTransition.setToX(+900);
		outTransition.setDuration(Duration.seconds(0.5));
		outTransition.setNode(this);
	}
	
	public TranslateTransition getTransition() {
		return transition;
	}	
	public TranslateTransition getOutTransition() {
		return outTransition;
	}	
		
}
