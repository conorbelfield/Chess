package com.ait.android.chess;

/**
 * Created by ConorBelfield on 12/6/17.
 */

public class ChessModel {

    private static ChessModel chessModel = null;

    private ChessModel() {}

    public static ChessModel getInstance() {
        if (chessModel == null)
            return new ChessModel();
        return chessModel;
    }

    public static final int whitePawn = 0;
    public static final int whiteRook = 1;
    public static final int whiteKnight = 2;
    public static final int whiteBishop = 3;
    public static final int whiteQueen = 4;
    public static final int whiteKing = 5;
    public static final int blackPawn = 6;
    public static final int blackRook = 7;
    public static final int blackKnight = 8;
    public static final int blackBishop = 9;
    public static final int blackQueen = 10;
    public static final int blackKing = 11;
    public static final int EMPTY = 12;
    public static final int WHITE = 13;
    public static final int BLACK = 14;
    public static final int BLUE = 15;
    public static final int YELLOW = 16;

    private int[][] board = {
            {whiteRook, whiteKnight, whiteBishop, whiteQueen, whiteKing, whiteBishop, whiteKnight, whiteRook},
            {whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn},
            {blackRook, blackKnight, blackBishop, blackQueen, blackKing, blackBishop, blackKnight, blackRook}
    };

    private int nextPlayer = WHITE;

    public void switchNextPlayer() {
        nextPlayer = (nextPlayer == BLACK) ? WHITE : BLACK;
    }

    public int getFieldContent(int x, int y) {
        return board[x][y];
    }

    public void setFieldContent(int x, int y, int content) {
        board[x][y] = content;
    }

    public int getNextPlayer() {
        return nextPlayer;
    }

    public void resetGame() {

        board = new int[][] {
                {whiteRook, whiteKnight, whiteBishop, whiteQueen, whiteKing, whiteBishop, whiteKnight, whiteRook},
                {whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn},
                {blackRook, blackKnight, blackBishop, blackQueen, blackKing, blackBishop, blackKnight, blackRook}
        };

        nextPlayer = WHITE;
    }
}
