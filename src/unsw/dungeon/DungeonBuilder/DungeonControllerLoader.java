package unsw.dungeon.DungeonBuilder;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityList.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import unsw.dungeon.EntityList.StaffList.Crystal;
import unsw.dungeon.EntityList.StaffList.Element;
import unsw.dungeon.EntityList.StaffList.Rod;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;
    private GridPane squares;

    //Images
    private Image playerImage;
    private Image wallImage1;
    private Image wallImage2;
    private Image wallImage3;
    private Image exitImage;
    private Image treasureImage;
    private Image doorcloseImage;
    private Image dooropenImage;
    private Image keyImage;
    private Image boulderImage;
    private Image floorswitchImage;
    private Image portalImage;
    private Image enemyImage;
    private Image swordImage;
    private Image potionImage;
    private Image bloodImage;
    private Image manaImage;
    private Image rodImage;
    private Image fireImage;
    private Image airImage;
    private Image waterImage;
    private Image wizardImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/images/nakedman.png");
        wallImage1 = new Image("/images/yellowtree.png");
        wallImage2 = new Image("/images/orangetree.png");
        wallImage3 = new Image("/images/greentree.png");
        exitImage = new Image("/images/exit.png");
        treasureImage = new Image("/images/Treasure Chest closed 32x32.png");
        doorcloseImage = new Image("/images/closedoor.png");
        dooropenImage = new Image("/images/opendoor.png");
        keyImage = new Image("/images/key.png");
        boulderImage = new Image("/images/boulder.png");
        floorswitchImage = new Image("/images/pressure_plate.png");
        portalImage = new Image("/images/portal.png");
        enemyImage = new Image("/images/enemyaxe.png");
        swordImage = new Image("/images/bigsword.png");
        potionImage = new Image("/images/brilliant_blue_new.png");
        bloodImage = new Image("/images/blood.png");
        manaImage = new Image("/images/mana.png");
        rodImage = new Image("/images/rod.png");
        fireImage = new Image("/images/fire.png");
        airImage = new Image("/images/air.png");
        waterImage = new Image("/images/water.png");
        wizardImage = new Image("/images/wizard.png");
        squares = new GridPane();
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
    	ArrayList<Image> wallImages = new ArrayList<>();
    	wallImages.add(wallImage1);
    	wallImages.add(wallImage2);
    	wallImages.add(wallImage3);
    	Random rand = new Random();
        Image randomImage = wallImages.get(rand.nextInt(wallImages.size()));
        ImageView view = new ImageView(randomImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(doorcloseImage);
        addEntity(door, view);
    }
    
    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Switch floorswitch) {
        ImageView view = new ImageView(floorswitchImage);
        addEntity(floorswitch, view);
    }
    
    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }
    
    @Override
    public void onLoad(Enemies enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
    	//System.out.println("fucked");
        ImageView view = new ImageView(swordImage);
        //System.out.println("addedimage");
        addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Potion potion) {
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
    }
    
    @Override
    public void onLoad(Crystal crystal) {
    	if (crystal.getCrystal().contentEquals("blood")) {
    		//System.out.println("putting blood");
    		ImageView view = new ImageView(bloodImage);
            addEntity(crystal, view);
    	}	else if (crystal.getCrystal().contentEquals("mana")) {
    		//System.out.println("putting mana");
    		ImageView view = new ImageView(manaImage);
            addEntity(crystal, view);
    	}
    }
    
    @Override
    public void onLoad(Rod rod) {
    	ImageView view = new ImageView(rodImage);
        addEntity(rod, view);
    }
	
    @Override
	public void onLoad(Element element) {
    	if (element.getElement().contentEquals("fire")) {
    		//System.out.println("putting fire");
    		ImageView view = new ImageView(fireImage);
            addEntity(element, view);
    	}	else if (element.getElement().contentEquals("air")) {
    		//System.out.println("putting air");
    		ImageView view = new ImageView(airImage);
            addEntity(element, view);
    	}	else if (element.getElement().contains("water")) {
    		//System.out.println("putting water");
    		ImageView view = new ImageView(waterImage);
    		addEntity(element, view);
    	}
    }
    
    @Override
    public void onLoad(Wizard wizard) {
    	ImageView view = new ImageView(wizardImage);
    	addEntity(wizard,view);
    }
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entity.setImageView(view);
        entities.add(view);
    }
    
    
    
    
    


    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

    public void setSquares(GridPane squares) {
    	this.squares = squares;
    }
    
    public void removeEntity(Entity entity) {


    	this.removeNodeByRowColumnIndex(entity.getX(), entity.getY(), entity.getImageView());
    	//squares.getChildren().remove(entityImage);
    }
    
    public void removeNodeByRowColumnIndex(final int row,final int column, ImageView image) {
    	//System.out.println("entered function");
    	ObservableList<Node> childrens = squares.getChildren();
    	if (childrens.size() == 0) {
    		//System.out.println("no nodes");
    	}
    	
    	for(Iterator<Node> itr = childrens.iterator(); itr.hasNext();){            
    		Node node = itr.next();    
    		if (node != null) {
	            if(node instanceof ImageView && squares.getRowIndex(node) == row && squares.getColumnIndex(node) == column){
	            	squares.getChildren().remove(image);
	            	break;
	            }
    		}
        }
    	
//    	for (Node node : childrens) {
//    		//System.out.println("x is: " + squares.getRowIndex(node) + "y is: " + squares.getRowIndex(node));
//    	    if(node instanceof ImageView && squares.getRowIndex(node) == row && squares.getColumnIndex(node) == column) {
//    	    	System.out.println("foundimage");
//    	        squares.getChildren().remove(image);
//    	        break;
//    	    }
//    	}
    }
}

