package ru.croc.individualProject.testProcessing;

//Подразумевается, что в приложение могут добавляться ещё тесты
//Для них будут нужны другие "проверятели", но во всех них заложена одна логика:
public interface Examiner {
    //формирование вопроса для вывода пользователю
    String generateQuestion();
    //проверяем правильность ответа
    boolean answerIsCorrect(String answer);

}
