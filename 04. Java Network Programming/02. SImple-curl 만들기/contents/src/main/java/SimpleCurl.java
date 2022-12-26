import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SimpleCurl {
    public static void main(String[] args) throws IOException {
        System.out.println("=====================================================================");
        System.out.println("Usage: scurl [options] url");
        System.out.println("Options:");
        System.out.println("-v                 verbose, 요청, 응답 헤더를 출력합니다.");
        System.out.println("-H <line>          임의의 헤더를 서버로 전송합니다.");
        System.out.println("-d <data>          POST, PUT 등에 데이타를 전송합니다.");
        System.out.println("-X <command>       사용할 method 를 지정합니다. 지정되지 않은 경우 기본값은 GET 입니다.");
        System.out.println("-L                 서버의 응딥이 30x 계열이면 다음 응답을 따라 갑니다.");
        System.out.println(
            "-F <name=content>  multipart/form-data 를 구성하여 전송합니다. content 부분에 @filename 을 사용할 수 있습니다.");
        System.out.println(
            "=====================================================================scurl http://httpbin.org/get");

        // 2

        // 3
//        while (true) {
//            Socket socket = null;
//            String serverMessage = null;
//
//            // User Input
//            Scanner scanner = new Scanner(System.in);
//            String commandMsg = scanner.nextLine();
//            String[] items = commandMsg.split(" ");
//            List<String> commandArr = new ArrayList<>(Arrays.asList(items));
//
//            try {
//                // Command Varification
//                if (!Objects.equals(commandArr.get(0), "scurl")) {
//                    System.out.println("[Error] Invalid Command");
//                    continue;
//                }
//
//                // URL
//                URL url = new URL("http://httpbin.org/get");
//                String urlPath = url.getPath();
//                String urlHost = url.getHost();
//
//                // Socket Connect
//                socket = new Socket(urlHost, 80);
//                System.out.println("[Success] Connect");
//
//                // Request Header
//                PrintStream printStream = new PrintStream(socket.getOutputStream());
//                printStream.println("GET " + urlPath + " HTTP/1.1\n" +
//                    "Host: " + urlHost + "\n" +
//                    "User-Agent: curl/7.68.0\n" +
//                    "Accept: */*\n" +
//                    "\n"
//                );
//
//                // Response Data
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                serverMessage = bufferedReader.readLine();
//                System.out.println(serverMessage);
//
//                while ((serverMessage = bufferedReader.readLine()) != null) {
//                    System.out.println(serverMessage);
//                }
//            }  catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                assert socket != null;
//                socket.close();
//            }
//        }

        // 4
        while (true) {
            Socket socket = null;
            String serverMessage = null;

            // User Input
            Scanner scanner = new Scanner(System.in);
            String commandMsg = scanner.nextLine();
            String[] items = commandMsg.split(" ");
            List<String> commandArr = new ArrayList<>(Arrays.asList(items));

            try {
                // Command Varification
                if (!Objects.equals(commandArr.get(0), "scurl")) {
                    System.out.println("[Error] Invalid Command");
                    continue;
                }

                // URL
                URL url = new URL("http://httpbin.org/get");
                String urlPath = url.getPath();
                String urlHost = url.getHost();

                // Socket Connect
                socket = new Socket(urlHost, 80);
                System.out.println("[Success] Connect");

                // Request Header
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                printStream.println("GET " + urlPath + " HTTP/1.1\n" +
                    "Host: " + urlHost + "\n" +
                    "User-Agent: curl/7.68.0\n" +
                    "Accept: */*\n" +
                    "X-Custom-Header: NA" +
                    "\n"
                );

                // Response Data
                BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
                serverMessage = bufferedReader.readLine();
                System.out.println(serverMessage);

                while ((serverMessage = bufferedReader.readLine()) != null) {
                    System.out.println(serverMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                assert socket != null;
                socket.close();
            }
        }
    }
}
