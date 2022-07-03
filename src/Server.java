package com.examples.serverClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {

    public static void main(String[] args) {
        String receiveMessage;
        String sendMessage;
        System.out.println("Server Hazir");

        try {
            ServerSocket serverSocket = new ServerSocket(8585);
            Socket socket = serverSocket.accept();

            OutputStream outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            PrintWriter printWriter = new PrintWriter(outputStream, true);

            InputStream inputStream = socket.getInputStream();
            BufferedReader receiveRead = new BufferedReader(new InputStreamReader(inputStream));

            while (true) {
                if ((receiveMessage = receiveRead.readLine()) != null) {
                    System.out.println("CLIENT: " + receiveMessage);
                }
                sendMessage = bufferedReader.readLine();
                printWriter.println(sendMessage);
                try (BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter("C:\\turkcell\\socket.txt", true))) {

                    bufferedWriter.write("Server: " + sendMessage);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}