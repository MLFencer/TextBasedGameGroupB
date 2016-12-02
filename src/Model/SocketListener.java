package Model;

public interface SocketListener {
	public void onMessage(String ling);
	public void onClosedStatus(boolean isClosed);
}
