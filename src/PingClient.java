
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PingClient {
    public static void main(String[] args) {
        
        try (Socket connection = new Socket("localhost",8080);){
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            List<Long> tempos = new ArrayList<>();
            
            for (int x=0; x <1000; x++) {
                out.write("Ping\n");
                out.flush();
                long tempoInicial = System.nanoTime();
                System.out.println(in.readLine());
                long tempoFinal = System.nanoTime();
                long tempoTotal = tempoFinal - tempoInicial;
                tempos.add(tempoTotal);
                System.out.print(tempoTotal);
            }
            out.write("close\n");

            long totalTempos = 0;
            long menorTempo = Long.MAX_VALUE;
            for (Long tempo : tempos) {
                totalTempos = totalTempos + tempo;
                if (tempo < menorTempo){
                    menorTempo = tempo;
                }
            }
            System.out.println("Menor tempo:" + menorTempo + "ns");
            System.out.println("Total de tempo:" + totalTempos + "ns");
            System.out.println("Média de tempo:" + totalTempos/1000 + "ns");


        } catch (Exception e) {
            e.printStackTrace();
        }

        



    }
}