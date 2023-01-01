package simpleChatEngine;
import java.util.*;
import java.io.*;
import java.net.*;




public class Client {
    private String host;
    private int port;
    Socket clientSocket;
    //gets the input stream from the socket
    private BufferedReader input;
    // to write I will use it and push it to server for output
    private PrintWriter output;
    
    Client(String host, int port){
        this.host = host;
        this.port = port;
        
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
    
    
    public void invoke(){
        try{
//            socket: one endpoint(ip address, port) of a two-way communication link between two programs 
//            running on the network. (localhost ---> 127.0. 0.1)

            clientSocket = new Socket(host, port);
            System.out.println("You are connected to the chat server. Have fun.");
            
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            HandleClient data = new HandleClient(clientSocket, output);
            Thread clientThread = new Thread(data);
            clientThread.start();
            
             String msg;
            while ((msg = input.readLine()) != null){
                System.out.println(msg);
            }
//        
            
            
            
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
            System.out.println("Write in cmd -> java Client.java [host] [port]");
            System.exit(0);
        }
        
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        Client clientSocket = new Client(ip, port);
        
        
        //invoking the client
        clientSocket.invoke();
    
    }
    
}

class HandleClient implements Runnable{
    Socket client;
    private PrintWriter output;
    
    HandleClient(Socket client , PrintWriter output){
        this.client = client;
        this.output = output;
    }

    @Override
    public void run() {
     
        try {
            OutputStream out = client.getOutputStream();
            output = new PrintWriter(out, true);
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String msg;
                try {
                    
//                        output.println("Enter Your text here: ");
                        msg = inputReader.readLine();
                        if(msg.startsWith("?leave")){
                        // functionality
                        }
                        else{
                            output.println(msg);
                        }
                    
                    
                } catch (IOException ex) {
                    //handle it later
                }
                
            }
        } catch (IOException ex) {
            //handle it later
        } 
    
    }
        
    
    
    
}