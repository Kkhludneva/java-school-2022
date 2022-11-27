package ru.croc.task11;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private  Socket socket;
    private  BufferedReader reader;
    private  BufferedWriter writer;
    private  String userName;

    public Client(Socket socket, String username){
        try{
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName = username;
        }catch (IOException e){
            closingTools(socket,writer,reader);
        }
    }

    public void sendMessage(){
        try {
            writer.write(userName);
            writer.newLine();
            writer.flush();

            Scanner source = new Scanner(System.in);
            while(socket.isConnected()){
                String message = source.nextLine();
                writer.write(userName+": "+ message);
                writer.newLine();
                writer.flush();
            }
        }catch (IOException e){
            closingTools(socket,writer,reader);
        }
    }

    public void waitForMessages(){
        Thread listening = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!socket.isClosed()){
                    try {
                        String gottenMessage = reader.readLine();
                        System.out.println(gottenMessage);
                    }catch (IOException e){
                        closingTools(socket,writer,reader);
                    }
                }
            }
        });
        listening.start();
    }

    public void closingTools(Socket socket, BufferedWriter writer, BufferedReader reader){
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя пользователя: ");
        String userName = scanner.nextLine();
        try {
            Socket socket = new Socket("localhost", 2021);
            Client client = new Client(socket,userName);
            client.waitForMessages();//это будет работать в отдельном потоке, поэтому сможем отправлять сообщения
            client.sendMessage();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
