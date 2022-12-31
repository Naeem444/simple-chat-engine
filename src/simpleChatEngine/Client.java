package simpleChatEngine;
import java.util.*;
import java.io.*;
import java.net.*;


public class Client {
    private String host;
    private int port;
    
    Client(String host, int port){
        this.host = host;
        this.port = port;
        
    }
    
    public void invoke(){
        try{
//            socket: one endpoint(ip address, port) of a two-way communication link between two programs 
//            running on the network.

            Socket socket = new Socket(host, port);
            System.out.println("You are connected to the chat server. Have fun.");
            
            
        }
        catch(UnknownHostException e){
            System.out.println("Error Occured: Server Unavailable");
        } 
        catch (IOException e) {
            System.out.println("IO Error: "+e.getMessage());
        }
    }

    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Write in cmd -> java Client.java [ip] [port]");
            System.exit(0);
        }
        
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        Client clientSocket = new Client(ip, port);
        
        
        //invoking the client
        clientSocket.invoke();
    
}
    
}
