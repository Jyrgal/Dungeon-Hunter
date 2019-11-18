package unsw.dungeon.observerPlayer;

public interface playerSubject {
	public void addPlayerObserver(playerObserver observer);
	public void removePlayerObserver(playerObserver observer);
	public void notifyPlayerObserver();
}
