package com.ait.android.chess.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ait.android.chess.ChessModel;
import com.ait.android.chess.R;


/**
 * Created by ConorBelfield on 11/29/17.
 */

public class ChessBoardView extends View {

    private Paint paintBgBlack;
    private Paint paintBgWhite;
    private Paint paintBgBlue;
    private Paint paintBgYellow;
    private Paint paintLine;
    private int[][] squareColors = new int[8][8];

    private Bitmap bitmapWBishop;
    private Bitmap bitmapBBishop;
    private Bitmap bitmapWKing;
    private Bitmap bitmapBKing;
    private Bitmap bitmapWKnight;
    private Bitmap bitmapBKnight;
    private Bitmap bitmapWPawn;
    private Bitmap bitmapBPawn;
    private Bitmap bitmapWQueen;
    private Bitmap bitmapBQueen;
    private Bitmap bitmapWRook;
    private Bitmap bitmapBRook;

    public ChessBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                squareColors[i][j] = 0;
            }
        }
        paintBgBlack = new Paint();
        paintBgBlack.setColor(Color.DKGRAY);
        paintBgBlack.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.BLACK);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(20);

        paintBgWhite = new Paint();
        paintBgWhite.setColor(Color.WHITE);
        paintBgWhite.setStyle(Paint.Style.FILL);

        paintBgBlue = new Paint();
        paintBgBlue.setColor(Color.BLUE);
        paintBgBlue.setStyle(Paint.Style.FILL);

        paintBgYellow = new Paint();
        paintBgYellow.setColor(Color.YELLOW);
        paintBgYellow.setStyle(Paint.Style.FILL);

        //bitmapBg = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        bitmapWBishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        bitmapBBishop = BitmapFactory.decodeResource(getResources(), R.drawable.black_bishop);
        bitmapWKing = BitmapFactory.decodeResource(getResources(), R.drawable.white_king);
        bitmapBKing = BitmapFactory.decodeResource(getResources(), R.drawable.black_king);
        bitmapWKnight = BitmapFactory.decodeResource(getResources(), R.drawable.white_knight);
        bitmapBKnight = BitmapFactory.decodeResource(getResources(), R.drawable.black_knight);
        bitmapWPawn = BitmapFactory.decodeResource(getResources(), R.drawable.white_pawn);
        bitmapBPawn = BitmapFactory.decodeResource(getResources(), R.drawable.black_pawn);
        bitmapWQueen = BitmapFactory.decodeResource(getResources(), R.drawable.white_queen);
        bitmapBQueen = BitmapFactory.decodeResource(getResources(), R.drawable.black_queen);
        bitmapWRook = BitmapFactory.decodeResource(getResources(), R.drawable.white_rook);
        bitmapBRook = BitmapFactory.decodeResource(getResources(), R.drawable.black_rook_backup);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //bitmapBg = Bitmap.createScaledBitmap(bitmapBg, getWidth(), getHeight(), false);
        bitmapWBishop = Bitmap.createScaledBitmap(bitmapWBishop, getWidth()/8,
                getHeight()/8, false);
        bitmapBBishop = Bitmap.createScaledBitmap(bitmapBBishop, getWidth()/8,
                getHeight()/8, false);
        bitmapWKing = Bitmap.createScaledBitmap(bitmapWKing, getWidth()/8,
                getHeight()/8, false);
        bitmapBKing = Bitmap.createScaledBitmap(bitmapBKing, getWidth()/8,
                getHeight()/8, false);
        bitmapWKnight = Bitmap.createScaledBitmap(bitmapWKnight, getWidth()/8,
                getHeight()/8, false);
        bitmapBKnight = Bitmap.createScaledBitmap(bitmapBKnight, getWidth()/8,
                getHeight()/8, false);
        bitmapWPawn = Bitmap.createScaledBitmap(bitmapWPawn, getWidth()/8,
                getHeight()/8, false);
        bitmapBPawn = Bitmap.createScaledBitmap(bitmapBPawn, getWidth()/8,
                getHeight()/8, false);
        bitmapWQueen = Bitmap.createScaledBitmap(bitmapWQueen, getWidth()/8,
                getHeight()/8, false);
        bitmapBQueen = Bitmap.createScaledBitmap(bitmapBQueen, getWidth()/8,
                getHeight()/8, false);
        bitmapWRook = Bitmap.createScaledBitmap(bitmapWRook, getWidth()/8,
                getHeight()/8, false);
        bitmapBRook = Bitmap.createScaledBitmap(bitmapBRook, getWidth()/8,
                getHeight()/8, false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw background
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBgBlack);

        // draw board
        drawGameArea(canvas, squareColors);

        // draw players
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackPawn) {
                    canvas.drawBitmap(bitmapBPawn,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackRook) {
                    canvas.drawBitmap(bitmapBRook,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackBishop) {
                    canvas.drawBitmap(bitmapBBishop,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackKnight) {
                    canvas.drawBitmap(bitmapBKnight,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackQueen) {
                    canvas.drawBitmap(bitmapBQueen,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackKing) {
                    canvas.drawBitmap(bitmapBKing,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whitePawn) {
                    canvas.drawBitmap(bitmapWPawn,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whiteRook) {
                    canvas.drawBitmap(bitmapWRook,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whiteKnight) {
                    canvas.drawBitmap(bitmapWKnight,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whiteBishop) {
                    canvas.drawBitmap(bitmapWBishop,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whiteQueen) {
                    canvas.drawBitmap(bitmapWQueen,i*getWidth()/8,j*getHeight()/8, null);
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whiteKing) {
                    canvas.drawBitmap(bitmapWKing,i*getWidth()/8,j*getHeight()/8, null);
                }

            }
        }


        //drawPlayers(canvas);

        //canvas.drawText("HELLO", 30, 100, paintText);

        //canvas.drawBitmap(bitmapBg, 0, 0, null);
    }

    private static final int SELECT = 100;
    private static final int MOVE = 101;
    private int nextTouch = SELECT;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int tX = ((int) event.getX()) / (getWidth() / 8);
            int tY = ((int) event.getY()) / (getHeight() / 8);

            if (nextTouch == SELECT) {
                if (ChessModel.getInstance().getFieldContent(tX, tY) != ChessModel.EMPTY) {
                    squareColors[tX][tY] = ChessModel.YELLOW;


                    // Black pawn
                    if (ChessModel.getInstance().getFieldContent(tX, tY) == ChessModel.blackPawn) {
                        if (ChessModel.getInstance().getFieldContent(tX, tY - 1) == ChessModel.EMPTY) {
                            squareColors[tX][tY - 1] = ChessModel.BLUE;
                            if (tY == 7 && ChessModel.getInstance().getFieldContent(tX, tY - 2) == ChessModel.EMPTY) {
                                squareColors[tX][tY - 2] = ChessModel.BLUE;
                            }
                        }
                        if (tX > 0 && ChessModel.getInstance().getFieldContent(tX - 1, tY - 1) != ChessModel.EMPTY) {
                            squareColors[tX - 1][tY - 1] = ChessModel.BLUE;
                        }
                        if (tX < 7 && ChessModel.getInstance().getFieldContent(tX + 1, tY - 1) != ChessModel.EMPTY) {
                            squareColors[tX + 1][tY - 1] = ChessModel.BLUE;
                        }
                    }

                    // White pawn
                    else if (ChessModel.getInstance().getFieldContent(tX, tY) == ChessModel.whitePawn) {
                        if (ChessModel.getInstance().getFieldContent(tX, tY - 1) == ChessModel.EMPTY) {
                            squareColors[tX][tY + 1] = ChessModel.BLUE;
                            if (tY == 7 && ChessModel.getInstance().getFieldContent(tX, tY - 2) == ChessModel.EMPTY) {
                                squareColors[tX][tY + 2] = ChessModel.BLUE;
                            }
                        }
                        if (tX < 7 && ChessModel.getInstance().getFieldContent(tX + 1, tY + 1) != ChessModel.EMPTY) {
                            squareColors[tX + 1][tY + 1] = ChessModel.BLUE;
                        }
                        if (tX > 0 && ChessModel.getInstance().getFieldContent(tX - 1, tY + 1) != ChessModel.EMPTY) {
                            squareColors[tX - 1][tY + 1] = ChessModel.BLUE;
                        }
                    }

                    else {
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                if (i != tX || j != tY)
                                    squareColors[i][j] = ChessModel.BLUE;
                            }
                        }
                    }
//
//                    // White Rook
//                    if (ChessModel.getInstance().getFieldContent(tX, tY) == ChessModel.whiteRook) {
//                        for (int i = -1; i < 2; i++) {
//                            for (int j = -1; j < 2; j++) {
//                                if (i != 0 || j != 0) {
//                                    int x = tX;
//                                    int y = tY;
//                                    while (x + i < 8 && x + i > -1
//                                            && y + j < 8 && y + j > -1
//                                            && ChessModel.getInstance().getFieldContent(x + i, y + j)
//                                            != ChessModel.EMPTY) {
//                                        x += i;
//                                        y += j;
//
//                                        squareColors[x][y] = ChessModel.BLUE;
//
//                                    }
//                                    if (x + i < 8 && x + i > -1
//                                            && y + j < 8 && y + j > -1
//                                            && ChessModel.getInstance().getFieldContent(x + i, y + j)
//                                            > 5)  {
//                                        squareColors[x + i][y + j] = ChessModel.BLUE;
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    // Black Rook
//                    if (ChessModel.getInstance().getFieldContent(tX, tY) == ChessModel.blackRook) {
//                        for (int i = -1; i < 2; i++) {
//                            for (int j = -1; j < 2; j++) {
//                                if (i != 0 || j != 0) {
//                                    int x = tX;
//                                    int y = tY;
//                                    while (x + i < 8 && x + i > -1
//                                            && y + j < 8 && y + j > -1
//                                            && ChessModel.getInstance().getFieldContent(x + i, y + j)
//                                            != ChessModel.EMPTY) {
//                                        x += i;
//                                        y += j;
//
//                                        squareColors[x][y] = ChessModel.BLUE;
//
//                                    }
//                                    if (x + i < 8 && x + i > -1
//                                            && y + j < 8 && y + j > -1
//                                            && ChessModel.getInstance().getFieldContent(x + i, y + j)
//                                            < 6)  {
//                                        squareColors[x + i][y + j] = ChessModel.BLUE;
//                                    }
//                                }
//                            }
//                        }
//                    }

                }
                nextTouch = MOVE;

            }

            else {
                int toMoveX = -1;
                int toMoveY = -1;
                for (int i = 0; i < 8; i ++) {
                    for (int j = 0; j < 8; j++) {
                        if (squareColors[i][j] == ChessModel.YELLOW) {
                            toMoveX = i;
                            toMoveY = j;
                        }
                    }
                }

                ChessModel.getInstance().setFieldContent(tX, tY, ChessModel.getInstance().getFieldContent(toMoveX, toMoveY));

                resetSquareColors();

                nextTouch = SELECT;
            }
        }
        invalidate();

        return super.onTouchEvent(event);
    }

    private void resetSquareColors() {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                squareColors[i][j] = 0;
            }
        }
    }

    private void drawGameArea(Canvas canvas, int [][] specialColors) {
        // border
        //canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        // White Squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (specialColors[i][j] == ChessModel.BLUE)
                canvas.drawRect((i) * getWidth() / 8, (j) * getWidth() / 8,
                        (i+1) * getWidth() / 8, (1+j) * getHeight() / 8, paintBgBlue);
                else if (specialColors[i][j] == ChessModel.YELLOW)
                canvas.drawRect((i) * getWidth() / 8, (j) * getWidth() / 8,
                        (i+1) * getWidth() / 8, (1+j) * getHeight() / 8, paintBgYellow);
                else if ((i + j) % 2 != 0) {
                    canvas.drawRect((i) * getWidth() / 8, (j) * getWidth() / 8,
                            (i+1) * getWidth() / 8, (1+j) * getHeight() / 8, paintBgWhite);
                }
            }
        }
    }
}
