package com;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
// import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class ClientHandler implements Runnable {
    private final Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // try (ServerSocket serverSocket = new ServerSocket(port)) {
        //     System.out.println("Server started!");
            
        //         // для подключившегося клиента открываем потоки 
        //         // чтения и записи
            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                    PrintWriter output = new PrintWriter(socket.getOutputStream())) {

                // ждем первой строки запроса
                while (!input.ready());

                    // считываем и печатаем все что было отправлено клиентом
                System.out.println();
                while (input.ready()) {
                    System.out.println(input.readLine());
                }

                // отправляем ответ
                output.println("HTTP/1.1 200 OK");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                output.println("<p>Hi jocker!</p>");
                output.flush();
                    
                // по окончанию выполнения блока try-with-resources потоки, 
                // а вместе с ними и соединение будут закрыты
                System.out.println("Client disconnected!");
            } catch (IOException ex) {
             ex.printStackTrace();
            }
        // } catch (IOException ex) {
        //     ex.printStackTrace();
        // }
    }
}