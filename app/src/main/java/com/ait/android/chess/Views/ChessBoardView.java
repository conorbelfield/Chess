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
    private int[][] squareColors;

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
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //bitmapBg = Bitmap.createScaledBitmap(bitmapBg, getWidth(), getHeight(), false);
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
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackRook) {
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackBishop) {
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackKnight) {
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackQueen) {
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.blackKing) {
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whitePawn) {
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whiteRook) {
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whiteKnight) {
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whiteBishop) {
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whiteQueen) {
                    // place piece. use 64 image views? use 32 that change location?
                }
                else if (ChessModel.getInstance().getFieldContent(i,j) == ChessModel.whiteKing) {
                    // place piece. use 64 image views? use 32 that change location?
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
                    if (ChessModel.getInstance().getFieldContent(tX, tY) == ChessModel.whitePawn) {
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

                    // White Rook
                    if (ChessModel.getInstance().getFieldContent(tX, tY) == ChessModel.whiteRook) {
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if (i != 0 || j != 0) {
                                    int x = tX;
                                    int y = tY;
                                    while (x + i < 8 && x + i > -1
                                            && y + j < 8 && y + j > -1
                                            && ChessModel.getInstance().getFieldContent(x + i, y + j)
                                            != ChessModel.EMPTY) {
                                        x += i;
                                        y += j;

                                        squareColors[x][y] = ChessModel.BLUE;

                                    }
                                    if (x + i < 8 && x + i > -1
                                            && y + j < 8 && y + j > -1
                                            && ChessModel.getInstance().getFieldContent(x + i, y + j)
                                            > 5)  {
                                        squareColors[x + i][y + j] = ChessModel.BLUE;
                                    }
                                }
                            }
                        }
                    }

                    // Black Rook
                    if (ChessModel.getInstance().getFieldContent(tX, tY) == ChessModel.blackRook) {
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if (i != 0 || j != 0) {
                                    int x = tX;
                                    int y = tY;
                                    while (x + i < 8 && x + i > -1
                                            && y + j < 8 && y + j > -1
                                            && ChessModel.getInstance().getFieldContent(x + i, y + j)
                                            != ChessModel.EMPTY) {
                                        x += i;
                                        y += j;

                                        squareColors[x][y] = ChessModel.BLUE;

                                    }
                                    if (x + i < 8 && x + i > -1
                                            && y + j < 8 && y + j > -1
                                            && ChessModel.getInstance().getFieldContent(x + i, y + j)
                                            < 6)  {
                                        squareColors[x + i][y + j] = ChessModel.BLUE;
                                    }
                                }
                            }
                        }
                    }

                }


            }
        }

        return super.onTouchEvent(event);
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
