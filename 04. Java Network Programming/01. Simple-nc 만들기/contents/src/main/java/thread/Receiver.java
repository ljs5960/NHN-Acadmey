package thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Receiver extends Thread {
    Socket socket;
    DataInputStream dataInputStream;

    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (dataInputStream != null) {
            try {
                System.out.println(dataInputStream.readUTF());
            } catch (SocketException se) {
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("[Success] Terminated");
                    }
                }));
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
