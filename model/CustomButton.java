package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.NamedArg;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomButton extends Button{

	private  String BUTTON_PRESSED_STYLE = "-fx-background-size: 100% 100%;-fx-background-color: transparent;-fx-background-image: url('/model/resourses/";
	private  String BUTTON_FREE_STYLE = "-fx-background-size: 100% 100%; -fx-background-color: transparent;-fx-background-image: url('/model/resourses/";
	private final DropShadow dropShadow = new DropShadow(10, 5, 5, Color.BEIGE);
	
	
	public CustomButton(@NamedArg("name") String name) throws FileNotFoundException {
		if (name==null) {
			name="default";
		}
		BUTTON_PRESSED_STYLE+=name+"_button_pressed.png');";
		BUTTON_FREE_STYLE+=name+"_button.png');";
		setButtonFont();
		setPrefHeight(49);
		setPrefWidth(190);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
		Image cursorImage = new Image(new FileInputStream("src/model/resourses/cursor.png"));
		setCursor(new ImageCursor(cursorImage, 0, 0));
	}
	
	
	private void setButtonFont() {
		try {
			String FONT_PATH = "src/model/resourses/kenvector_future.ttf";
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (Exception e) {
			setFont(Font.font("Verdana", 23));
		}
	}
	
	
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
	}
	
	private void initializeButtonListeners() {
		
		
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
				
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
				
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(dropShadow);
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}
}
