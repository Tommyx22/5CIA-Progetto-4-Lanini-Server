package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class ServerManager extends Thread {
    public static void main( String[] args )
    {
        try {      
            System.out.println("Server in avvio!");

            ServerSocket server = new ServerSocket(3000);

            Socket s = server.accept();

            System.out.println("Un client si è collegato");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            String ricevuta = in.readLine();
            System.out.println(ricevuta);

            String stringaRicevuta;

            do {
                stringaRicevuta = in.readLine();
                System.out.println(stringaRicevuta);
            } while(!stringaRicevuta.isEmpty());

            String[] stringa2 = ricevuta.split(" ");

            File f = new File(stringa2[1].substring(1));

            if(f.exists()) {
                sendBinaryFile(s, f);
                /*String msg = "il file esiste";
                out.writeBytes("HTTP/1.1 200 OK\n");
                out.writeBytes("Content-Length: " + msg.length() + "\n");
                out.writeBytes("\n");
                out.writeBytes(msg);*/
            } else {
                String msg = "il file non esiste";
                out.writeBytes("HTTP/1.1 404 Not found\n");
                out.writeBytes("Content-Length: " + msg.length() + "\n");
                out.writeBytes("\n");
                out.writeBytes(msg);
            }

            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }

    private static void sendBinaryFile(Socket socket, File file) throws IOException {
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        output.writeBytes("HTTP/1.1 200 OK\n");
        output.writeBytes("Content-Length: " + file.length() + "\n");


        output.writeBytes("Content-Type: " + getContentType(file) + "\n");

        output.writeBytes("\n");
        InputStream input = new FileInputStream(file);
        byte[] buf = new byte[8192];
        int n;
        while ((n = input.read(buf)) != -1) {
            output.write(buf, 0, n);
        }
        input.close();
    }

    private static String getContentType(File f) {
        String fileName = f.getName();
        String[] temp = fileName.split("\\.");
        String estensione = temp[temp.length-1];

        switch (estensione) {

            case "html":
                return "text/html";
            case "png":
                return "image/png";
            case "jpeg":
            case "jpg":
                return "image/jpeg";
            case "css":
                return "text/css";

            default:
                return "text/plain";
        }



    }
}