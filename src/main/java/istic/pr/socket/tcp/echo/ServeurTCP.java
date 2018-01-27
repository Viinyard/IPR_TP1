//...

package istic.pr.socket.tcp.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTCP {

    public static void main(String[] args) {
    	//ServerSocket server;
    	try(ServerSocket server = new ServerSocket(9999)) {
			boolean loop = true;
    		while(loop) {
    			traiterSocketCliente(server.accept());
    		}
    		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//
		}
        //Attente des connexions sur le port 9999

        //Traitement des exceptions

        //Dans une boucle, pour chaque socket clientes, appeler traiterSocketCliente

    }

    public static void traiterSocketCliente(Socket socketVersUnClient) {
        //Créer printer et reader
    	try {
			BufferedReader reader = ServeurTCP.creerReader(socketVersUnClient);
			PrintWriter printer = ServeurTCP.creerPrinter(socketVersUnClient);
			
			String s;
			while(( s = ServeurTCP.recevoirMessage(reader)) != null) {
				System.out.println(s);
				
				ServeurTCP.envoyerMessage(printer, "ok");
			}
			
			socketVersUnClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        //Tant qu’il y’a un message à lire via recevoirMessage

        //Envoyer message au client via envoyerMessage

        //Si plus de ligne à lire fermer socket cliente
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

    public static void envoyerMessage(PrintWriter printer, String message)
    throws IOException {
        printer.println(message);
        printer.flush();
    }

}