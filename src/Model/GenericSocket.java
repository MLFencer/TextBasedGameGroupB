package Model;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.net.*;
import java.util.logging.Logger;

import Controller.MultiController;

public abstract class GenericSocket implements SocketListener{

	private final static Logger LOGGER =
			Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public int port;
	protected Socket socketConnection = null;
	private BufferedWriter output = null;
	private BufferedReader input = null;
	private boolean ready = false;
	private Thread socketReaderThread;
	private Thread setupThread;


	/**
	 * Set up a connection in the background.  This method returns no status,
	 * however the onClosedStatus(boolean) method will be called when the
	 * status of the socket changes, either opened or closed (for whatever
	 * reason).
	 */
	public void connect() {
		try {
			/*
			 * Background thread to set up and open the input and
			 * output data streams.
			 */
			setupThread = new SetupThread();
			setupThread.start();
			/*
			 * Background thread to continuously read from the input stream.
			 */
			socketReaderThread = new SocketReaderThread();
			socketReaderThread.start();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * This method is invoked to do instance-specific socket initialization.
	 * Due to the different ways that sockets are set up (e.g.
	 * ServerSocket vs Socket), the implementation details go here.
	 * Initialization up to and including either accept() or connect() take
	 * place here.
	 * @throws java.net.SocketException
	 */
	protected abstract void initSocketConnection() throws SocketException;

	/**
	 * This method is called to close any additional sockets that are
	 * internally used.  In some cases (e.g. SocketClient), this method
	 * should do nothing.  In others (e.g. SocketServer), this method should
	 * close the internal ServerSocket instance.
	 */
	//protected abstract void closeAdditionalSockets();

	/*
	 * Synchronized method set up to wait until the SetupThread is
	 * sufficiently initialized.  When notifyReady() is called, waiting
	 * will cease.
	 */
	private synchronized void waitForReady() {
		while (!ready) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
	}

	/*
	 * Synchronized method responsible for notifying waitForReady()
	 * method that it's OK to stop waiting.
	 */
	private synchronized void notifyReady() {
		ready = true;
		notifyAll();
	}

	/**
	 * Send a message in the form of a String to the socket.  A NEWLINE will
	 * automatically be appended to the message.
	 *
	 * @param msg The String message to send
	 */
	public void sendMessage(String msg) {
		try {
			output.write(msg);
			output.newLine();
			output.flush();

			System.out.println("send> " + msg);

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	class SetupThread extends Thread {

		@Override
		public void run() {
			try {
				/*
				 * Get input and output streams
				 */
				initSocketConnection();
				input = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
				output = new BufferedWriter(new OutputStreamWriter(socketConnection.getOutputStream()));
				output.flush();

				/*
				 * Notify SocketReaderThread that it can now start.
				 */
				notifyReady();
				MultiController.setIsConnected(true);
			} catch (IOException e) {
				System.out.println(e);

				/*
				 * This will notify the SocketReaderThread that it should exit.
				 */
				System.out.println("Ready");
				notifyReady();
			}
		}
	}

	class SocketReaderThread extends Thread {

		@Override
		public void run() {
			/*
			 * Wait until the socket is set up before beginning to read.
			 */
			waitForReady();
			/*
			 * Read from from input stream one line at a time
			 */
			try {
				if (input != null) {
					String line;
					while ((line = input.readLine()) != null) {
						//String logMsg = "recv> " + line;
						//System.out.println(logMsg);

						//MultiController.outLine(line);

						/*
						 * The onMessage() method has to be implemented by
						 * a sublclass.  If used in conjunction with JavaFX,
						 * use Platform.runLater() to force this method to run
						 * on the main thread.
						 */
						onMessage(line);
					}
				}
			} catch (IOException e) {
				System.out.println(e);

			}
		}
	}

	GenericSocket(int port) {
		this.port=port;
	}
}
