package unsw.dungeon.EntityList;

import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.ExitInteract;

public class Exit extends Entity{
	
	/**
     * Constructor for new exit entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Exit(int x, int y) {
        super(x, y);
        interact = new ExitInteract();
    }
    
	/**
     * Once dungeon level is complete, print out finished to signify end of level and exit
     */
    public void finish() {
    	System.out.println("finished");
    	//System.exit(0);
    }
    
    public void error() {
    	System.out.println("complete other goals");
    }
}