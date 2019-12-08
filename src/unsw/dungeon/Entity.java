package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.dungeon.EntityList.Player;
import unsw.dungeon.observerEntity.entityObserver;
import unsw.dungeon.observerEntity.entitySubject;
import unsw.dungeon.observerPlayer.playerObserver;
import unsw.dungeon.observerPlayer.playerSubject;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity implements entitySubject{

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    public Interact interact;
    private List<entityObserver> observers;	//composition of goalgroups & goals
    private ImageView image;
    
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.observers = new ArrayList<>();
    }
    
    /**
     * setter for imageview objects to be set in dungeonload/update
     * @param image
     */
    public void setImageView(ImageView image) {
    	this.image = image;
    }
    
    /**
     * return the imageview to be added or removed when called
     * @return image of type ImageView
     */
    public ImageView getImageView() {
    	return this.image;
    }

    /**
     * Gets the IntergerProperty x-ordinate of the entity
     * @return x, the IntergerProperty x-ordinate of the entity
     */
    public IntegerProperty x() {
        return x;
    }

    /**
     * Gets the IntergerProperty y-ordinate of the entity
     * @return y, the IntergerProperty y-ordinate of the entity
     */
    public IntegerProperty y() {
        return y;
    }

    /**
     * Gets the int y-ordinate of the entity
     * @return y, the int y-ordinate of the entity
     */
    public int getY() {
        return y().get();
    }

    /**
     * Gets the int x-ordinate of the entity
     * @return x, the int x-ordinate of the entity
     */
    public int getX() {
        return x().get();
    }

    /**
     * Sets the int x,y-ordinate of the entity given it interacts with a portal
     * @param x, the int x-ordinate of the respective portal 
     * @param y, the int y-ordinate of the respective portal
     */
    public void teleport(int x, int y) {
    	this.x().set(x);
    	this.y().set(y);
    }

    /**
     * to initiate the interact functionality of entities
     * @param dungeon, the entity within the dungeon to perform interact functionality
     */
	public void interactEntity(Dungeon dungeon, Direction direction) {
		this.interact.entityInteract(dungeon, this, direction);
	}
	
	@Override
    public void addEntityObserver(entityObserver observer) {
    	observers.add(observer);
    }

    
	/**
	 * remove observer in current dungeon
	 */
    @Override
    public void removeEntityObserver(entityObserver observer) {
    	//observers.remove(observer);
    	for(Iterator<entityObserver> itr = observers.iterator(); itr.hasNext();){            
    		entityObserver o = itr.next();            
            if(o.equals(observer)){
                itr.remove(); // right call
            }
        }
    	return;
    }
	/**
	 * Notify observers of dungeon
	 */

    @Override
    public void notifyEntityObserver(Dungeon dungeon, boolean complete) {
    	for (entityObserver observer: observers) {
    			//System.out.println("telling observer");
    			observer.updateEntityObserver(dungeon, this, complete);
    	}
    }
}
