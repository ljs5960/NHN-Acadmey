package client;

import java.io.EOFException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.Scanner;
import thread.Receiver;
import thread.Sender;

public class SimpleNcClient {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("Usage: snc [option] [hostname] [port]");
        System.out.println("Options:");
        System.out.println("-l   <port>     서버 모드로 동작, 입력 받은 포트로 listen");
        System.out.println("============================================================");

        while (true) {
            // 명령어 입력
            Scanner scanner = new Scanner(System.in);
            String sncCommand = scanner.nextLine();

            // 명령어 파싱
            String[] commandArr = sncCommand.split(" ");
            // 명령어 예외처리
            try {
                if ((!Objects.equals(commandArr[0], "snc")) ||
                    (Objects.equals(commandArr[1], " ")) ||
                    (commandArr[2] == null)) {
                    System.out.println("[Error] Invalid Command");
                    continue;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("[Error] Invalid Command");
                continue;
            }
            String ipAddress = commandArr[1];   // IP주소
            int portNumber = Integer.parseInt(commandArr[2]);   // 포트번호

            Socket socket = null;

            try {
                socket = new Socket(ipAddress, portNumber);
                System.out.println("[Success] Connected with Server");

                Sender sender = new Sender(socket);
                Receiver receiver = new Receiver(socket);

                sender.start();
                receiver.start();
            } catch (SocketException ue) {  // IP주소 예외처리
                System.out.println("[Error] Invalid IP Address");
            } catch (EOFException ee) {
                return ;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
