package ru.croc.individualProject;

import ru.croc.individualProject.dataBaseConnection.DataBaseConnector;
import ru.croc.individualProject.testProcessing.StudentTestRunnable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TeacherServer {
    public static void main(String[] args)  {

        try{
            ServerSocket serverSocket= new ServerSocket(2021);
            System.out.println("Server Started");
            //создаём БД, в которой будем сохранять статистику
            Connection dbConnection = DriverManager.getConnection("jdbc:h2:mem:~/test_results");
            DataBaseConnector dbConnector = new DataBaseConnector(dbConnection);
            dbConnector.createDataBase();

            //ЗАКРЫТЬ
            while(!serverSocket.isClosed()) {
                //ждём подключения
                Socket socket = serverSocket.accept();
                //каждому клиенту своё подключение к БД
                Connection newConnection = DriverManager.getConnection("jdbc:h2:mem:~/test_results");
                DataBaseConnector threadConnector = new DataBaseConnector(newConnection);
                //каждое подключение обрабатывается в отдельном потоке
                Thread newStudent = new Thread(new StudentTestRunnable(socket,threadConnector));
                newStudent.start();
            }
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }

}
