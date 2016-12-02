package Model;

import java.io.IOException;
import java.net.*;

//import Controller.MultiController.FxSocketListener;

public class SocketClient extends GenericSocket implements SocketListener{
	public String host;
    private SocketListener fxListener;

    @Override
    public void onMessage(final String line) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fxListener.onMessage(line);
            }
        });
    }

    /**
     * Called whenever the open/closed status of the Socket
     * changes.  In JavaFX, this method must be run on the main thread and
     * is accomplished by the Platform.runLater() call.  Failure to do so
     * will* result in strange errors and exceptions.
     * @param isClosed true if the socket is closed
     */
    @Override
    public void onClosedStatus(final boolean isClosed) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fxListener.onClosedStatus(isClosed);
            }
        });
    }

    /**
     * Initialize the SocketClient up to and including issuing the accept()
     * method on its socketConnection.
     * @throws java.net.SocketException
     */
    @Override
    protected void initSocketConnection() throws SocketException {
        try {
            socketConnection = new Socket();
            socketConnection.connect(new InetSocketAddress(host, port));

                System.out.println("Connected to " + host
                        + "at port " + port);

        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    /**
     * For SocketClient class, no additional work is required.  Method
     * is null.
     * @param fxSocketListener
     */
    public SocketClient(String host, int port, SocketListener sl) {
        super(port);
        this.host=host;
        this.fxListener=sl;

    }
}


