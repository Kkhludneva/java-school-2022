package ru.croc.task9;


import static ru.croc.task9.HashTools.hashPassword;

public class PasswordGuessingFromMD5 {
    private static final char[] PASSWORD_DIGITS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final char PASSWORD_LENGTH = 7;

    private static String passwordHash;


    public static void tryToGuess(int numberOfThreads, String passwordHash){
        /*Записываем хэш в static поле(чтобы во время перебора можно было к нему обращаться
        * поочередно запускаем потоки
        */
        PasswordGuessingFromMD5.passwordHash = passwordHash;
        for (int i = 0; i < numberOfThreads; i++){
            Thread newThread = new Thread(new GuessingThread(i));
            newThread.start();
        }
    }

    private static class GuessingThread implements Runnable {
        //в зависимости от номера потока введём сдвиг по буквам
        //он повлияет на формирование строки(потенциального пароля) из численной перестановки(см ниже)
        private int offsetOfRangeOfSymbols;
        //volatile поле, чтобы остановить потоки, когда найдем пароль
        public volatile static boolean passwordFond = false;

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
                permutationsFromNToK(PASSWORD_DIGITS.length, PASSWORD_LENGTH, offsetOfRangeOfSymbols);
            }
        }

    }

    public static void permutationsFromNToK(int n, int k, int offset){
        /*алгоритм перестановок из n чисел по k позициям
    (повторения элементов возможны)*/
        int[] indexes = new int[k];
        //проверяем, не найден ли пароль и формируем размещения
        while (!GuessingThread.passwordFond) {
               // System.out.println(Arrays.toString(indexes) + "  ----  "+permutationToString(offset, indexes));

                //permutationToString(offset, indexes) - формирует из массива индексов(размещения) строку
                //тут сравнивает хэши, если нашли, что хотели, останавливаем всё
                if (hashPassword(permutationToString(offset, indexes)).equals(passwordHash)) {
                    GuessingThread.passwordFond = true;//сообщаем другим потокам
                    System.out.println("PASSWORD IS : " + permutationToString(offset, indexes));
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
    public static String permutationToString(int offset, int[] indexes){
        String potentialPassword = "";
        for (int i = 0; i < indexes.length; i++){
            potentialPassword+=PASSWORD_DIGITS[(indexes[i]+offset)%PASSWORD_DIGITS.length];
        }
        return potentialPassword;
    }
}
