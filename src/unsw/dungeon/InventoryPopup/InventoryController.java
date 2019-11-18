package unsw.dungeon.InventoryPopup;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import unsw.dungeon.CustomImage;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityList.Key;
import unsw.dungeon.EntityList.Potion;
import unsw.dungeon.EntityList.Sword;
import unsw.dungeon.EntityList.Treasure;
import unsw.dungeon.EntityList.StaffList.Staff;

public class InventoryController implements Initializable{
	InventoryLoader inventoryloader;
	Stage inventorystage;
	
	
	public void setStage(Stage stage) {
		this.inventorystage = stage;
	}
	
	public void setLoader(InventoryLoader inventoryloader) {
		this.inventoryloader = inventoryloader;
	}
	
	@FXML
	private Button button;
	
	@FXML
	public void pressbutton(ActionEvent event) throws IOException {
		System.out.println("hi");
	}
	
	@FXML
    private TableView<Item> inventoryTable;
	
    @FXML
    public TableColumn<Item, ImageView> itemImage;

    @FXML
    public TableColumn<Item, Integer> itemID;

    @FXML
    public TableColumn<Item, Integer> itemStatus;
    
    @FXML
    private Pane InventoryPane;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemImage.setCellValueFactory(new PropertyValueFactory<Item, ImageView>("itemImage"));
		itemID.setCellValueFactory(new PropertyValueFactory<Item, Integer>("itemID"));
		itemStatus.setCellValueFactory(new PropertyValueFactory<Item, Integer>("itemStatus"));
		
		inventoryTable.setItems(getItems());
	}
	
	
	public ObservableList<Item> getItems() {
		ObservableList<Item> items = FXCollections.observableArrayList ();
		ArrayList<Entity> inventory = new ArrayList<>();
		inventory = inventoryloader.getItems();
		if (inventory.size() == 1) {
			
		}
		for (Entity item: inventory) {
			if (item instanceof Key) {
				ImageView kImage = new ImageView(new Image("/key.png"));
				items.add(new Item(kImage, ((Key)item).getID(), -1));
			}	else if (item instanceof Treasure) {
				ImageView tImage = new ImageView(new Image("/gold_pile.png"));
				items.add(new Item(tImage, 0, 0));
			}	else if (item instanceof Sword) {
				ImageView sImage = new ImageView(new Image("/greatsword_1_new.png"));
				items.add(new Item(sImage, 0, ((Sword)item).getDurability()));
			}	else if (item instanceof Potion) {
				ImageView pImage = new ImageView(new Image("/brilliant_blue_new.png"));
				items.add(new Item(pImage, 0, ((Potion)item).getDuration()));
			}	else if (item instanceof Staff) {
				System.
				out.println("wtf??????????");
				String type = ((Staff)item).getType();
				System.out.println("staff type is: ??????????" + type);
				switch (type) {
				case "bloodair":
					ImageView baImage = new ImageView(new Image("/bloodair.png"));
					items.add(new Item(baImage, 0, 1));
					break;
				case "bloodwater":
					ImageView bwImage = new ImageView(new Image("/bloodwater.png"));
					items.add(new Item(bwImage, 0, 1));
					break;
				case "bloodfire":
					ImageView bfImage = new ImageView(new Image("/bloodfire.png"));
					items.add(new Item(bfImage, 0, 1));
					break;
				case "manaair":
					ImageView maImage = new ImageView(new Image("/manaair.png"));
					items.add(new Item(maImage, 0, 1));
					break;
				case "manawater":
					ImageView mwImage = new ImageView(new Image("/manawater.png"));
					items.add(new Item(mwImage, 0, 1));
					break;
				case "manafire":
					ImageView mfImage = new ImageView(new Image("/manafire.png"));
					items.add(new Item(mfImage, 0, 1));
					break;
				default:
					break;
				}
			}
		}
		return items;
	}
}