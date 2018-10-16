package Comunicacion;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente extends Thread{
    Socket s;
    int port;
    String ip;

   public Cliente (String ip, int port){
       this.ip =ip;
       this.port = port;


    }

    public void run(){
        try {
            s = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviar(final Mensaje m){

       new Thread(new Runnable() {
           @Override
           public void run() {

               try {
                   OutputStream salida = s.getOutputStream();

                   ObjectOutputStream objSalida = new ObjectOutputStream(salida);

                   objSalida.writeObject(m);


               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
       }).start();

    }
}
