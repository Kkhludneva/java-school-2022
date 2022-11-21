package ru.croc.task11;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)  {
        try(ServerSocket serverSocket= new ServerSocket(2021)){
            System.out.println("Server Started");
            Socket socket = serverSocket.accept();
            while(true) {

                String s = "Пришло сообщение: " + ConnectionTools.readLine(socket);
                System.out.println(s);
                ConnectionTools.writeLine(s,socket);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("client connected");
    }

}
