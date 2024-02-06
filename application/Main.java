package application;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.ViewManager;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends Application{

	@Override
	public void start(Stage primaryStage){
		try {
			ViewManager manager = new ViewManager();
			primaryStage = manager.getMainStage();
			primaryStage.show();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/homeView/home.fxml"));
			loadHomeScreen(fxmlLoader);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadHomeScreen(FXMLLoader fxmlLoader) {
		new Timer().schedule(
				new TimerTask() {
					@Override
					public void run() {
						Platform.runLater(() -> {
							try {
								ViewManager.setCurrentFxmlLoader(fxmlLoader);
								fxmlLoader.<HomeController>getController().initializeData(0, 0);
							} catch (IOException e) {
								e.printStackTrace();
							}
						});

					}
				},
				3000
		);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
