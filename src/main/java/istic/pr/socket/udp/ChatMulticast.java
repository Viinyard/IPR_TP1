/**
 *
 */
package istic.pr.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author VinYarD
 *
 * IPR_TP1/ChatMulticast/ChatMulticast.java
 * 27 janv. 2018
 */

public class ChatMulticast {

	public static void main(String[] args) {

		String name;
		if(args.length < 1) {
			System.err.println("Error usage : you have to specifie a name !");
			System.exit(1);
		}

		if(args[0].length() < 4) {
			System.err.println("Error usage : your name must have more than 4 characters !");
			System.exit(2);
		}

		name = args[0];

		try {
			InetAddress groupeIP = InetAddress.getByName("225.0.4.8");
			int port = 9999;

			MulticastSocket socket = new MulticastSocket(port);
			socket.joinGroup(groupeIP);

			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					while(true) {
						String m = ChatMulticast.recevoirMessage(socket);

						System.out.println(m);//ChatMulticast.envoyerMessage(socket, m, port, groupeIP);
					}
				}

			});

			t.start();

			boolean loop = true;
			while(loop) {
				ChatMulticast.envoyerMessage(socket, name + " > " + ChatMulticast.lireMessageAuClavier(), port, groupeIP);
			}

			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static void envoyerMessage(MulticastSocket s, String message, int port, InetAddress groupeIP) {
		byte[] buffer = message.getBytes();
		DatagramPacket data = new DatagramPacket(buffer, buffer.length, groupeIP, port);
		try {
			s.send(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String recevoirMessage(MulticastSocket s) {
		byte[] buffer = new byte[1024];
		DatagramPacket message = new DatagramPacket(buffer, buffer.length);
		try {
			s.receive(message);
			return new String(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "err";
	}

	public static String lireMessageAuClavier() throws IOException {
        return new Scanner(System.in).nextLine();
    }

}
