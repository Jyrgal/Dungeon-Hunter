package unsw.dungeon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import unsw.dungeon.DungeonBuilder.DungeonController;
import unsw.dungeon.DungeonBuilder.DungeonControllerLoader;


/**
 * 
 * Start Screen controller for start screen, connects to dungeon launcher and inventory
 * @author Justin Dong and James Pan
 *
 */
public class StartScreenController {
	private Stage startStage;
	
	private ArrayList<String> dungeonz = new ArrayList<>();
	@FXML
	private Button startDungeon;
	
	@FXML
    public void loadDungeon(ActionEvent event) throws IOException {
		getMaps();
		startStage.setTitle("Dungeon");
		
		Random rand = new Random();
		String map = dungeonz.get(rand.nextInt(dungeonz.size()));
		
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);
        DungeonController controller = dungeonLoader.loadController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonBuilder/DungeonView.fxml"));
        controller.setStage(startStage);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        startStage.setScene(scene);
        startStage.show();
	}
	
	public void setStage(Stage stage) {
		startStage = stage;
	}
	
	public void getMaps() {
		File maps = new File("dungeons/");
		File[] listOfFiles = maps.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		    if (listOfFiles[i].isFile()) {
		    	//add each file name to list
		    	dungeonz.add(listOfFiles[i].getName());
			    System.out.println("File " + listOfFiles[i].getName());
		    } else if (listOfFiles[i].isDirectory()) {
		  	  System.out.println("Directory " + listOfFiles[i].getName());
		    }
		}
	}
}
