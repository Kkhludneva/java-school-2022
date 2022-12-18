package ru.croc.individualProject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class StudentClient {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String userName;
    private String message = "";
    private int difficultyLevel;

    public StudentClient(Socket socket, String username, int level) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName = username;
            this.difficultyLevel = level;
        } catch (IOException e) {
            closingTools(socket, writer, reader);
        }
    }

    private void sendAnswer() {
        try {
            writer.write(userName);
            writer.newLine();
            writer.flush();

            writer.write(difficultyLevel + "");
            writer.newLine();
            writer.flush();

            Scanner source = new Scanner(System.in);
            while (socket.isConnected() && !message.equalsIgnoreCase("EXIT")) {
                message = source.nextLine();
                writer.write(message);
                writer.newLine();
                writer.flush();
            }
            closingTools(socket, writer, reader);
        } catch (IOException e) {
            closingTools(socket, writer, reader);
        }
    }

    private void waitForNewTask() {
        Thread listening = new Thread(() -> {
            while (!socket.isClosed()) {
                try {
                    String gottenMessage = reader.readLine();
                    System.out.println(gottenMessage);
                } catch (IOException e) {
                    closingTools(socket, writer, reader);
                }
            }
        });
        listening.start();
    }

    private void closingTools(Socket socket, BufferedWriter writer, BufferedReader reader) {
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


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.println("""
                Choose a difficulty level and enter it's number:
                1 - easy
                2 - medium
                3 - hard""");
        int level = 0;
        while (level < 1 || level > 3) {
            try {
                level = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                level = 0;
            }
            if (level < 1 || level > 3){
                System.err.println("Incorrect level, try again:");
            }
        }
        try {
            Socket socket = new Socket("localhost", 2021);
            StudentClient client = new StudentClient(socket, userName, level);
            client.waitForNewTask();//это будет работать в отдельном потоке, поэтому сможем отправлять сообщения
            client.sendAnswer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
