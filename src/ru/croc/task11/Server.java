package ru.croc.task11;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args)  {

        try(ServerSocket serverSocket= new ServerSocket(2021)){
            System.out.println("Server Started");

            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Новое подключение...");
                Thread newClient = new Thread(new ClientConnectionRunnable(socket));
                newClient.start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("client connected");
    }


}
