package model;

import java.io.FileInputStream;

import javafx.beans.NamedArg;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class CustomLabel extends Label{

	private final String FONT_PATH = "src/model/resourses/kenvector_future.ttf";

	private String BACKGROUND = "model/resourses/";
	
	
	
	public CustomLabel(@NamedArg("label") String label, @NamedArg("name") String name, @NamedArg("small") String small) {
		setPrefWidth(400);
		setPrefHeight(49);
		setLayoutX(175);
		setLayoutY(25);
		setText(label);
		setWrapText(true);
		setLabelFont();
		setBackground(name, small);
		setAlignment(Pos.CENTER);
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (Exception e) {
			setFont(Font.font("Verdana", 23));
		}
	}
	
	private void setBackground(String name, String small) {
		BACKGROUND+= name+"_label.png";
		int x = 400;
		if (small.equals("yes")) {
			x = 90;
		}
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND, x, 49, false, true),
		BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		setBackground(new Background(image));
	}
}
