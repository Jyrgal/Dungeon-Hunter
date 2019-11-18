package unsw.dungeon.InventoryPopup;


import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import unsw.dungeon.CustomImage;
import unsw.dungeon.Entity;
import unsw.dungeon.StartScreen;



public class InventoryLoader extends Popup{
    private Stage primaryStage;
    private BorderPane inventorylayout;
    private Popup inventoryPopup;
    private ArrayList<Entity> inventory;
    
    public void showInventory(Stage stage, ArrayList<Entity> items) throws IOException {
    	inventoryPopup = new Popup();
    	this.inventory = items;
    	InventoryController controller = new InventoryController();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryView.fxml"));
    	loader.setController(controller);
    	controller.setStage(stage);
    	controller.setLoader(this);
    	try {
			inventoryPopup.getContent().add((Parent)loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch blockpressbutton
			e.printStackTrace();
		}
    	inventoryPopup.show(stage);
    	//inventoryPopup.centerOnScreen();
    }
    
    public ArrayList<Entity> getItems() {
    	return this.inventory;
    }
    
    public void hideInventory() {
    	inventoryPopup.hide();
    }
    
//    public void showInventoryImage(Stage stage) {
//    	 /* layout */
//    	final Stage inventoryStage = new Stage();
//    	inventoryStage.initModality(Modality.NONE);
//    	inventoryStage.initOwner(stage);
//        BorderPane layout = new BorderPane();
//
//        /* layout -> center */
//        TableView<CustomImage> tableview = new TableView<CustomImage>();
//
//        /* layout -> center -> tableview */
//
//        /* initialize two CustomImage objects and add them to the observable list */
//        ObservableList<CustomImage> imgList = FXCollections.observableArrayList();
//        CustomImage item_1 = new CustomImage(new ImageView(new Image("/human_new.png")));
//        CustomImage item_2 = new CustomImage(new ImageView(new Image("/gold_pile.png")));
//        imgList.addAll(item_1, item_2);
//
//        /* initialize and specify table column */
//        TableColumn<CustomImage, ImageView> firstColumn = new TableColumn<CustomImage, ImageView>("Images");
//        firstColumn.setCellValueFactory(new PropertyValueFactory<CustomImage, ImageView>("image"));
//        firstColumn.setPrefWidth(60);
//
//        /* add column to the tableview and set its items */
//        tableview.getColumns().add(firstColumn);
//        tableview.setItems(imgList);
//
////        /* add TableView to the layout */
////        layout.setCenter(tableview);
//        this.primaryStage = inventoryStage;
//        InventoryController inventoryController = new InventoryController();
//        inventoryController.setStage(inventoryStage);
//        ((FXMLLoader) layout).setController(inventoryController);
//        inventoryStage.setScene(new Scene((Parent)layout));
//        inventoryStage.setWidth(200);
//        inventoryStage.setHeight(200);
//        inventoryStage.show();
//    }
//    
//    public void hideInventoryImage() {
//    	primaryStage.hide();
//    }
    
//	public void showInventoryView(Stage stage) throws IOException {
//		this.primaryStage = stage;
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(StartScreen.class.getResource("InventoryPopup/InventoryView.fxml"));
//		inventorylayout = loader.load();
//		InventoryController controller = loader.getController();
//		controller.setStage(this.primaryStage);
//		Scene scene = new Scene(inventorylayout);
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}

}