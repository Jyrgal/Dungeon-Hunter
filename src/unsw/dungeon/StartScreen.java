package unsw.dungeon;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import unsw.dungeon.DungeonBuilder.DungeonApplication;
import unsw.dungeon.DungeonBuilder.DungeonController;
import unsw.dungeon.DungeonBuilder.DungeonControllerLoader;


/**
 * 
 * StartScreen menu for users to start the game with random generator map and continuous levels
 * @author Justin Dong and James Pan
 *
 */
public class StartScreen extends Application{
    private Stage primaryStage;
    private BorderPane mainLayout;
	
    
    public static void main (String[] args) {
    	launch(args);
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
    	this.primaryStage.setTitle("Dungeon game");
    	
    	showStartView();
	}
	
	public void showStartView() throws IOException {
		   FXMLLoader loader = new FXMLLoader();
	       loader.setLocation(StartScreen.class.getResource("StartScreenLayout.fxml"));
	       mainLayout = loader.load();
	       StartScreenController controller = loader.getController();
	       controller.setStage(this.primaryStage);
	       Scene scene = new Scene(mainLayout);
	       BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
	       
	       Image image = new Image("/images/menu.png");
	       Background background = new Background(new BackgroundImage(image,
	               BackgroundRepeat.NO_REPEAT,
	               BackgroundRepeat.NO_REPEAT,
	               BackgroundPosition.CENTER,
	               bSize));
	       mainLayout.setBackground(background);
	       primaryStage.setScene(scene);
	       primaryStage.show();
	}
	
	public void showDungeonView(Stage primaryStage) throws IOException {
//		primaryStage.setTitle("Dungeon");
//
//        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");
//
//        DungeonController controller = dungeonLoader.loadController();
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
//        loader.setController(controller);
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        root.requestFocus();
//        primaryStage.setScene(scene);
//        primaryStage.show();
		//System.out.println("hi");
		//javafx.application.Application.launch(DungeonApplication.class);
	}
	
	public Stage getStage() {
		return this.primaryStage;
	}
}
