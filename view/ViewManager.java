package view;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class ViewManager {
	private static final int HEIGHT =  1000;
	private static final int WIDTH =  1240;

	private static AnchorPane mainPane;
	private static Scene mainScene;
	private static Stage mainStage;


	public ViewManager() throws IOException {
		mainStage = new Stage();
		mainStage.getIcons().add(new Image(new FileInputStream("src/resources/icon.png")));
		mainStage.setTitle("Color Switch");
		setCurrentFxmlLoader(new FXMLLoader(getClass().getResource("/view/welcomeView/welcome.fxml")));
	}


	public static void setCurrentFxmlLoader(FXMLLoader fxmlLoader) throws IOException {
		setMainPane(fxmlLoader.load());
	}

	private static void setMainPane(AnchorPane mPane) {
		mainPane = mPane;
		setMainScene();
	}

	private static void setMainScene() {
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		setMainStage();
	}

	private static void setMainStage() {
		mainStage.setScene(mainScene);
	}

	public Stage getMainStage() {
		return mainStage;
	}

}
