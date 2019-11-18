package unsw.dungeon.DungeonBuilder;



import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import unsw.dungeon.CustomImage;
import unsw.dungeon.Entity;

public class glossaryItems {
//    @FXML
//    public TableColumn<Entity, CustomImage> itemImage;
//
//    @FXML
//    public TableColumn<Entity, Integer> ID;
//
//    @FXML
//    public TableColumn<Entity, Integer> itemStatus;
	private ImageView ItemImage;
	private String description;
	
	public glossaryItems(ImageView itemImage, String description) {
		this.ItemImage = itemImage;
		this.description = description;
	}
	
	public ImageView getItemImage() {
		return this.ItemImage;
	}
	
	public void setItemImage(ImageView itemImage) {
		this.ItemImage = itemImage;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}

