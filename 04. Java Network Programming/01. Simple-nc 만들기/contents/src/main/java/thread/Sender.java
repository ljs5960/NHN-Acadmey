package thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
    Socket socket;
    DataOutputStream dataOutputStream;

    public Sender(Socket socket) {
        this.socket = socket;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (dataOutputStream != null) {
            try {
                dataOutputStream.writeUTF(scanner.nextLine());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    scanner.close();
                    dataOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
