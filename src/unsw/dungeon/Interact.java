package unsw.dungeon;

public interface Interact {
	
	/**
	 * interface for entities to interact with other entities
	 * @param dungeon
	 * @param entity
	 * @param direction
	 * @return
	 */
	boolean checkMove(Dungeon dungeon, Entity entity, Direction direction);
	void entityInteract(Dungeon dungeon, Entity entity, Direction direction);
}
