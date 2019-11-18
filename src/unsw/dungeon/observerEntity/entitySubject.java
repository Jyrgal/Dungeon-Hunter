package unsw.dungeon.observerEntity;

import unsw.dungeon.Dungeon;

public interface entitySubject {
	public void addEntityObserver(entityObserver observer);
	public void removeEntityObserver(entityObserver observer);
	public void notifyEntityObserver(Dungeon dungeon, boolean complete);
}