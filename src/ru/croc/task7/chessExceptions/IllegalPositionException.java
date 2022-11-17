package ru.croc.task7.chessExceptions;

public class IllegalPositionException extends Exception{

    private String message;//чтобы кратко вывести сообщение об ошибке через .getMessage

    public IllegalPositionException(){
        super("Некорректный ввод координат фигуры");
        this.message = "Некорректный ввод координат фигуры";
    }

    //В случае, если хотим задать конкретное сообщение
    public IllegalPositionException(String message){
        super(message);
        this.message = message;

    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
