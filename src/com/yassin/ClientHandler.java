package com.yassin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

    public class ClientHandler extends Thread {

        private Socket socket;
        private static ArrayList<ClientHandler> clientsList = new ArrayList<>();
        private String username;
        public PrintWriter out = null;
        public BufferedReader in = null;


        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            try {
                out = new PrintWriter(socket.getOutputStream(),true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                username = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            clientsList.add(this);
        }

        @Override
        public void run() {




            // reading clients username:



            System.out.println("client connected!");

            String message;

            message = username + " entered the room!";
            sendToAllClients(message);
            message = null;
            while (true){
                try {
                    message = username + ": "+in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (message != null && message.contains("bye")){
                    message = username + " left the room!";
                    sendToAllClients(message);
                    break;
                }
                sendToAllClients(message);
            }
            out.close();
            try {
                in.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }

        public void sendToAllClients(String msg){
            for (ClientHandler c :
                    clientsList) {
                if( c.username != null && !c.username.equals(username)) {
                    c.out.println(msg);
                    System.out.println("sending " + msg + " to all clients:");
                }
            }
        }

    }
