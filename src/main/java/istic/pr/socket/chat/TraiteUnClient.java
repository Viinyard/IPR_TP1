/**
 * 
 */
package istic.pr.socket.chat;

import java.net.Socket;

/**
 * @author VinYarD
 *
 * IPR_TP1/istic.pr.socket.thread/TraiteUnClient.java
 * 25 janv. 2018
 */

public class TraiteUnClient implements Runnable {

	private Socket socketVersUnClient;
	private String charset;
	
	public TraiteUnClient(Socket socketVersUnClient, String charset) {
		this.socketVersUnClient = socketVersUnClient;
		this.charset = charset;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ServeurTCP.traiterSocketCliente(this.socketVersUnClient, this.charset);
	}

}
