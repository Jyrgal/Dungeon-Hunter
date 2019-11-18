package unsw.dungeon;

import unsw.dungeon.EntityList.Enemies;

/**
 * interface for state of enemy
 */
public interface enemyState {
	void move(Dungeon dungeon, Enemies enemy);
}
