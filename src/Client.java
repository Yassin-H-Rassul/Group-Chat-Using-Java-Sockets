
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    Socket echoSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    public void start() {

        try {
            echoSocket = new Socket("127.0.0.1", 4444);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Don't  know  host:  toshiba.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't  get  I/O  for  "
                    + "the  connection  to:  toshiba.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String userInput = null;
        System.out.println("please enter your username: ");
        try {
            userInput = stdIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(userInput);


        // listening:
        new Thread(() -> {
            String received = null;
            while(true) {
                try {
                    received = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(received);
            }
        }).start();

        // speaking:
        while (true) {
            try {
                userInput = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(userInput.contains("bye")) break;
            out.println(userInput);
        }

        // closing resources:
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stdIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            echoSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
