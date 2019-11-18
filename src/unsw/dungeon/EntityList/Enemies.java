package unsw.dungeon.EntityList;

import unsw.dungeon.interactEntity.EnemyInteract;
import unsw.dungeon.observerPlayer.playerObserver;

import java.util.ArrayList;

import unsw.dungeon.*;
import unsw.dungeon.EnemyStrategy.*;

public class Enemies extends Entity implements playerObserver {
	enemyState charge = new PepegaCharge();
	enemyState run = new OmegaRun();
	enemyState death = new KekDeath();
	enemyState currState = new PepegaCharge();
	enemyState freeze = new Frozen();
   
	/**
     * Constructor for new enemy entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Enemies(int x, int y) {
        super(x, y);
        interact = new EnemyInteract();
    }

    /**
     * Enemy moves up
     * @param dungeon, the current dungeon
     */
	 public void moveUp(Dungeon dungeon) {
		 if (getY() > 0)
			 y().set(getY() - 1);
	 }

    /**
     * Enemy moves down
     * @param dungeon, the current dungeon
     */
	 public void moveDown(Dungeon dungeon) {
		 if (getY() < dungeon.getHeight() - 1)
			 y().set(getY() + 1);
	 }

    /**
     * Enemy moves left
     * @param dungeon, the current dungeon
     */
	 public void moveLeft(Dungeon dungeon) {
	     if (getX() > 0)
			 x().set(getX() - 1);
	 }
	 
	 /**
     * Enemy moves right
     * @param dungeon, the current dungeon
     */
	 public void moveRight(Dungeon dungeon) {
		 if (getX() < dungeon.getWidth() - 1)
			 x().set(getX() + 1);
	 }

	 /**
     * update observers of the enemy state
     * @param player, the player in the current dungeon
     * @param dungeon, the current dungeon
     */
	@Override
	public void updateObserver(Player player, Dungeon dungeon) {
//		if (player.getX() == this.getX() && player.getY() == this.getY()) {
//			if (player.checkSword()) {
//				System.out.println("shoudl die");
//				this.notifyEntityObserver(dungeon, true);
//				this.die(dungeon);
//				Sword sword = (Sword)player.findSword();
//				sword.use(player);
//			}	else if (player.checkPotion()){
//				System.out.println("shoudl die");
//				this.notifyEntityObserver(dungeon, true);
//				this.die(dungeon);			
//			}
//		}
		if (player.chaseEnemy()) {
			this.currState = charge;
			currState.move(dungeon, this);
		}	else {
			this.currState = run;
			currState.move(dungeon, this);
		}
		

//		//enemy interact with portal
//		Portal portal = dungeon.checkPortal(this.getX(), this.getY());
//		if (portal != null) {
//			Portal nextPortal = (Portal)dungeon.findSpecificEntity(portal);
//			this.teleport(nextPortal.getX(), nextPortal.getY());
//		}
	}
	
	 /**
     * update state of the enemy, the enemy's death
     * @param dungeon, the current dungeon
     */
	public void die(Dungeon dungeon) {
    	this.currState = death;
    	dungeon.removeEntity(this);
		Player player = dungeon.getPlayer();
		//player.removePlayerObserver(this);
    }

	 /**
     * Checks if the current state of the enemy is dead
     * @return true if enemy is dead, false otherwise
     */
	public boolean enemyDead() {
		if (currState == death) {
			return true;
		}
		return false;
	}
	
	public void dumbEnemy() {
		this.currState = run;
	}
	
	public void freezeEnemy() {
		this.currState = freeze;
	}
	
}
