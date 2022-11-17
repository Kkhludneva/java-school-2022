package ru.croc.task7;


import ru.croc.task7.chessExceptions.IllegalMoveException;
import ru.croc.task7.chessExceptions.IllegalPositionException;

import java.util.ArrayList;
import java.util.Scanner;

public class Task7 {
    public static boolean isItKnightMove(ArrayList<ChessPosition> posSequence) throws IllegalMoveException {

        for(int i = 0; i < posSequence.size()-1; i++){
            ChessPosition cur = posSequence.get(i);
            ChessPosition next = posSequence.get(i+1);
            int deltaX = Math.abs(cur.getX()- next.getX());
            int deltaY = Math.abs(cur.getY()- next.getY());
            if ( ((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1))==false ) {
                throw new IllegalMoveException("Конь так не ходит: ",cur,next);
                //return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        //ПРОВЕРКА конструкторов и parse
        /*try{
            ChessPosition pos = new ChessPosition(0,1);
            System.out.println("Через конструктор: "+ pos);
            String s = "b5";
            ChessPosition pos2 = ChessPosition.parse(s);
            System.out.println("Через фабричный метод: " + pos2);
        }catch(IllegalPositionException e){
            System.err.println(e.getMessage());
        }*/

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите последовательность позиций: ");
        String s = "";
        if (scanner.hasNextLine()) {
            s = scanner.nextLine();
        }
        //разбили введенную строку на последовательность ходов
        String[] strPositions = s.split("\\s");
        //создали и заполнили массив позиций
        ArrayList<ChessPosition> positions = new ArrayList<>();

        for (int i = 0; i < strPositions.length; i++){
            try{
                ChessPosition pos = ChessPosition.parse(strPositions[i]);
                positions.add(pos);
            }catch(IllegalPositionException ex){
                System.err.println(ex.getMessage());
            }
        }
        // проверяем ходит ли так конь
        try{
            isItKnightMove(positions);
            System.out.println("Конь так ходит:)");
        }catch (IllegalMoveException ex){
            System.err.println(ex.getMessage());
        }


    }

}
