package simpleChatEngine;
import java.io.*;
import java.net.*; // serversocket 
import java.util.*;


public class Server {
    
    private int port;
    private ArrayList<HandleUser> userData = new ArrayList<>();

    Server(){
        
    }
    Server(int port){
        this.port = port;
        
    }
    
    //methods
    public void invoke(){
        try(ServerSocket server = new ServerSocket(port)){
            System.out.println("\nConnection is now active at port: " + port + "\n\n");
            
            //It will keep accepting all the sockets that wants to get connected
            while(true){
                Socket clientSocket = server.accept();
        
                HandleUser user = new HandleUser(clientSocket, this); 
                userData.add(user);
                user.start(); //activating the thread
                         
            }
  
            
        }
        catch(IOException e){
            System.out.println("Error occured in the server: " + e.getMessage());
        }
    }
    public void sendMsgtoAll(String msg){
        // looping for every userDetail in the userData List and then send 'em all
        for(HandleUser user: userData){
            if(user != null){
                user.sendMsg(msg);
            }
        }
    }
    

    
    public static void main(String[] args) {
           // command line input verification
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


class HandleUser extends Thread{
    private Socket clientSocket;
 
    PrintWriter writer;

    private String name;
    private Server server;
    HandleUser(){
        
    }
    
    HandleUser(Socket clientSocket, Server server){
        this.clientSocket = clientSocket;
        this.server = server;

    }
    

    
    @Override
    public void run() {
        
        try{

//           output = new PrintWriter(clientSocket.getOutputStream(), true);
//            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//             System.out.println("working this");
             
            InputStream input = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = clientSocket.getOutputStream();
            writer = new PrintWriter(output, true);
 
          
            writer.println("Enter Your Name: ");
            name = reader.readLine();
            System.out.println(name + " is now connected with the server...\n");
//            server.addUserName(userName);
 
            String joinMsg = name + " just joined in this chat room";
            server.sendMsgtoAll(joinMsg);

            
            String msg;
            while ((msg = reader.readLine()) != null){
                if(msg.startsWith("?leave")){
                    // functionality will be added later
                }
                else{
                    //sending msg to all the clients
                    server.sendMsgtoAll( "\n"+ name + ": " + msg);
                }
            }
                 
        
        }
        catch(IOException e){
            // TODO: handle it later
        }
        
    }
 

    public void sendMsg(String msg){
            writer.println(msg);
    }
    
    
}

