package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import thread.Receiver;
import thread.Sender;

public class SimpleNcServer {
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
                if ((!Objects.equals(commandArr[0], "snc")) ||  // snc 명령어가 아닐 경우
                    (!Objects.equals(commandArr[1], "-l")) ||   // option이 -l이 아닐 경우
                    (commandArr[2] == null)) { // portNum이 null일 경우
                    System.out.println("[Error] Invalid Command");
                    continue;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("[Error] Invalid Command");
                continue;
            }
            int portNum = Integer.parseInt(commandArr[2]);  // 포트번호

            try {
                ServerSocket serverSocket = new ServerSocket(portNum);
                System.out.println("[Success] Server is Ready");

                Socket socket = serverSocket.accept();
                System.out.println("[Success] Connected with Client");

                Sender sender = new Sender(socket);
                Receiver receiver = new Receiver(socket);

                sender.start();
                receiver.start();
            } catch (Exception e) {
                e.printStackTrace();
                return ;
            }
        }
    }
}
