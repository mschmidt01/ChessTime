package com.mikastamm.chesstime.Game.Figures;

import android.graphics.Point;

import com.mikastamm.chesstime.R;

public class Pawn extends Figure {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public int getDrawableResourceId() {
        if(isWhite)
        {
            return R.drawable.ic_w_pawn;
        }
        else
        {
            return R.drawable.ic_b_pawn;
        }
    }

    @Override
    public FigureMovePattern[] getValidMovePositions() {
        final int movePatternCount = 3;
        FigureMovePattern[] moves = new FigureMovePattern[movePatternCount];

        if(isWhite)
        {
            moves[0] = new FigureMovePattern(new Point(0,-1), false, false, false);
            moves[1] = new FigureMovePattern(new Point(1,-1), false, true, false);
            moves[2] = new FigureMovePattern(new Point(-1,-1), false, true, false);
        }
        else{
            moves[0] = new FigureMovePattern(new Point(0,1), false, false, false);
            moves[1] = new FigureMovePattern(new Point(1,1), false, true, false);
            moves[2] = new FigureMovePattern(new Point(-1,1), false, true, false);
        }



        return moves;
    }
}
