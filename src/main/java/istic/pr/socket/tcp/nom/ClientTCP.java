//...

package istic.pr.socket.tcp.nom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {

    public static void main(String[] args) {
    	try(Socket socketVersUnClient = new Socket(InetAddress.getLocalHost(), 9999)) {
    		PrintWriter printer = ClientTCP.creerPrinter(socketVersUnClient);
    		BufferedReader reader = ClientTCP.creerReader(socketVersUnClient);
    		
    		ClientTCP.envoyerNom(printer, "Vinyard");
    		
    		String line;
    		do {
    			System.out.print("prompt > ");
    			line = ClientTCP.lireMessageAuClavier();
    			ClientTCP.envoyerMessage(printer, line);
    			System.out.println(ClientTCP.recevoirMessage(reader));
    			
    		} while(!line.equals("fin"));
    		
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

    public static BufferedReader creerReader(Socket socketVersUnClient)
    throws IOException {
        return new BufferedReader(new InputStreamReader(socketVersUnClient.getInputStream()));
    }

    public static PrintWriter creerPrinter(Socket socketVersUnClient) throws
    IOException {
        return new PrintWriter(socketVersUnClient.getOutputStream());
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