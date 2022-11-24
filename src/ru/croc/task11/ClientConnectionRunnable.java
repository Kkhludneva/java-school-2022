package ru.croc.task11;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnectionRunnable implements Runnable {

    /*Сюда убрала потоки ввода\вывода, отправку сообщение */
    /*public static void writeLine(String message, Socket socket){
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
    }*/

    //чтобы отправлять сообщение нескольким клиентам, пробегаясь по массиву
    public static ArrayList<ClientConnectionRunnable> connectedClients = new ArrayList<>();

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    private String userName;
    private String message;

    public ClientConnectionRunnable(Socket socket){
        try{
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName = reader.readLine();
            connectedClients.add(this);
            sendToEveryone("---"+this.userName+" вошел в чат");
        }catch(IOException e){
            //закрыть ридеры и райтеры
        }
    }
    @Override
    public void run() {
        while (socket.isConnected()){
            try{
                message = reader.readLine();
                sendToEveryone(message);
            }catch (IOException e){//ошибка возникнет если
                closingTools(socket,writer,reader);
                break;
            }
        }
    }

    public void sendToEveryone(String message){
        for(ClientConnectionRunnable client : connectedClients){
            try{
                if(!client.equals(this)){
                    client.writer.write(message);
                    client.writer.newLine();
                    client.writer.flush();

                }
            }catch(IOException e){
                closingTools(socket,writer,reader);
            }
        }
    }

    public void disconnectClient(){
        connectedClients.remove(this);
        sendToEveryone("---"+userName+" вышел из чата");
    }

    public void closingTools(Socket socket, BufferedWriter writer, BufferedReader reader){
        disconnectClient();//удаляем из списка клиентов
        //закрываем всё открытое
        try {
            if (socket != null) {
                socket.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
