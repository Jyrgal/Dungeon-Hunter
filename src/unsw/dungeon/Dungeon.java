/**
 *
 */
package unsw.dungeon;

import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyEvent;
import unsw.dungeon.DungeonBuilder.DungeonController;
import unsw.dungeon.DungeonBuilder.DungeonControllerLoader;
import unsw.dungeon.DungeonBuilder.DungeonLoader;
import unsw.dungeon.EntityList.*;
import unsw.dungeon.EntityList.StaffList.Staff;
import unsw.dungeon.Goals.*;
import unsw.dungeon.observerEntity.entityObserver;
import unsw.dungeon.observerEntity.entitySubject;
import unsw.dungeon.observerPlayer.playerObserver;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {
    private int width, height;
    private List<Entity> entities;
    private Player player;
    private GoalComponent goals;
    private boolean completed;
    private int[][]adjacenyMatrix;
    private DungeonLoader loader;
    private DungeonController controller;
    private DungeonControllerLoader controllerLoader;
    private Staff staff;
    private ArrayList<Integer> portals;
    
    /**
     * Constructor for the dungeon
     * @param width The width of the generated dungeon
     * @param height The height of the generated dungeon
     */

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.adjacenyMatrix = new int[width * height][width * height];
        for (int i = 0; i < adjacenyMatrix.length; i++) {
            for (int j = 0; j < adjacenyMatrix[i].length; j++) {
            	adjacenyMatrix[i][j] = 0;
            }
        }
        this.portals = new ArrayList<>();
    }
    
    /**
     * Setter to set controller
     * @param controller, the controller to be set
     */
    public void setController(DungeonController controller) {
    	this.controller = controller;
    }
    
    /**
     * Setter to set controller
     * @param controller, the controller to be set
     */
    public DungeonController getController() {
    	return this.controller;
    }

	/**
	 * Get the dungeon's width
	 * @return the width of the dungeon
	 */

    public int getWidth() {
        return width;
    }

	/**
	 * Get the dungeon's height
	 * @return the height of the dungeon
	 */
    public int getHeight() {
        return height;
    }

	/**
	 * Get the player in the dungeon
	 * @return the player in the dungeon
	 */
    public Player getPlayer() {
        return player;
    }

	/**
	 * Set the player in the dungeon
	 * @param the player of the dungeon
	 */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Adds the entity to entity list
     * If entity is also an enemy, add to player observer to update enemy
     * @param entity An entity to add to the list
     */    
    public void addEntity(Entity entity) {
    	if (entity instanceof Enemies) {
	       	this.player.addPlayerObserver((Enemies)entity);
    	}
    	if (entity instanceof Portal) {

    		int portalCoordinate = entity.getX() + (entity.getY() * width);
    		System.out.println("portal found: " + portalCoordinate);
    		portals.add(portalCoordinate);
    	}
	    entities.add(entity);
    }
    
    public ArrayList<Integer> getPortals() {
    	return this.portals;
    }

    
    /**
     * Setter goal of the dungeon to be completed
     * @param goal, the goal to be set
     */
    public void setGoal(GoalComponent goal) {
    	// need to change number to enum
    	this.goals = goal;
    }
    
    /**
     * Add goal to the goal components to the goal 
     * @param goal, the goal object
     */
    public void addGoal(GoalComponent goal) {
    	this.goals.add(goal);
    }
    
    /**
     * get goal to the goal components to the goal 
     * @param goal, return the goalcomponent object
     */
    public GoalComponent getGoals() {
    	return this.goals;
    }
    
    /**
     * boolean check to see if goalExit is true, if player
     * can exit the game
     * @return boolean true or false if player can exit level
     */
    public boolean goalExit() {
    	return goals.checkExit(this);
    }
    
    /**
     * boolean check to see if exit is valid
     * can exit the game
     * @return boolean true or false if exit can be used
     */
    public boolean validExit() {
    	if (goals.checkGoalExit(this)) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Setter for if goal has been completed
     */
    public void setComplete() {
    	this.completed = true;
    	System.out.println("dungeon is complete");
    }
    
    /**
     * Getter for goal status for completion
     * @return boolean true if  finished,
     */
    public boolean getComplete() {
    	return this.completed;
    }

    /**
     * Checks if the square is an obstacle
     * @param x x-coordinate to look in
     * @param y y-coordinate to look in
     * @return true if an obstacle, false if not obstacle
     */
   
    public boolean move(int x, int y, Direction direction) {
    	ArrayList<Entity> tileEntities = findEntity(x,y);

    	//free empty tile
    	if (tileEntities.size() == 1 && tileEntities.get(0) == null) {
    		return true;
    	}

    	for (Entity entity: tileEntities) {
    		if (entity.interact.checkMove(this, entity, direction) == false){
    				return false;
    		}
    	}
    	return true;
    }

    /**
     * Invoked if the tile x,y-coordinate is an interact Entity
     * Given the entities in the x,y tile, invoke the interact function of each entity
     * update entity observer and player observer of new configuration of map
     * @param x x-coordinate to look in
     * @param y y-coordinate to look in
     */
    public void playerInteract(int x, int y, Direction direction) {
    	//enemy ai move
    	this.player.notifyPlayerObserver();
    	
    	for (Entity entity: entities) {
    		if (entity instanceof Portal && entity.getX() != x && entity.getY() != y) {
    			Direction asdf = Direction.UP;
    			((Portal)entity).interactEntity(this, asdf);
    		}
    	}
    	
    	//entities interact
    	ArrayList<Entity> tileEntities = findEntity(x,y);
        for (Entity entity: tileEntities) {
        	if (entity instanceof Enemies) {
        		if (((Enemies)entity).enemyDead()) {
        			player.removePlayerObserver((Enemies)entity);
        		}
        	}
        	System.out.println(entity + "interact");
        	//entity.notifyEntityObserver(this);
	        //directly activate enttiies that player steps on
        	entity.interactEntity(this, direction);       
        }
        
        updateSwitch();
        
        if (goals.isCompleted()) {
        	System.out.println("WOW GOALS ARE FINISHED MAAAAATE");
        }
    }
    
    /**
     * If all the sets of goal required is completed (ANR and OR case)
     * @return true if all requirements of goal completed otherwise false
     */
    public boolean dungeonComplete() {
    	if (this.completed) {
    		return true;
    	}
    	return goals.isCompleted();
    }
    
    /**
     * If boulder has been moved, setter to update switch status
     */
    public void updateSwitch() {
    	for (Entity entity: entities) {
    		if (entity instanceof Switch) {
    			ArrayList<Entity> entityList = findEntity(entity.getX(), entity.getY());
    	    	for (Entity currEntity: entityList) {
    	    		if (currEntity instanceof Boulder) {
    	    			if (((Switch)entity).isActivated() == false) {
    	    				((Switch)entity).activate(this);
    	    			}
    	    		}
    	    	}
    		}
    	}
    }

	/**
	 * Returns entity in a given co-ordinate
	 * @param x the x-ordinate of tile being check
	 * @param y the y-ordinate of tile being check
	 * @return the found entity(s) on the tile added to list
	 */
    public ArrayList<Entity> findEntity(int x, int y) {
    	ArrayList<Entity> foundEntities = new ArrayList<Entity>();
    	if (x < 0 || y < 0 || x > this.width - 1 || y > this.height - 1) {
    		return foundEntities;
    	}
    	for (Entity currEntity: entities) {
    		//System.out.println("search entity");
    		if (currEntity != null) {
	    		if (currEntity.getX() == x && currEntity.getY() == y) {
	    			foundEntities.add(currEntity);
	    		}
    		}
    	}

    	return foundEntities;
    }
    
    public ArrayList<Entity> findNearbyEntity(int x, int y) {
    	ArrayList<Entity> foundEntities = new ArrayList<Entity>();
    	for (Entity currEntity: entities) {
    		if (currEntity != null) {
	    		if (Math.abs(x - currEntity.getX()) <= 2 && Math.abs(y - currEntity.getY()) <= 2 && currEntity instanceof Enemies) {
	    			foundEntities.add(currEntity);
	    		}
    		}
    	}
    	return foundEntities;
    }

	/**
	 * checks the entity location if it is the portal
	 * @param x the x-ordinate of tile being check
	 * @param y the y-ordinate of tile being check
	 * return portal, otherwise null
	 */
    public Portal checkPortal(int x, int y) {
        for (Entity currEntity: entities) {
        	if (currEntity != null) {
	            if (currEntity.getX() == x && currEntity.getY() == y && currEntity instanceof Portal) {
	                return (Portal)currEntity;
	            }
        	}
        }
        return null;
    }

	/**
	 * Returns entity in a given co-ordinate
	 * @param x the x-ordinate of tile being check
	 * @param y the y-ordinate of tile being check
	 * @return the found entity(s) on the tile added to list
	 */
    public Entity findSpecificEntity(Entity entity) {
    	for (Entity currEntity: entities) {
    		if (currEntity instanceof Portal && entity instanceof Portal) {
    			if(((Portal)entity).getID() == ((Portal) currEntity).getID() && entity != (Portal) currEntity) {
    				return currEntity;
    			}
    		}	else if (currEntity instanceof Switch && entity instanceof Boulder) {
    			if (currEntity.getX() == entity.getX() && currEntity.getY() == entity.getY()) {
    				return currEntity;
    			}
    		}
    	}
    	return null;
    }

	/**
	 * Checks the current tile square if it is empty or has an entity on it, used for boulder & enemy 
	 * @param x the x-ordinate of square being checked and compared to
	 * @param y the y-ordinate of square being checked and compared to
	 */
    public boolean checkTile(int x, int y) {
    	if (x < 0 || y < 0 || x > this.width - 1 || y > this.height - 1) {
    		return false;
    	}
    	for (Entity currEntity: entities) {
    		if (currEntity != null) {
	    		if (currEntity.getX() == x && currEntity.getY() == y) {
	    			if (currEntity instanceof Boulder || currEntity instanceof Enemies || currEntity instanceof Wall) {
	    				return false;
	    			}	else if (currEntity instanceof Door) {
	    				if (((Door)currEntity).checkLocked() == true) {
	    					return false;
	    				}
	    			}
	    		}
    		}
    	}
    	return true;
    }
    
    public ArrayList<Key> findKey() {
    	ArrayList<Key> listKeys = new ArrayList<>();
    	for (Entity currEntity: entities) {
    		if (currEntity instanceof Key) {
    			listKeys.add((Key) currEntity);
    		}
    	}
    	return listKeys;
    }

	/**
	 * removes entity in the dungeon (Eg. picked up)
	 */
    public void removeEntity(Entity entity) {
    	for(Iterator<Entity> itr = entities.iterator(); itr.hasNext();){            
    		Entity o = itr.next();    
    		if (o != null) {
	            if(o.equals(entity)){
	                itr.remove(); // right call
	            }
    		}
        }
    	//entities.remove(entity);
    	//System.out.println("removing !!!!!==== " + entity);
    	this.controller.removeEntity(entity);
    }
    
    public ArrayList<Entity> findEntityList(String entityType) {
    	ArrayList<Entity> entityList = new ArrayList<>();
    	for (Entity currEntity: this.entities) {
    		if (currEntity != null) {
	    		String entityName = currEntity.getClass().getSimpleName().toLowerCase();
	    		if (entityName.contentEquals(entityType)) {
	    			entityList.add(currEntity);
	    		}
    		}
    	}
    	
    	return entityList;
    }
    
    public ArrayList<Entity> findEnemies() {
    	ArrayList<Entity> entityList = new ArrayList<>();
    	for (Entity currEntity: this.entities) {
    		if (currEntity instanceof Enemies) {
    			entityList.add(currEntity);
    		}
    	}
    	return entityList;
    }
    
    public void openDoor(Door door) {
    	graphOpen(door, "open");
    	entities.remove(door);
    	System.out.println("door removed");
    	this.controller.removeEntity(door);
    	this.controller.changeEntity(door);
    }
    
    public void graphOpen(Entity entity, String instruction) {
    	int entityX = entity.getX();
    	int entityY = entity.getY();
    	int source = entityX + (entityY * width);
    	
    	int entityUpX = entity.getX();
    	int entityUpY = entity.getY() - 1;
    	int Up = entityUpX + (entityUpY * width);
    	
    	int entityLeftX = entity.getX() - 1;
    	int entityLeftY = entity.getY();
    	int Left = entityLeftX + (entityLeftY * width);
    	
    	int entityRightX = entity.getX() + 1;
    	int entityRightY = entity.getY();
    	int Right = entityRightX + (entityRightY * width);
    	
    	int entityDownX = entity.getX();
    	int entityDownY = entity.getY() + 1;
    	int Down = entityDownX + (entityDownY * width);
    	
    	switch (instruction) {
    	case "open":
    		adjacenyMatrix[Left][source] = 1;
    		adjacenyMatrix[Up][source] = 1;
    		adjacenyMatrix[Right][source] = 1;
    		adjacenyMatrix[Down][source] = 1;
    		break;
    	case "close":
    		adjacenyMatrix[Left][source] = 0;
    		adjacenyMatrix[Up][source] = 0;
    		adjacenyMatrix[Right][source] = 0;
    		adjacenyMatrix[Down][source] = 0;
    		break;
    	default:
    		break;
    	}
    }
    
    
    public void makeGraph() {
    	for (int i = 0; i < this.width * this.height; i++) {
//    		Point2D currPoint = convertInteger(i);
//    		Portal portal = checkPortal((int)currPoint.getX(),(int)currPoint.getY());
//    		if (portal != null) {
//    			Portal nextPortal = (Portal)findSpecificEntity(portal);
//    			int coordinate = nextPortal.getX() + (nextPortal.getY() * width);
//    			System.out.println("from " + i + "to " + coordinate);
//    			System.out.println("from " + (int)currPoint.getX() + ":"+(int)currPoint.getY() + "to " + nextPortal.getX() + ":" + nextPortal.getY());
//    			//System.out.println("portal found from: " + (int)destinationPoint.getX() + " : "+ (int)destinationPoint.getY() + "to " + nextPortal.getX() + " : "+ nextPortal.getY());
//    			adjacenyMatrix[i][coordinate] = 1;
//    			break;
//    		}	else {
		            for (int j = 0; j < this.width * this.height; j++) {
		            	
		            	adjacenyMatrix[i][j] = matrixFill(i, j);
		        		
		            } 
    		//}
        }
    	
        //path on portal
        for (Integer portal: portals) {
        	//marking other portal as 1
        	Point2D cPortal = this.convertInteger(portal);
        	Portal currPortal = this.checkPortal((int)cPortal.getX(), (int)cPortal.getY());
        	Portal endPortal = (Portal) this.findSpecificEntity(currPortal);
        	Point2D ePortal = new Point2D.Double(endPortal.getX(), endPortal.getY());
        	Integer coordinate = this.convertPoint(ePortal);
        	adjacenyMatrix[portal][coordinate] = 1;
        	System.out.println("source portal at" + (int) currPortal.getX() + ":" + (int) currPortal.getY());
    		System.out.println("end portal at" + (int) ePortal.getX() + ":" + (int) ePortal.getY());
    		
    		
    		int entityUpX = (int) cPortal.getX();
        	int entityUpY = (int) (cPortal.getY() - 1);
        	int Up = entityUpX + (entityUpY * this.getWidth());
        	Point2D UpPoint = new Point2D.Double(entityUpX, entityUpY);
        	
        	
        	int entityLeftX = (int) (cPortal.getX() - 1);
        	int entityLeftY = (int) cPortal.getY();
        	int Left = entityLeftX + (entityLeftY * this.getWidth());
        	Point2D LeftPoint = new Point2D.Double(entityLeftX, entityLeftY);
        	
        	
        	int entityRightX = (int) (cPortal.getX() + 1);
        	int entityRightY = (int) cPortal.getY();
        	int Right = entityRightX + (entityRightY * this.getWidth());
        	Point2D RightPoint = new Point2D.Double(entityRightX, entityRightY);
        	
        	
        	int entityDownX = (int) cPortal.getX();
        	int entityDownY = (int) (cPortal.getY() + 1);
        	int Down = entityDownX + (entityDownY * this.getWidth());
        	Point2D DownPoint = new Point2D.Double(entityDownX, entityDownY);
        	
        	if (checkTile(entityUpX, entityUpY)) {
        		//adjacenyMatrix[Up][portal] = 1;
        		System.out.println(adjacenyMatrix[Up][portal] + ": before move Up" + (int) UpPoint.getX() + ":" + (int) UpPoint.getY());
        		System.out.println("opposite way" + adjacenyMatrix[portal][Up]);
        	} 
        	
        	if (checkTile(entityLeftX, entityLeftY)) {
        		//adjacenyMatrix[Left][portal] = 1;
        		System.out.println(adjacenyMatrix[Left][portal] + ": before move left"  + (int) LeftPoint.getX() + ":" + (int) LeftPoint.getY());
        		System.out.println("opposite way" + adjacenyMatrix[portal][Left]);
        	} 
        	
        	if (checkTile(entityRightX, entityRightY)) {
        		//adjacenyMatrix[Right][portal] = 1;
        		System.out.println(adjacenyMatrix[Right][portal] + ": before move right" + (int) RightPoint.getX() + ":" + (int) RightPoint.getY());
        		System.out.println("opposite way" + adjacenyMatrix[portal][Right]);
        	}	
        	
        	if (checkTile(entityDownX, entityDownY)) {
        		//adjacenyMatrix[Down][portal] = 1;
        		System.out.println(adjacenyMatrix[Down][portal] + ": before move down"  + (int) DownPoint.getX() + ":" + (int) DownPoint.getY());
        		System.out.println("opposite way" + adjacenyMatrix[portal][Down]);
        	}
        }
        
//      //paths ONTO portal (no portal on starting point), correct chcekd
//    	if (portalPoints.contains(v) && !portalPoints.contains(u) && dungeon.check(sourcePortal, portalPoint)) {
//    		//graph[u][v] = 1;
//    		//System.out.println("possible path found: "+ (int) sourcePortal.getX() + ":" + (int) sourcePortal.getY() + " to " + (int) portalPoint.getX() + ":" + (int) portalPoint.getY());
//    		if (dungeon.checkTile((int)sourcePortal.getX(), (int)sourcePortal.getY())) {
//    			graph[u][v] = 1;
//    			//System.out.println("oh found a portal! can move onto: " + graph[u][v]);
//        		System.out.println("move from" + (int) sourcePortal.getX() + ":" + (int) sourcePortal.getY() + " to " + (int) portalPoint.getX() + ":" + (int) portalPoint.getY());
//    		}
//    	}
    	
    	System.out.println(Arrays.toString(adjacenyMatrix[286]));
    }

	
	public int matrixFill(int source, int destination) {
		if (source == destination) {
			return 0;
		}
		Point2D sourcePoint = convertInteger(source);
		Point2D destinationPoint = convertInteger(destination);
		
		

//		Portal checkPortal = checkPortal(endX, endY);
//		Portal sourcePortal = checkPortal(startX, startY);
//		if (checkPortal != null && sourcePortal != null) {
//			Portal nextPortal = (Portal)findSpecificEntity(checkPortal);
//			int coordinate = nextPortal.getX() + (nextPortal.getY() * width);
//			System.out.println("FOUND END PORTALLLL");
//			adjacenyMatrix[source][coordinate] = 1;
//		}
		

		
		if(check(sourcePoint, destinationPoint)) {
			return 1;
		}
		
		return 0;
	}
	
	public int[][] getMatrix() {
		return this.adjacenyMatrix;
	}
	
	public boolean check (Point2D source, Point2D destination) {
		int startX = (int)source.getX();
		int startY = (int)source.getY();
		int endX = (int)destination.getX();
		int endY = (int)destination.getY();
		
		if (!(checkTile(endX,endY))) {
			return false;
		}
		
		
		if (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 0) {
			return true;
		}	else if (Math.abs(startX - endX) == 0 && Math.abs(startY - endY) == 1) {
			return true;
		}
		return false;
	}
	
	public Point2D convertInteger(int number) {
		int x = number % width;
		int y = number/ width;
    	Point2D coordinate = new Point2D.Double(x, y);
    	return coordinate;
    }
	
	public int convertPoint(Point2D coordinate) {
    	int x = (int)coordinate.getX();
    	int y = (int)coordinate.getY();
    	return (x + (y * width));
    }
	

	public DungeonLoader getLoader() {
		return loader;
	}

	public void setLoader(DungeonLoader loader) {
		this.loader = loader;
	}
	
	public Staff getStaff() {
		return this.staff;
	}
	
	public ArrayList<Entity> getAllEntities() {
		return (ArrayList<Entity>) this.entities;
	}
}
