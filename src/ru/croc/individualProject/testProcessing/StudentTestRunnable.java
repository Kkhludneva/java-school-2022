package ru.croc.individualProject.testProcessing;


import ru.croc.individualProject.dataBaseConnection.*;
import ru.croc.individualProject.verbProcessing.*;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.*;

public class StudentTestRunnable implements Runnable {

    public static final int NUMBER_OF_TASKS = 10;
    private static final List<Verb> allIrregularVerbs = new VerbParser
            ( new File("./src/ru/croc/individualProject/verbProcessing/irregularVerbs.txt")).parseToVerbs();
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String userName;
    private int difficultyLevel;
    private DataBaseConnector dbConnector;




    public StudentTestRunnable(Socket socket, DataBaseConnector dbConnector) {
        try {
            this.socket = socket;
            this.dbConnector = dbConnector;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName = reader.readLine();
            this.difficultyLevel = Integer.valueOf(reader.readLine());


        } catch (IOException e) {
            closingTools(this.socket, writer, reader);
        }
    }

    private void closingTools(Socket socket, BufferedWriter writer, BufferedReader reader) {
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

    private StudentStatistic testProcessing(){
        Random random = new Random();
        //выбираем глаголы, которые попадут в тест
        int[] chooseVerbs = random.ints(0, allIrregularVerbs.size())
                .distinct().limit(NUMBER_OF_TASKS).toArray();

        //засекаем начало тестирования
        LocalDateTime start = LocalDateTime.now();

        //будем хранить глаголы, в которых ошибка
        List<Verb> mistakes = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            //создаём обработчика задания
            IrregularVerbsExaminer examiner =
                    new IrregularVerbsExaminer(allIrregularVerbs.get(chooseVerbs[i]), difficultyLevel);
            try {
                //выводим задание
                writer.write((i + 1) + ". " + examiner.generateQuestion());
                writer.newLine();
                writer.flush();

                String answer = reader.readLine();

                //если ответ неверный, занесём глагол в список ошибок
                if (!examiner.answerIsCorrect(answer)) {
                    mistakes.add(allIrregularVerbs.get(chooseVerbs[i]));
                }
            } catch (IOException e) {
                closingTools(socket, writer, reader);
            }
        }
        //фиксируем время окончания теста
        LocalDateTime finish = LocalDateTime.now();
        //выводим результаты
        try {
            writer.write(resultsToString(mistakes));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int totalScore = NUMBER_OF_TASKS-mistakes.size();

        return new StudentStatistic(userName,start,finish,totalScore);
    }


    @Override
    public void run() {
        String nextAction;
        do {
            //Внутри метода testProcessing осуществляется вся логика тестирования,
            // возвращается только результат по ученику,
            // Добавляем запись о нём в БД, выводим накопившиеся данные о всех студентах в консоль сервера

            dbConnector.insertStudentStatistic(testProcessing());
            dbConnector.printStatistics();
            nextAction = "";
            while (!(nextAction.equals("AGAIN") || nextAction.equals("EXIT"))) {
                try {
                    writer.write("Enter \"AGAIN\" to try again or \"EXIT\" to finish:");
                    writer.newLine();
                    writer.flush();
                    nextAction = reader.readLine().toUpperCase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }while(nextAction.equals("AGAIN"));
    }

    private String resultsToString(List<Verb> mistakes) {
        String rslt = "Total score: " + (StudentTestRunnable.NUMBER_OF_TASKS - mistakes.size()) + "/" + StudentTestRunnable.NUMBER_OF_TASKS;
        if (!mistakes.isEmpty()) {
            rslt += "\nYou should repeat the verbs: \n";
            for (Verb verb : mistakes) {
                rslt += "-- " + verb.getFirstForm() + "\n";
            }
        } else {
            rslt += "\nGreat!";
        }
        return rslt;
    }
}
