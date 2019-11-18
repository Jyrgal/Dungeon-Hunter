package unsw.dungeon.InventoryPopup;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import unsw.dungeon.CustomImage;
import unsw.dungeon.Entity;

public class Item {
//    @FXML
//    public TableColumn<Entity, CustomImage> itemImage;
//
//    @FXML
//    public TableColumn<Entity, Integer> ID;
//
//    @FXML
//    public TableColumn<Entity, Integer> itemStatus;
	private ImageView ItemImage;
	private int ItemID;
	private int ItemStatus;
	
	public Item(ImageView itemImage, int itemID, int itemStatus) {
		this.ItemImage = itemImage;
//		this.ItemID = new SimpleIntegerProperty(itemID);
//		this.ItemStatus = new SimpleIntegerProperty(itemStatus);
		this.ItemID = itemID;
		this.ItemStatus = itemStatus;
	}
	
	public ImageView getItemImage() {
		return this.ItemImage;
	}
	
	public void setItemImage(ImageView itemImage) {
		this.ItemImage = itemImage;
	}
	
	public int getItemID() {
		return this.ItemID;
	}
	
	public void setItemID(int ID) {
		this.ItemID = ID;
	}
	
	public int getItemStatus() {
		return this.ItemStatus;
	}
	
	public void setItemStatus(int status) {
		this.ItemStatus = status;
	}
}
