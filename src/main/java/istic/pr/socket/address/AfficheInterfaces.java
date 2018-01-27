/**
 * 
 */
package istic.pr.socket.address;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author VinYarD
 *
 * IPR_TP1/istic.pr.socket.address/AfficheInterfaces.java
 * 16 janv. 2018
 */

public class AfficheInterfaces {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			for(Enumeration<NetworkInterface> eni = NetworkInterface.getNetworkInterfaces(); eni.hasMoreElements(); ) {
				NetworkInterface ni = eni.nextElement();
				System.out.println(ni.getName() + ": " + ni.getDisplayName());
				
				for(Enumeration<InetAddress> eia = ni.getInetAddresses(); eia.hasMoreElements();) {
					InetAddress ia = eia.nextElement();
					System.out.println("->" + ia.getHostName());
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		

	}
	

}
