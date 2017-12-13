package com.ait.android.chess.Data;

/**
 * Created by Ryan on 12/13/2017.
 */

public class Game {

    private String uidB;
    private String uidW;
    private ChessModel board;

    public Game(){
    }

    public Game(String uidB, String uidW, ChessModel board) {
        this.uidB = uidB;
        this.uidW = uidW;
        this.board = board;
    }

    public String getUidB() {
        return uidB;
    }

    public void setUidB(String uidB) {
        this.uidB = uidB;
    }

    public String getUidW() {
        return uidW;
    }

    public void setUidW(String uidW) {
        this.uidW = uidW;
    }

    public ChessModel getBoard() {
        return board;
    }

    public void setBoard(ChessModel board) {
        this.board = board;
    }
}
