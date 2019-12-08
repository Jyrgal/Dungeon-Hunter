package unsw.dungeon.EntityList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.StaffList.Crystal;
import unsw.dungeon.EntityList.StaffList.Element;
import unsw.dungeon.EntityList.StaffList.Rod;
import unsw.dungeon.EntityList.StaffList.Staff;
import unsw.dungeon.interactEntity.PlayerInteract;
import unsw.dungeon.observerPlayer.*;


/**
 * The player entity
 * @author Robert -CliftonEverest
 *
 */
public class Player extends Entity implements playerSubject{

    private Dungeon dungeon;
    private List<Entity> inventory;
    private List<playerObserver> observers;
    boolean life;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        inventory = new ArrayList<>();
        observers = new ArrayList<>();
        this.life = true;
        this.interact = new PlayerInteract();
    }

    /**
     * moveUp moves the corresponding player up
     */
    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }
    
    /**
     * moveDown moves the corresponding player down
     */
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }
    /**
     * moveLeft moves the corresponding player left
     */
    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }
    /**
     * moveRight moves the corresponding player right
     */
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    /**
     * Add collectable item into player inventory
     * @param item, the item to be added to inventory
     * Activates the sword or potion
     */
    public void addItem(Entity item) {
    	inventory.add(item);
        if (item instanceof Potion) {
            this.observers.add((Potion)item);
        }
    }

    /**
     * Remove item into player inventory
     * @param item, the item to be removed from inventory
     */
    public void removeItem(Entity item) {
    	inventory.remove(item);
    }
    
    public Key searchKey () {
    	for (Entity item: inventory) {
    		if (item instanceof Key) {
    			return (Key)item;
    		}
    	}
    	return null;
    }

    /**
     * Check if key in inventory is available
     * @param door, check if key in inventory matches the door ID
     */
    public boolean findKey(Door entity) {
    	for (Entity item: inventory) {
    		if (item instanceof Key) {
    			if (((Key)item).getID() == entity.getID()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public boolean haveKey() {
    	for (Entity item: inventory) {
    		if (item instanceof Key) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Use the respective key on the door
     * @param door, the door to be unlocked with key
     * once unlocked, remove key item that was in inventory
     */
    public void useKey(Door door) {
    	for (Entity item: inventory) {
    		if (item instanceof Key) {
    			if (((Key)item).getID() == door.getID()) {
    				this.removeItem(item);
    				return;
    			}
    		}
    	}
    }

    /**
     * Check if sword is in player inventory
     * @return sword if found, otherwise return null
     */
    
    public Entity findSword() {
    	for (Entity item: inventory) {
    		if (item instanceof Sword) {
    			return item;
    		}
    	}
    	return null;
    }

    public boolean isAlive() {
    	return life;
    }
    /**
     * The death state of the player
     */
    public void die() {
    	this.life = false;
    	
    	System.out.println("OH NOOJOJO");
    }
    
    public void reborn() {
    	
    }

    /**
     * Checks the inventory of the user for a given entity
     * @param entity, the entity to be checked in inventory, used for testing
     * @return true, if given entity is in inventory, false if otherwise
     */
    public boolean checkInventory(Entity entity) {
    	for (Entity currEntity: inventory) {
    		if (currEntity == null) {
    			System.out.println("BUGBUGBUG");
    		}
    		if (currEntity.getClass() == entity.getClass()) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Checks if player has a sword
     * @return true if player has sword, false if not
     */
    public boolean checkSword() {
    	for (Entity item: inventory) {
    		if (item instanceof Sword) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean checkPotion() {
    	for (Entity item: inventory) {
    		if (item instanceof Potion) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean checkStaffPart(String entity) {
    	if (hasStaff()) {
    		return true;
    	}
    	for (Entity item: inventory) {
    		if (item.getClass().getSimpleName().toLowerCase().contentEquals(entity)) {
    			return true;
    		}
    	}

    	return false;
    }
    
    public boolean hasStaff() {
    	for (Entity item: inventory) {
    		if (item instanceof Staff) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Checks if player can chase enemy or if enemy should run due to potion
     * @return true if player possess potion, false otherwise
     */
    public boolean chaseEnemy() {
    	for (Entity item: inventory) {
    		if (item instanceof Potion) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public List<Entity> getInventory() {
    	return this.inventory;
    }


    /**
     * Add player observer in the dungeon
     */
    //enemy observing player
    @Override
    public void addPlayerObserver(playerObserver observer) {
    	observers.add(observer);
    	return;
    }

    /**
     * Remove player observer in the dungeon
     */
    @Override
    public void removePlayerObserver(playerObserver observer) {
    	//observers.remove(observer);
    	for(Iterator<playerObserver> itr = observers.iterator(); itr.hasNext();){            
            playerObserver o = itr.next();            
            if(o.equals(observer)){
                itr.remove(); // right call
            }
        }
    	return;
    }

    /**
     * Notify player observer in the dungeon
     */
    @Override
    public void notifyPlayerObserver() {
    	for (playerObserver observer: observers) {
    		observer.updateObserver(this, dungeon);
    	}
    }
    
    
    public Crystal getCrystal() {
    	for (Entity item: inventory) {
    		if (item instanceof Crystal) {
    			return (Crystal)item;
    		}
    	}
    	return null;
    }
    
    public Rod getRod() {
    	for (Entity item: inventory) {
    		if (item instanceof Rod) {
    			return (Rod)item;
    		}
    	}
    	return null;
    }
    
    public Element getElement() {
    	for (Entity item: inventory) {
    		if (item instanceof Element) {
    			return (Element)item;
    		}
    	}
    	return null;
    }
    
    public Staff getStaff() {
    	for (Entity item: inventory) {
    		if (item instanceof Staff) {
    			return (Staff)item;
    		}
    	}
    	return null;
    }
    
    public boolean checkStaffParts() {
    	boolean crystal = false;
    	boolean rod = false;
    	boolean element = false;
    	
    	for (Entity item: inventory) {
    		if (item instanceof Element) {
    			element = true;
    		}	else if (item instanceof Crystal) {
    			crystal = true;
    		}	else if (item instanceof Rod) {
    			rod = true;
    		}
    	}
    	
    	if (crystal && rod && element) {
    		return true;
    	}
    	return false;
    }
    
    public void giveStaff(Staff staff) {
    	this.inventory.add(staff);
//    	Crystal playerCrystal = null;
//    	Rod playerRod = null;
//    	Element playerElement = null;
    	//removing staff items
    	for(Iterator<Entity> itr = inventory.iterator(); itr.hasNext();){            
    		Entity currEntity = itr.next();    
    		if (currEntity != null) {
    			if (currEntity instanceof Crystal || currEntity instanceof Rod || currEntity instanceof Element) {
        			itr.remove();
        		}
    		}
        }
//    	for (Entity currEntity: inventory) {
//    		if (currEntity instanceof Crystal) {
//    			playerCrystal = (Crystal)currEntity;
//    			
//    		}	else if (currEntity instanceof Rod) {
//    			playerRod = (Rod)currEntity;
//    		}	else if (currEntity instanceof Element) {
///   			playerElement = (Element)currEntity;
//    		}
//    	}
//    	
//    	Staff playerStaff = new Staff();
//    	playerStaff = playerStaff.constructStaff(playerCrystal, playerRod, playerElement);
    }
    
}
