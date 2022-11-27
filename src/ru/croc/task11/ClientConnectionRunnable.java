package ru.croc.task11;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientConnectionRunnable implements Runnable {

    //чтобы хранить всех подключенных клиентов, и иметь возможность разослать всем сообщение
    public static List<ClientConnectionRunnable> connectedClients = Collections.synchronizedList(new ArrayList<>());

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    private String userName;
    private String message;

    public ClientConnectionRunnable(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName = reader.readLine();
            connectedClients.add(this);
            sendToEveryone("---" + this.userName + " вошел в чат");
        } catch (IOException e) {
            closingTools(socket, writer, reader);
        }
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                message = reader.readLine();
                sendToEveryone(message);
            } catch (IOException e) {
                closingTools(socket, writer, reader);
                break;
            }
        }
    }

    public void sendToEveryone(String message) {
        for (ClientConnectionRunnable client : connectedClients) {
            try {
                if (!client.equals(this)) {
                    client.writer.write(message);
                    client.writer.newLine();
                    client.writer.flush();

                }
            } catch (IOException e) {
                closingTools(socket, writer, reader);
            }
        }
    }

    public void disconnectClient() {
        connectedClients.remove(this);
        sendToEveryone("---" + userName + " вышел из чата");
    }

    public void closingTools(Socket socket, BufferedWriter writer, BufferedReader reader) {
        disconnectClient();//удаляем из списка клиентов
        //закрываем всё открытое для этого клиента
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
