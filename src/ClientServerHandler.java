

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientServerHandler extends Thread {
    private final Socket socket;
    
    public ClientServerHandler (Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true){
                String mensagem = in.readLine();
                if (mensagem.equals("close")){
                    socket.close();
                    break;
                }
                if(mensagem.equalsIgnoreCase("Ping")){
                    out.write("Pong\n");
                    out.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
