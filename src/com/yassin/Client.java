//package com.yassin;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//public class Client {
//
//
//    public void start() {
//
//        Socket echoSocket = null;
//        PrintWriter out = null;
//        BufferedReader in = null;
//        try {
//            echoSocket = new Socket("127.0.0.1", 4444);
//            out = new PrintWriter(echoSocket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(
//                    echoSocket.getInputStream()));
//
//        } catch (UnknownHostException e) {
//            System.err.println("Don't  know  host:  toshiba.");
//            System.exit(1);
//        } catch (IOException e) {
//            System.err.println("Couldn't  get  I/O  for  "
//                    + "the  connection  to:  toshiba.");
//            System.exit(1);
//        }
//
//        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//
//        String userInput = null;
//        System.out.println("please enter your username: ");
//        try {
//            userInput = stdIn.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        out.println(userInput);
////        System.out.println("Welcome to the room!");
//
//        // speaking:
//        while (true) {
//            try {
//                if ((userInput = stdIn.readLine()) == null) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            out.println(userInput);
//        }
//
//        // listening:
//        BufferedReader finalIn = in;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String received = null;
//                try {
//                    received = finalIn.readLine();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(received);
//            }
//        });
//
//
//        // closing resources:
//        out.close();
//        try {
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            stdIn.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            echoSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
