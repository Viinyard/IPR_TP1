//...

package istic.pr.socket.chat;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {

    public static void main(String[] args) {
    	try(Socket socketVersUnClient = new Socket(InetAddress.getLocalHost(), 9999)) {
    		String charset = args.length >= 2 ? args[1] : "UTF-8";
    		PrintWriter printer = ClientTCP.creerPrinter(socketVersUnClient, charset);
    		BufferedReader reader = ClientTCP.creerReader(socketVersUnClient, charset);
    		
    		ClientTCP.envoyerNom(printer, "Vinyard");
    		
    		Thread tL = new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
    					String line;
						try {
							//System.out.print("prompt > ");
							line = ClientTCP.lireMessageAuClavier();
							ClientTCP.envoyerMessage(printer, line);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}
				}
			});
    		
    		tL.start();
    		
    		boolean loop = true;
    		while(loop) {
    			System.out.println(ClientTCP.recevoirMessage(reader));
    		}
    		
    		socketVersUnClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
        //créer une socket client

        //créer reader et writer associés

        //Tant que le mot «fin» n’est pas lu sur le clavier,

        //Lire un message au clavier

        //envoyer le message au serveur

        //recevoir et afficher la réponse du serveur
    }

    public static String lireMessageAuClavier() throws IOException {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static BufferedReader creerReader(Socket socketVersUnClient,
    		String charset) throws IOException {
        return new BufferedReader(new InputStreamReader(socketVersUnClient.getInputStream(), charset));
    }

    public static PrintWriter creerPrinter(Socket socketVersUnClient, String
    		charset) throws IOException {
       	return new PrintWriter(new OutputStreamWriter(socketVersUnClient.getOutputStream(), charset));
    }

    public static String recevoirMessage(BufferedReader reader) throws
    IOException {
        return reader.readLine();
    }
    
    public static void envoyerNom(PrintWriter printer, String nom) throws
    IOException {
    	printer.println("NAME:"+nom);
    	printer.flush();
    }

    public static void envoyerMessage(PrintWriter p, String message) throws
    IOException {
        p.println(message);
        p.flush();
    }

}