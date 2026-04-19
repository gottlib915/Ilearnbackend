package com;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.lang.Thread;


public class Multistream {
    
    
    public static void main(String[] args) {
        System.out.println("1");
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Server started!");
            
            while (true) {
                // ожидаем подключения
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                new Thread(ClientHandler(socket)).start();
            }   
    
    } catch (IOException ex) {
            ex.printStackTrace();
    }

    }


};



