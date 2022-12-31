package simpleChatEngine;


public class SimpleChatEngine {

    
    public static void main(String[] args) {
        
         int port = Integer.parseInt(args[0]);
         System.out.println(port);
        Server server = new Server(port);
        
        //invoking the server
        server.invoke();
        
        
        
        
    }
    
}
