package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Direction;
import unsw.dungeon.Dungeon;
import unsw.dungeon.EntityList.*;
import unsw.dungeon.Goals.*;
import unsw.dungeon.observerEntity.entityObserver;

class goalTest {
	Dungeon dungeon = new Dungeon(6,6);
	Player player = new Player(dungeon, 2, 2);
	Boulder b1 = new Boulder(3,2);
	Switch f1 = new Switch(4,2,dungeon);
	Treasure t1 = new Treasure(2,3);
	Treasure t2 = new Treasure(3,1);
	
	Exit e1 = new Exit(2,1);
	Exit e2 = new Exit(2,4);
	Exit e3 = new Exit(0,2);
	
	GoalComponent gr1 = new GoalGroup(1);	//or
	GoalComponent gr2 = new GoalGroup(2);	//and
	
	GoalComponent gf1 = new Goal("floorswitch", 1);
	GoalComponent gt1 = new Goal("treasure", 1);
	GoalComponent gt2 = new Goal("treasure", 2);
	GoalComponent ge1 = new Goal("exit", 1);
	GoalComponent ge2 = new Goal("exit", 1);
	GoalComponent ge3 = new Goal("exit", 1);
	

	@Test
	void exitGoal() {
		dungeon.setPlayer(player);
		dungeon.addEntity(e1);
		dungeon.setGoal(ge1);
		e1.addEntityObserver(ge1);
		
		Direction direction = Direction.UP;
		assertEquals(dungeon.move(2, 1, direction), true, "dungeon was not properly completed after reaching exit");
		player.moveUp();
		
		dungeon.playerInteract(2, 1, direction);

		assertEquals(dungeon.getComplete(), true, "dungeon was not properly completed after reaching exit");
	}

//	@Test
//	void enemyGoal() {
//		dungeon.setPlayer(player);
//		dungeon.setGoal(1);
//		dungeon.addEntity(e1);
//		dungeon.addGoal(ge1);
//		((Goal) ge1).addEntity(e1);
//		dungeon.addObserver((entityObserver) ge1);
//	}
//	
//	@Test
//	void switchGoal() {
//		dungeon.setPlayer(player);
//		dungeon.setGoal(1);
//		dungeon.addEntity(e1);
//		dungeon.addGoal(ge1);
//		((Goal) ge1).addEntity(e1);
//		dungeon.addObserver((entityObserver) ge1);
//	}
//	
//	@Test
//	void treasureGoal() {
//		dungeon.setPlayer(player);
//		dungeon.setGoal(1);
//		dungeon.addEntity(e1);
//		dungeon.addGoal(ge1);
//		((Goal) ge1).addEntity(e1);
//		dungeon.addObserver((entityObserver) ge1);
//	}
}
