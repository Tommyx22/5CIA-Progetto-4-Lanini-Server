package com.example;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            ServerSocket server = new ServerSocket(3001);
            do{
                Socket s = server.accept();
                ServerManager t = new ServerManager();
                t.start();
            }while(true);
            
        }
        catch(Exception e){
            System.out.println("Errore di connessione");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}