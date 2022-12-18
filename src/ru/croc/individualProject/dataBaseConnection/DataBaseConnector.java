package ru.croc.individualProject.dataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnector {
    private final Connection connection;
    private final StudentStatisticDao stDao;

    public DataBaseConnector(Connection connection){
        this.connection = connection;
        this.stDao = new StudentStatisticDao(connection);
    }
    public void createDataBase(){
        try {
            final Statement creatingTables = connection.createStatement();
            creatingTables.execute("create table statistics (userName varchar," +
                    " startedTest TIMESTAMP, finishedTest TIMESTAMP, totalScore integer);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStudentStatistic(StudentStatistic studentStatistic){
        stDao.create(studentStatistic);
    }

    public void printStatistics(){
        stDao.printAll();
    }

}
