package unsw.dungeon.DungeonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.awt.AWTException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.StartScreen;
import unsw.dungeon.StartScreenController;
import unsw.dungeon.EntityList.Door;
import unsw.dungeon.EntityList.Key;
import unsw.dungeon.EntityList.Player;
import unsw.dungeon.EntityList.Potion;
import unsw.dungeon.EntityList.Sword;
import unsw.dungeon.EntityList.Treasure;
import unsw.dungeon.EntityList.StaffList.Staff;
import unsw.dungeon.Goals.Goal;
import unsw.dungeon.Goals.GoalComponent;
import unsw.dungeon.Goals.GoalGroup;
import unsw.dungeon.InventoryPopup.InventoryController;
import unsw.dungeon.InventoryPopup.InventoryLoader;
import unsw.dungeon.InventoryPopup.Item;
import unsw.dungeon.CustomImage;
import unsw.dungeon.Direction;
/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    
//    @FXML
//    private HBox Hbox;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private StackPane stackPane;
    
    @FXML
    private TableView<Goal> goal;
    
    @FXML
    public TableColumn <Goal, String> entity;
    
    @FXML
    public TableColumn <Goal, Integer> status;
    
    @FXML 
    private TableView <String> instruction;
    
    @FXML
    public TableColumn <String, String> keybind;
    
    @FXML
    public TableColumn <String, String> action;
    
    @FXML
    public Accordion sidebar;
    
    @FXML
    public TitledPane Goals;
    
    @FXML
    public TitledPane Keybindings;
    
    @FXML
    public TableView<glossaryItems> glossary;
    
    @FXML
    public TableColumn<glossaryItems, ImageView> items;
    
    @FXML
    public TableColumn<glossaryItems, String> descriptions;

    private List<ImageView> initialEntities;
    boolean inventoryOpen;
    private InventoryLoader inventory;

    private Player player;

    private Dungeon dungeon;
    
    private Stage dungeonStage;
    
    private VBox sideMenu;
    //Table for GoalComponent
    private TableView gc;
    
    private TableView instructions;
    
    private DungeonApplication dungeonApp;
    
	private ArrayList<String> dungeonz = new ArrayList<>();
	
    private StackPane textPane;
    
    private String currMap;

    
    public void setDungeonApp(DungeonApplication app) {
    	this.dungeonApp = app;
    }
 
    public void setReset(String s) {
    	this.currMap = s;
    }
    
    public GridPane getSquares() {
    	return this.squares;
    }
    
    public void setStage(Stage stage) {
    	this.dungeonStage = stage;
    }
    
    public Stage getStage() {
    	return this.dungeonStage;
    }


    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.inventoryOpen = false;
        inventory = new InventoryLoader();
        dungeon.setController(this);
    }

    /**
     *  General initizer to call each different component of Dungeon.
     */
    @FXML
    public void initialize() {
        initializeDungeon();
        initializeSideMenu();
        initializeGlossary();

        
        // Use to return control and focus to Dungeon Game
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	// focus on grid
                squares.requestFocus();
            }
        });
    }
    
    @FXML
    public void GoalClick() {
    	squares.setFocusTraversable(true);
    	Robot robot = new Robot();

		// Simulate a key press
		robot.keyPress(KeyCode.LEFT);
		robot.keyRelease(KeyCode.LEFT);
    }
    
    @FXML
    public void KeybindingClick() {
    	squares.setFocusTraversable(true);
    	Robot robot = new Robot();

		// Simulate a key press
		robot.keyPress(KeyCode.LEFT);
		robot.keyRelease(KeyCode.LEFT);
    }
    
    @FXML
   public void initializeGlossary() {
    	ObservableList<glossaryItems> dataItems = FXCollections.observableArrayList ();
		ArrayList<glossaryItems> inventory = new ArrayList<>();
		inventory = this.getItems();
		items.setCellValueFactory(new PropertyValueFactory<glossaryItems, ImageView>("ItemImage"));
		descriptions.setCellValueFactory(new PropertyValueFactory<glossaryItems, String>("description"));
		for (glossaryItems item: inventory) {
			dataItems.add(item);
		}
		glossary.setItems(dataItems);
    }
    
    public ArrayList<glossaryItems> getItems() {
    	ArrayList<glossaryItems> list = new ArrayList<>();
    	
    	ImageView playerImage = new ImageView (new Image("/nakedman.png"));
    	ImageView wallImage1 = new ImageView (new Image("/yellowtree.png"));
    	ImageView wallImage2 = new ImageView (new Image("/orangetree.png"));
    	ImageView wallImage3 = new ImageView (new Image("/greentree.png"));
    	ImageView exitImage = new ImageView (new Image("/exit.png"));
        ImageView treasureImage = new ImageView (new Image("/Treasure Chest closed 32x32.png"));
        ImageView doorcloseImage  =new ImageView (new Image("/closedoor.png"));
        ImageView dooropenImage = new ImageView (new Image("/opendoor.png"));
        ImageView keyImage = new ImageView (new Image("/key.png"));
        ImageView boulderImage =new ImageView (new Image("/boulder.png"));
        ImageView floorswitchImage = new ImageView (new Image("/pressure_plate.png"));
        ImageView portalImage =new ImageView (new Image("/portal.png"));
        ImageView enemyImage = new ImageView (new Image("/enemyaxe.png"));
        ImageView swordImage =new ImageView (new Image("/bigsword.png"));
        ImageView potionImage = new ImageView (new Image("/brilliant_blue_new.png"));
        ImageView bloodImage = new ImageView (new Image("/blood.png"));
        ImageView manaImage = new ImageView (new Image("/mana.png"));
        ImageView rodImage = new ImageView (new Image("/rod.png"));
        ImageView fireImage = new ImageView (new Image("/fire.png"));
        ImageView airImage =new ImageView (new Image("/air.png"));
        ImageView waterImage = new ImageView (new Image("/water.png"));
        ImageView wizardImage =new ImageView (new Image("/wizard.png"));
		ImageView baImage = new ImageView(new Image("/bloodair.png"));
		ImageView bwImage = new ImageView(new Image("/bloodwater.png"));
		ImageView bfImage = new ImageView(new Image("/bloodfire.png"));
		ImageView maImage = new ImageView(new Image("/manaair.png"));
		ImageView mwImage = new ImageView(new Image("/manawater.png"));
		ImageView mfImage = new ImageView(new Image("/manafire.png"));
		
		glossaryItems playerItem = new glossaryItems(playerImage, "this is the player model");
		glossaryItems wallItem1 = new glossaryItems(wallImage1, "this is a wall");
		glossaryItems wallItem2 = new glossaryItems(wallImage2, "this is a wall");
		glossaryItems wallItem3 = new glossaryItems(wallImage3, "this is a wall");
		glossaryItems exitItem = new glossaryItems(exitImage, "this is the exit for th elevel");
		glossaryItems treasureItem = new glossaryItems(treasureImage, "this is the treasure");
		glossaryItems doorcloseItem = new glossaryItems(doorcloseImage, "this is a locked door");
		glossaryItems dooropenItem = new glossaryItems(dooropenImage, "this is an unlocked door");
		glossaryItems keyItem = new glossaryItems(keyImage, "this is a key");
		glossaryItems boulderItem = new glossaryItems(boulderImage, "this is a boulder");
		glossaryItems switchItem = new glossaryItems(floorswitchImage, "this is a floorswitch");
		glossaryItems portalItem = new glossaryItems(portalImage, "this is a portal");
		glossaryItems enemyItem = new glossaryItems(enemyImage, "this is an enemy");
		glossaryItems swordItem = new glossaryItems(swordImage, "this is sword");
		glossaryItems potionItem = new glossaryItems(potionImage, "this is potion");
		glossaryItems bloodItem = new glossaryItems(bloodImage, "this is blood crystal");
		glossaryItems manaItem = new glossaryItems(manaImage, "this is mana crystal");
		glossaryItems rodItem = new glossaryItems(rodImage, "this is a rod");
		glossaryItems fireItem = new glossaryItems(fireImage, "this is fire element");
		glossaryItems airItem = new glossaryItems(airImage, "this is air element");
		glossaryItems waterItem = new glossaryItems(waterImage, "this is water element");
		glossaryItems wizardItem = new glossaryItems(wizardImage, "this is wizard model");
		glossaryItems baItem = new glossaryItems(baImage, "this is blood air staff (press u to kill all enemies)");
		glossaryItems bwItem = new glossaryItems(bwImage, "this is blood water staff (press u to make enemies stupid)");
		glossaryItems bfItem = new glossaryItems(bfImage, "this is blood fire staff (press u to kill all enemies)");
		glossaryItems maItem = new glossaryItems(maImage, "this is mana air staff (press u to activate 10 turn potino)");
		glossaryItems mwItem = new glossaryItems(mwImage, "this is mana water staff (press u to freeze all enemies)");
		glossaryItems mfItem = new glossaryItems(mfImage, "this is mana fire model (press u to teleport to random entity)");
		
		list.add(playerItem);
		list.add(wallItem1);
		list.add(wallItem2);
		list.add(wallItem3);
		list.add(exitItem);
		list.add(treasureItem);
		list.add(doorcloseItem);
		list.add(dooropenItem);
		list.add(keyItem);
		list.add(boulderItem);
		list.add(switchItem);
		list.add(portalItem);
		list.add(enemyItem);
		list.add(swordItem);
		list.add(potionItem);
		list.add(bloodItem);
		list.add(manaItem);
		list.add(rodItem);
		list.add(fireItem);
		list.add(airItem);
		list.add(waterItem);
		list.add(wizardItem);
		list.add(baItem);
		list.add(bwItem);
		list.add(bfItem);
		list.add(maItem);
		list.add(mwItem);
		list.add(mfItem);
		
		return list;
		
    }
    
    @FXML
    public void initializeDungeon() {
        Image ground1 = new Image("/grass1.png");
        Image ground2 = new Image("/grass2.png");
        Image ground3 = new Image("/grass3.png");
        Image ground4 = new Image("/grass4.png");
        Image ground5 = new Image("/grass5.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
            	ArrayList<Image> groundImages = new ArrayList<>();
            	groundImages.add(ground1);
            	groundImages.add(ground2);
            	groundImages.add(ground3);
            	groundImages.add(ground4);
            	groundImages.add(ground5);
            	Random rand = new Random();
                Image randomImage = groundImages.get(rand.nextInt(groundImages.size()));
                squares.add(new ImageView(randomImage), x, y);
            }
        }
        
        //need to add into goals first, goal list
        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        dungeon.makeGraph();
    }
    
    
    /**
     * Initialize the size menu to display game information
     */
    public void initializeSideMenu() {
        initializeGoals();
        initializeInstructions();
        sidebar.setFocusTraversable(false);
        Goals.setFocusTraversable(false);
        Keybindings.setFocusTraversable(false);
        glossary.setFocusTraversable(false);
       
        // System.out.println("Side Menu Active");
        // add into side
        //borderPane.getChildren().add(sideMenu);
    }
    
    /**
     * Update Goals on the SideMenu in a table format
     */
    public void initializeGoals() {
        
        // Create columns
//        TableColumn<String, String>  goalsColumn = new TableColumn<String, String>("Dungeon Goal!");
//        goalsColumn.setCellValueFactory(new PropertyValueFactory<>("typeEntity")); // Read descriptions
//
//        TableColumn<String, SimpleBooleanProperty>  completenessColumn = new TableColumn<String, SimpleBooleanProperty>("Have you Achieved?");
//        completenessColumn.setCellValueFactory(new PropertyValueFactory<>("Completed")); // Read status of each goal
//       
        
//        gc.getColumns().setAll(goalsColumn, completenessColumn); // add columns to table

        
        GoalComponent dungeonGoals = this.dungeon.getGoals();

        // fetch list of goals from dungeon
        ArrayList<GoalComponent> SingleGoals = dungeonGoals.getSingleGoals();
        ObservableList<Goal> data = FXCollections.observableArrayList(); // temporary container for data
        
        
	    for (GoalComponent goal: SingleGoals) {
		   System.out.println("========== IN IN IN ==========");
		   SimpleIntegerProperty tableGoal = ((Goal)goal).NeedCompleteProperty();
		   tableGoal.addListener((ChangeListener) (observable, oldValue, newValue) -> {		   
			   if (oldValue != newValue) {
				   data.remove((Goal)goal);
				   data.add((Goal)goal);
			   }
			
		   });
		   data.add((Goal)goal);
		}
	    
	    entity.setCellValueFactory(new PropertyValueFactory<Goal, String>("typeEntity"));
	    status.setCellValueFactory(new PropertyValueFactory<Goal, Integer>("NeedComplete"));
	    goal.setItems(data);
	    goal.setFocusTraversable(false);
	    goal.setMouseTransparent(true);
}
    
    public void initializeInstructions() {

        // Columns descriptors
        keybind.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(splitter(cellData.getValue())[0]));
        
        action.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(splitter(cellData.getValue())[1]));

        // Add the respective columns to table
        instruction.getColumns().setAll(keybind, action);

        // Add rows of instruction to table splitted by colon
        instruction.getItems().add("Up arrow key:Move up");
        instruction.getItems().add("Down arrow key:Move down");
        instruction.getItems().add("Left arrow key:Move left");
        instruction.getItems().add("Right arrow key:Move right");
        instruction.getItems().add("R key:restart level");
        instruction.getItems().add("Q key:quit level");
        instruction.getItems().add("I key:open inventory");
        instruction.getItems().add("U key:activate staff");
        
        instruction.setFocusTraversable(false);
        //instruction.setMouseTransparent(true);
        
    }
    
    
    // Instruction reading, separate the columns
    private String[] splitter(String string) {
		// TODO Auto-generated method stub
    	return string.split(":",2);
	}
//
//	// Structure format for Menu
//    public void initializeSideMenuFormat(TableView table) {
//    	
//    	// Table format structure
//        table.setFixedCellSize(30); // size of each cell
//        table.minHeightProperty().bind(table.prefHeightProperty()); // min height
//        table.maxHeightProperty().bind(table.prefHeightProperty()); // min height
//        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        table.prefWidthProperty().setValue(300);
//        table.minWidthProperty().bind(table.prefWidthProperty());
//        table.maxWidthProperty().bind(table.prefWidthProperty());
//        
//        // UI Restrictions
//        table.setFocusTraversable(false); // User cannot click list
//        table.setMouseTransparent(true); // Cannot click or alter table
//        sideMenu.getChildren().add(table); // add table to the side menu
//
//    }
    
    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException, InterruptedException {
        boolean moveCase = false;
        Direction direction = null;
        switch (event.getCode()) {
        case UP:
        	
        	direction = Direction.UP;
            moveCase = dungeon.move(player.getX(), player.getY() - 1, direction);
            if (moveCase == true) {
            	player.moveUp();
            }
            break;
        case DOWN:
        	direction = Direction.DOWN;
            moveCase = dungeon.move(player.getX(), player.getY() + 1, direction);
            if (moveCase == true) {
            	player.moveDown();
            }
            break;
        case LEFT:
        	direction = Direction.LEFT;
            moveCase = dungeon.move(player.getX() - 1, player.getY(), direction);
            if (moveCase == true) {
            	player.moveLeft();
            }
            break;
        case RIGHT:
        	direction = Direction.RIGHT;
            moveCase = dungeon.move(player.getX() + 1, player.getY(), direction);
            if (moveCase == true) {
            	player.moveRight();
            }
            break;
        case I:
            if (inventoryOpen == false) {
            	ArrayList<Entity> inventoryItems = (ArrayList<Entity>) player.getInventory();
                for (Entity currEntity: inventoryItems) {
                	System.out.println(currEntity);
                }
            	//inventory.show(dungeonStage);
            	//inventory.showInventoryView(dungeonStage);
                inventory.showInventory(dungeonStage, inventoryItems);
                //inventory.showInventoryImage(dungeonStage);
            	System.out.println("hi");
            	inventoryOpen = true;
            }	else {
            	System.out.println("hi");
            	inventory.hideInventory();
            	//inventory.hideInventoryImage();
            	//inventory.hide();
            	inventoryOpen = false;
            }
 	        break;
        case K:
        	System.out.println("Heres a cookie");
        	break;
        case Q:
        	dungeonStage.close();
        	break;
        case R:
        	dungeonReset();
        	break;
        case U:
        	Staff staff = player.getStaff();
        	if (staff != null) {
        		staff.useStaff(dungeon);
        	}
        	break;
        default:
            break;
        }
        if (moveCase) {
            dungeon.playerInteract(player.getX(), player.getY(), direction);
        }
    	if(player.isAlive() == false) playerDead();
    	if(dungeon.dungeonComplete()) dungeonFinish();

    }
    
    public void removeEntity(Entity entity) {    	
    	ObservableList<Node> childrens = squares.getChildren();
    	if (childrens.size() == 0) {
    		System.out.println("no nodes");
    	}
    	for(Node node : childrens) {
    		//System.out.println("x is: " + squares.getRowIndex(node) + "y is: " + squares.getRowIndex(node));
    	    if(node instanceof ImageView && squares.getRowIndex(node) == entity.getX() && squares.getColumnIndex(node) == entity.getY()) {
    	    	System.out.println("foundimage");
    	        squares.getChildren().remove(entity.getImageView());
    	        break;
    	    }
    	}
    }
    
    public void changeEntity(Entity entity) {    	
    	ObservableList<Node> childrens = squares.getChildren();
    	if (childrens.size() == 0) {
    		System.out.println("no nodes");
    	}
    	for(Node node : childrens) {
    		System.out.println("x is: " + squares.getRowIndex(node) + "y is: " + squares.getRowIndex(node));
    	    if(node instanceof ImageView && squares.getRowIndex(node) == entity.getX() && squares.getColumnIndex(node) == entity.getY()) {
    	    	System.out.println("foundimage");
    	    	squares.getChildren().remove(((Entity)entity).getImageView());
    	    	ImageView door = new ImageView (new Image("opendoor.png"));
    	        squares.add(door, squares.getRowIndex(node), squares.getColumnIndex(node));
    	        break;
    	    }
    	}
    }
    
    public void openDoor(Door door) {
    	ObservableList<Node> childrens = squares.getChildren();
    	if (childrens.size() == 0) {
    		System.out.println("no nodes");
    	}
    	for(Node node : childrens) {
    		System.out.println("x is: " + squares.getRowIndex(node) + "y is: " + squares.getRowIndex(node));
    	    if(node instanceof ImageView && squares.getRowIndex(node) == door.getX() && squares.getColumnIndex(node) == door.getY()) {
    	    	System.out.println("foundimage");
    	        squares.getChildren().remove(door.getImageView());
    	        ImageView openDoor = new ImageView("/open_door.png");
    	        squares.getChildren().add(openDoor);
    	        break;
    	    }
    	}
    }
    /**
     * Show user message on game status, 
     * prints message or warning to screen and stops game (unless R restarted)
     * @param string
     */
    public void showMessage(String string) {
        // Create message
        String message = "Press R to reset the Level!!!";
        Text text = new Text(string + "\n" + message); // text object
        
        textPane = new StackPane(); // message

        // Style settings
        text.setFill(Color.WHITE);
        text.setStrokeWidth(2);
        text.setStroke(Color.PURPLE);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));

        // Add text to textPane
        textPane.getChildren().add(text);

        // Align message
        textPane.setAlignment(Pos.CENTER);
        
        // Key listener to check request on stackPane focused when game stops
        textPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	
                // Check which key is pressed
                switch (event.getCode()) {
                    case R:
                        stackPane.getChildren().remove(textPane); // remove pane to allow for reset
					try {
						dungeonReset();
					} catch (IOException e) {
						System.out.println("Exception in showMessage "+ e);
						e.printStackTrace();
					} // restart game
                        break;
                    case N:
                        stackPane.getChildren().remove(textPane); // remove pane to allow for reset
					try {
						continueDungeon();
					} catch (IOException e) {
						System.out.println("Exception in showMessage "+ e);
						e.printStackTrace();
					} // restart game
                        break;
                    default:
                        
                }
            }
        });

        stackPane.getChildren().add(textPane); // add message to screen
        stackPane.setFocusTraversable(false); // disable interaction with rest of game
        textPane.requestFocus(); // force message to be focus
       
    }
    
    
    public void playerDead() {
    	showMessage("YOU ARE DEAD");
    }
    
    public void dungeonFinish() throws IOException, InterruptedException {
    	showMessage("LEVEL COMPLETE!!! ~ PRESS N FOR NEXT LEVEL");
    }
    
    public void dungeonReset() throws FileNotFoundException, IOException {
    	dungeonStage.close();
    	dungeonStage.setTitle("Dungeon");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(currMap);
        DungeonController controller = dungeonLoader.loadController();
        
        //set curr Map to resetted map again
        controller.setReset(currMap);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        controller.setStage(dungeonStage);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        

        dungeonStage.setScene(scene);
        dungeonStage.show();
    }
    
    public void continueDungeon() throws IOException {
		getMaps();
		Random rand = new Random();
		String map = dungeonz.get(rand.nextInt(dungeonz.size()));
		
    	dungeonStage.close();
    	dungeonStage.setTitle("Dungeon NEW LEVEL!!! EXTRAWUWODINARY !!!");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);
        DungeonController controller = dungeonLoader.loadController();
        controller.setReset(map);
        //set curr Map to resetted map again
        controller.setReset(currMap);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        controller.setStage(dungeonStage);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        

        dungeonStage.setScene(scene);
        dungeonStage.show();
    }
    
    /**
     * Read from dungeons folder and return arraylist of map
     * map to be used to generate next map once level complete
     */
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


