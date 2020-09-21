
import java.net.ServerSocket;
import java.net.Socket;

public class ClientServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080);){
            
            while (true){
                Socket connection = serverSocket.accept();
                ClientServerHandler serverHandler= new ClientServerHandler(connection);
                serverHandler.start();
            }

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
