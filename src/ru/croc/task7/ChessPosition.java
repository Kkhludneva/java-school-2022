package ru.croc.task7;

import ru.croc.task7.ChessExceptions.IllegalPositionException;

public class ChessPosition {
    /* X,Y от 0 до 7, но при выводе и вводе учитываю,
    что пользователь вводит от 0 до 8 (+-1, при вводе/выводе)*/
    private int x;
    private int y;
    //чтобы первую координату фигуры выводить буквой
    private static final String axisXLetters = "abcdefgh";

    //getter-ы
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    //В setter-ax проверяем, чтобы x,y были из нужного диапазона
    public void setX(int x) throws IllegalPositionException{
        if (positionIsValid(x)==true) {
            this.x = x;
        }else{
            throw new IllegalPositionException("Попытка некорректного изменения координаты");
        }
    }
    public void setY(int y) throws IllegalPositionException{
        if (positionIsValid(y)==true) {
            this.y = y;
        }else{
            throw new IllegalPositionException("Попытка некорректного изменения координаты");
        }
    }

    //проверка на попадание координаты в нужный диапазон
    private static boolean positionIsValid(int n){
        if (n>=0 && n<8){//от 0 до 7
            return true;
        }
        else{
            return false;
        }
    }

    public ChessPosition(){
        this.x = 0;
        this.y = 0;
    }
    public ChessPosition(int x, int y) throws IllegalPositionException{
        if (positionIsValid(x)==false && positionIsValid(y)==false){
            throw new IllegalPositionException();
        }
        else if (positionIsValid(x)==false){
            throw new IllegalPositionException("Первая координата введена неверно");
        }else if (positionIsValid(y)==false){
            throw new IllegalPositionException("Вторая координата введена неверно");
        }else {
            this.x = x;
            this.y = y;
        }
    }

    //при создании объекта из строки тоже может возникнуть ошибка
    static ChessPosition parse (String position) throws IllegalPositionException{
        ChessPosition rslt = new ChessPosition();
        //на случай если первой координатой введена заглавная буква
        position = position.toLowerCase();
        if (position.length() != 2){
            throw new IllegalPositionException("В строке должно быть два символа - координаты фигуры");
        }else{
            //выделили координаты из строки
              Character xLetter = position.charAt(0);
              int yValue = Character.getNumericValue(position.charAt(1)) - 1;//учитываем, что Y ввели от 1 до 8
            //проверяем корректность
              if (axisXLetters.indexOf(xLetter) == -1 && positionIsValid(yValue)==false) {
                  throw new IllegalPositionException();
              }
              else if (axisXLetters.indexOf(xLetter) == -1 ) {
                  throw new IllegalPositionException("Первая координата должна быть в диапазоне A - H (англ. алфавит)");
              }
              else if (positionIsValid(yValue)==false) {
                  throw new IllegalPositionException("Вторая координата должна быть у диапазоне от 1 до 8");
              }//если всё хорошо, создаём объект
              else {
                  rslt.setX(axisXLetters.indexOf(xLetter));
                  rslt.setY(yValue);
              }
        }
        return rslt;
    }

    @Override
    public String toString() {
        String s = axisXLetters.charAt(this.x)+""+(this.y+1);
        return s;
    }
}
