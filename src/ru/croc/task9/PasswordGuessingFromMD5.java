package ru.croc.task9;


import java.util.Arrays;

import static ru.croc.task9.HashTools.hashPassword;

public class PasswordGuessingFromMD5 {
    private static final char[] PASSWORD_DIGITS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final char PASSWORD_LENGTH = 7;

    private static String passwordHash;
    private static int numberOfThreads;


    public static void tryToGuess(int numberOfThreads, String passwordHash){
        /*Записываем хэш в static поле(чтобы во время перебора можно было к нему обращаться
        * поочередно запускаем потоки
        */
        PasswordGuessingFromMD5.passwordHash = passwordHash;
        PasswordGuessingFromMD5.numberOfThreads = numberOfThreads;
        for (int i = 0; i < numberOfThreads; i++){
            //новый offset
            int offsetForThread = (PASSWORD_DIGITS.length/numberOfThreads)*i;
            //System.out.println(offsetForThread);
            Thread newThread = new Thread(new GuessingThread(offsetForThread));
            newThread.start();
        }
    }

    private static class GuessingThread implements Runnable {
        //в зависимости от номера потока введём сдвиг по буквам
        //он повлияет на формирование строки(потенциального пароля) из численной перестановки(см ниже)
        private int offsetOfRangeOfSymbols;
        //volatile поле, чтобы остановить потоки, когда найдем пароль
        private volatile static boolean passwordFond = false;
        //для закрытия потока, когда он отработал свою часть
        private boolean cancelled = false;

        public void cancel() {
            cancelled = true;
        }


        //конструктор для каждого потока, чтобы записать, какой будет сдвиг по буквам
        public GuessingThread (int numberOfThread){
            offsetOfRangeOfSymbols = numberOfThread;
        }

        @Override
        public void run() {
            //если не нашли пароль, запускаем поток
            if(!passwordFond) {
                //функция, которая формирует из 26 букв алфавита группы по 7 букв
                //там же будет проверка на совпадение с данным хэшем
                //---Буду передавать в функцию тот поток, который её вызывает
                //чтобы закрыть именно его, когда он отработает свою часть
                permutationsFromNToK(PASSWORD_DIGITS.length, PASSWORD_LENGTH, offsetOfRangeOfSymbols, this);
            }
        }

    }

    public static void permutationsFromNToK(int n, int k, int offset, GuessingThread cause){
        /*алгоритм перестановок из n чисел по k позициям
    (повторения элементов возможны)*/
        int[] indexes = new int[k];
        //проверяем, не найден ли пароль и формируем размещения
        while (!GuessingThread.passwordFond && !cause.cancelled) {
                //System.out.println(Arrays.toString(indexes) + "  ----  "+permutationToString(offset, indexes,cause)+" -- "+offset);

                //permutationToString(offset, indexes) - формирует из массива индексов(размещения) строку
                //тут сравнивает хэши, если нашли, что хотели, останавливаем всё
                if (hashPassword(permutationToString(offset, indexes, cause)).equals(passwordHash)) {
                    GuessingThread.passwordFond = true;//сообщаем другим потокам
                    System.out.println("PASSWORD IS : " + permutationToString(offset, indexes, cause));
                    return;
                }
                //Формирование новой перестановки
                int i = k - 1;
                for (; i >= 0; i--) {
                    if (indexes[i] < n - 1) {
                        break;
                    }
                }
                if (i < 0) {
                    break;
                }
                indexes[i] += 1;
                i += 1;
                for (; i < k; ) {
                    indexes[i] = 0;
                    i += 1;
                }

            }
    }

    //преобразуем массив чисел в строчку(которая может оказаться искомым паролем)
    //берём символы из алфавита, учитываем сдвиг
    public static String permutationToString(int offset, int[] indexes,GuessingThread cause){
        String potentialPassword = "";
        /*проверяем, не перешли ли в зону работы другого потока
        * и для последнего потока: зона его работы будет больше, чем у остальных(число букв может не делиться числом потоков)
        * проверим второе условие, чтобы не выйти из последнего потока раньше, чем нужно*/
        if (indexes[0]==offset + PASSWORD_DIGITS.length/numberOfThreads &&
                (PASSWORD_DIGITS.length-indexes[0])>PASSWORD_DIGITS.length/numberOfThreads){
                     //System.out.println("This thread completed at "+ Arrays.toString(indexes)+ "offset was "+offset);
                    cause.cancel();
        }
        for (int i = 0; i < indexes.length; i++){
            potentialPassword+=PASSWORD_DIGITS[(indexes[i]+offset)%PASSWORD_DIGITS.length];
        }
        return potentialPassword;
    }
}
