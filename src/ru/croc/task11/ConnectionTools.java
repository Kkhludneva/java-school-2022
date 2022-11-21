package ru.croc.task11;

import java.io.*;
import java.net.Socket;

public class ConnectionTools {

    /*Сюда убрала потоки ввода\вывода, отправку сообщение */
    public static void writeLine(String message, Socket socket){
        try{
            BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(message);
            writer.newLine();
            writer.flush();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
    public static String readLine(Socket socket){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return reader.readLine();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

}
