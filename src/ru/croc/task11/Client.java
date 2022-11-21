package ru.croc.task11;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/*Не понимаю, как реализовать общение нескольких клиентов.
Наверное, нужно запускать на сервере по несколько потоков(по 1 под каждого клиента)
но я не понимаю, как это сделать (ещё подумаю сама и спрошу на паре)

На данный момент получилось сделать так:
есть 1 клиент, он отправляет сообщение на сервер
с сервера приходит ответ: какое сообщение было получено от клиента
если клиент отправляет сообщение bye, клиент завершает работу
* */

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1", 2021)){
            System.out.println("Connected to server");
            String s ="";
            Scanner scanner = new Scanner(System.in);
            while(!s.equals("bye")) {
                s = scanner.nextLine();
                ConnectionTools.writeLine(s, socket);
                String ans = ConnectionTools.readLine(socket);
                System.out.println(ans);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
