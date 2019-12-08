package unsw.dungeon.DungeonBuilder;

import java.io.FileNotFoundException;
import unsw.dungeon.Goals.*;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityList.*;
import unsw.dungeon.EntityList.StaffList.Crystal;
import unsw.dungeon.EntityList.StaffList.Element;
import unsw.dungeon.EntityList.StaffList.Rod;
import unsw.dungeon.EntityList.StaffList.Staff;
import unsw.dungeon.Goals.GoalComponent;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    
    private int goalID = 0;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        String goalType = jsonGoals.getString("goal");
        GoalComponent goal = loadGoals(jsonGoals, dungeon, goalType);
        dungeon.setGoal(goal);
        return dungeon;
    }
    
    public GoalComponent loadGoals(JSONObject json, Dungeon dungeon, String goalType) {
    	//if it is an and/or
    	String goal = json.getString("goal");
    	if (goal.contentEquals("AND")) {
    		GoalGroup goalGroup = new GoalGroup(2);
    		JSONArray subgoals = json.getJSONArray("subgoals");
    		
    		for (int i = 0; i < subgoals.length(); i++) {
    			goalGroup.add(loadGoals(subgoals.getJSONObject(i), dungeon, "AND"));	
    		}
    		goalID++;
    		Player player = dungeon.getPlayer();
    		player.addPlayerObserver(goalGroup);
    		return goalGroup;
    	}	else if (goal.contentEquals("OR")) {
    		GoalGroup goalGroup = new GoalGroup(1);
    		JSONArray subgoals = json.getJSONArray("subgoals");
    		for (int i = 0; i < subgoals.length(); i++) {
    			goalGroup.add(loadGoals(subgoals.getJSONObject(i), dungeon, "OR"));
    		}
    		goalID++;
    		Player player = dungeon.getPlayer();
    		player.addPlayerObserver(goalGroup);
    		return goalGroup;
    	}
    	
    	else {
    		
    		if (goal.contentEquals("boulders")) {
    			ArrayList<Entity> switchList = dungeon.findEntityList("switch");
    			Goal switchGoal = new Goal("switch", switchList.size(), goalType, goalID);
    			for (Entity currEntity: switchList) {
        			currEntity.addEntityObserver(switchGoal);
        		}
    			return switchGoal;
    		}	else {
	    		ArrayList<Entity> entityList = dungeon.findEntityList(goal);
	    		Goal finalGoal = new Goal(goal, entityList.size(), goalType, goalID);
	    		
	    		
	    		for (Entity currEntity: entityList) {
	    			currEntity.addEntityObserver(finalGoal);
	    		}
	    		return finalGoal;
    		}
    	}
    }
    
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        //System.out.println("hi" + x + y);
        
        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(x,y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x,y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x,y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch floorswitch = new Switch(x,y,dungeon);
        	onLoad(floorswitch);
        	entity = floorswitch;
        	break;
        case "enemy":
        	Enemies enemy = new Enemies(x,y);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "sword":
        	Sword sword = new Sword(x,y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "potion":
        	Potion potion = new Potion(x,y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "door":
        	int doorid = json.getInt("id");
    		Door door = new Door(x,y,doorid);
         	onLoad(door);
         	entity = door;
         	break;
        case "key":
        	int keyid = json.getInt("id");
         	Key key = new Key(x,y,keyid);
         	onLoad(key);
         	entity = key;
         	break;
        case "portal":
        	int portalid = json.getInt("id");
         	Portal portal = new Portal(x,y,portalid);
         	onLoad(portal);
         	entity = portal;
         	break;
        case "bloodcrystal":
         	Crystal bloodcrystal = new Crystal(x,y);
         	bloodcrystal.setCrystal("blood");
         	onLoad(bloodcrystal);
         	entity = bloodcrystal;
         	break;
        case "manacrystal":
        	Crystal manacrystal = new Crystal(x,y);
        	manacrystal.setCrystal("mana");
         	onLoad(manacrystal);
         	entity = manacrystal;
         	break;
        case "rod":
        	Rod rod = new Rod(x,y);
        	rod.setRod("nimbus");
         	onLoad(rod);
         	entity = rod;
         	break;
        case "fireelement":
        	Element fireelement = new Element(x,y);
        	fireelement.setElement("fire");
         	onLoad(fireelement);
         	entity = fireelement;
         	break;
        case "airelement":
        	Element airelement = new Element(x,y);
        	airelement.setElement("air");
         	onLoad(airelement);
         	entity = airelement;
         	break;
        case "waterelement":
        	Element waterlement = new Element(x,y);
        	waterlement.setElement("water");
         	onLoad(waterlement);
         	entity = waterlement;
         	break;
        case "wizard":
        	Wizard wizard = new Wizard(x,y);
        	onLoad(wizard);
        	entity = wizard;
        	break;
        default:
        	break;
        }
        
        if (entity == null)  {
        	System.out.println(type);
        }
        dungeon.addEntity(entity);
    }
   

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

	public abstract void onLoad(Exit exit);
	
	public abstract void onLoad(Treasure treasure);
	
	public abstract void onLoad(Door door);
	
	public abstract void onLoad(Key key);
	
	public abstract void onLoad(Boulder boulder);
	
	public abstract void onLoad(Switch floorswitch);
	
	public abstract void onLoad(Portal portal);
	
	public abstract void onLoad(Enemies enemy);
	
	public abstract void onLoad(Sword sword);
	
	public abstract void onLoad(Potion potion);
	
	public abstract void onLoad(Crystal crystal);
	
	public abstract void onLoad(Rod rod);
	
	public abstract void onLoad(Element element);
	
	public abstract void onLoad(Wizard wizard);
}

