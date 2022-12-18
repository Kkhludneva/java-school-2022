package ru.croc.individualProject.dataBaseConnection;

import java.time.LocalDateTime;

public class StudentStatistic {
    private final String username;
    private final LocalDateTime startedTest;
    private final LocalDateTime finishedTest;
    private final int totalScore;

    public StudentStatistic(String userName, LocalDateTime start, LocalDateTime finish, int totalScore) {
        this.username = userName;
        this.startedTest = start;
        this.finishedTest = finish;
        this.totalScore = totalScore;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getStartedTest() {
        return startedTest;
    }

    public LocalDateTime getFinishedTest() {
        return finishedTest;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
