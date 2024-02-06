package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.glass.events.MouseEvent;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CustomItemPicker extends HBox {
		
	private ArrayList<CustomItem> sprites;

	public CustomItem getSelectedSprite() {
		return selectedSprite;
	}

	private CustomItem selectedSprite;
	
	public class CustomItem extends VBox {

		private final ImageView circleImage;

		public ImageView getSprite() {
			return sprite;
		}

		private final ImageView sprite;
		private final String circleNotChoosen = "model/resourses/unselected.png";
		private boolean isCircleChoosen;

		public String getSpriteUrl() {
			return spriteUrl;
		}

		private final String spriteUrl;
		
		public CustomItem(String name){
			circleImage = new ImageView(circleNotChoosen);
			spriteUrl = "view/homeView/resources/" + name + ".png";
			sprite = new ImageView(spriteUrl);
			isCircleChoosen = false;
			this.setAlignment(Pos.CENTER);
			this.setSpacing(20);
			this.getChildren().addAll(circleImage, sprite);
			initializeListeners();
		}

		private void initializeListeners() {
			CustomItem thisCustomItem = this;
			setOnMouseClicked(event -> {
				selectSprite(thisCustomItem);
			});
			
			
			
			setOnMouseEntered(event -> {
					sprite.setScaleX(1.1);
					sprite.setScaleY(1.1);
			});
			
			setOnMouseExited(event -> {
				sprite.setScaleX(1);
				sprite.setScaleY(1);
			});
			
			
		}

		public void setCircleChoosen(boolean isCircleChoosen) {
			this.isCircleChoosen = isCircleChoosen;
			String circleChoosen = "model/resourses/selected.png";
			String imageToSet = this.isCircleChoosen? circleChoosen : circleNotChoosen;
			circleImage.setImage(new Image(imageToSet));
		}
		
	}
	
	public CustomItemPicker(@NamedArg("names") String names){
		this.sprites = new ArrayList<>();
		setSpacing(30);
		setLayoutX(125);
		setLayoutY(140);
		setSprites(parseNames(names));
		selectSprite(sprites.get(0));
	}
	
	private void selectSprite(CustomItem customItem) {
		if (selectedSprite == null) {
			selectedSprite = customItem;
			selectedSprite.setCircleChoosen(true);
			return;
		}
		
		selectedSprite.setCircleChoosen(false);
		selectedSprite = customItem;
		selectedSprite.setCircleChoosen(true);
	}


	private void setSprites(ArrayList<String> sprites) {
		for (String name: sprites) {
			CustomItem sprite= new CustomItem(name);
			this.sprites.add(sprite);
			getChildren().add(sprite);
		}
	}
	
	private ArrayList<String> parseNames(String names) {
		String[] strings = names.split(" ");
		return new ArrayList<>(Arrays.asList(strings));
	}
	
}
