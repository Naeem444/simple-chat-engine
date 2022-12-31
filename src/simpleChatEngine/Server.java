package simpleChatEngine;
import java.io.*;
import java.net.*; // serversocket 
import java.util.*;


public class Server {
    
    private int port;
    
    Server(int port){
        this.port = port;
    }
    
    //methods
    public void invoke(){
        try(ServerSocket server = new ServerSocket(port)){
            System.out.println("Connection is now active at port: " + port);
            
            //It will keep accepting all the sockets that wants to get connected
            while(true){
                Socket socket = server.accept();
                System.out.println("A New user is connected with the server");
            }
            
            
            
            
        }
        catch(IOException e){
            System.out.println("Error occured in the server: " + e.getMessage());
        }
    }
    
    
 
    
    
 public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Write in cmd -> java Server.java [port]");
            System.exit(0);
        }
        
        int port = Integer.parseInt(args[0]);
        Server server = new Server(port);
        
        
        //invoking the server
        server.invoke();
        
        
        
        
    }
      
}
