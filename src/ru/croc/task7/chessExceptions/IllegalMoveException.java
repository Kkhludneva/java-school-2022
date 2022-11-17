package ru.croc.task7.chessExceptions;

import ru.croc.task7.ChessPosition;

public class IllegalMoveException extends Exception{
    private ChessPosition currentPosition;
    private ChessPosition nextPosition;
    private String message;

    public IllegalMoveException(ChessPosition cur, ChessPosition next){
        this.currentPosition = cur;
        this.nextPosition = next;
    }

    public IllegalMoveException(String m,ChessPosition cur, ChessPosition next){
        super(m+" "+cur+"->"+next);
        this.currentPosition = cur;
        this.nextPosition = next;
        this.message = m +""+cur+" -> "+next;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
