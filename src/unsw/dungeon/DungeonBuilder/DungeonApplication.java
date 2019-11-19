package unsw.dungeon.DungeonBuilder;


import javafx.application.Application;
import javafx.stage.Stage;
import unsw.dungeon.StartScreen;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
	private Stage primaryStage;
	
    @Override
    public void start(Stage primaryStage) throws IOException {
    	this.primaryStage = primaryStage;
        primaryStage.setTitle("Dungeon");

        String s = new String("switchdoor.json");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(s);

        DungeonController controller = dungeonLoader.loadController();
        controller.setStage(primaryStage);
        controller.setDungeonApp(this);
        controller.setReset(s);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        
        dungeonLoader.setSquares(controller.getSquares());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

