import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SimpleCurlMain {
    public static void main(String[] args) {
        System.out.println("scurl http://httpbin.org/get");
        System.out.println("scurl -X GET http://httpbin.org/get");
        System.out.println("scurl -v http://httpbin.org/get");
        System.out.println("scurl -v -H \"X-Custom-Header: NA\" -v http://httpbin.org/get");
        System.out.println("scurl -v -X POST -d \\\"{ \"hello\": \"world\" }\\\" -H \"Content-Type: application/json\"  http://httpbin.org/post");
        System.out.println("scurl -L http://httpbin.org/status/302");
        System.out.println("scurl -F \"upload=@file_path\" http://httpbin.org/post");
        System.out.println("===============================================================");

        while (true) {
            // User Input
            Scanner scanner = new Scanner(System.in);
            String commandMsg = scanner.nextLine();
            String[] items = commandMsg.split(" ");
            List<String> commandArr = new ArrayList<>(Arrays.asList(items));

            // Command Varification
            try {
                if (!Objects.equals(commandArr.get(0), "scurl")) {
                    switch (commandArr.get(1)) {
                        case "-v":
                            System.out.println("test");
                            break;
                        case "-x":
                            System.out.println("test1");
                            break;
                    }
                } else {    // First Command  isn't scurl
                    System.out.println("[Error] Invalid Command");
                }
            } catch (NullPointerException e) {
                System.out.println("Invalid Command");
                e.printStackTrace();
            }
        }
    }
}
