package com.ait.android.chess.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ait.android.chess.R;

/**
 * Created by ConorBelfield on 11/29/17.
 */

public class ChessBoardView extends View {

    private Paint paintBgBlack;
    private Paint paintBgWhite;
    private Paint paintLine;

    public ChessBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

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
        drawGameArea(canvas);

        // draw players
        //drawPlayers(canvas);

        //canvas.drawText("HELLO", 30, 100, paintText);

        //canvas.drawBitmap(bitmapBg, 0, 0, null);
    }

    private void drawGameArea(Canvas canvas) {
        // border
        //canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        // White Squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    canvas.drawRect((i) * getWidth() / 8, (j) * getWidth() / 8,
                            // If this isn't working, change 1 to i+1/j+1. Not sure if it's end location or width
                            (i+1) * getWidth() / 8, (1+j) * getHeight() / 8, paintBgWhite);
                }
            }
        }
    }
}
