package com.mikastamm.chesstime;

import android.app.Application;

import com.mikastamm.chesstime.Game.Logic.ChessGamesManager;
import com.mikastamm.chesstime.Game.Logic.GamesManager;

public class ChessTimeApplication extends Application {
    public static ChessGamesManager gamesManager;

    @Override
    public void onCreate() {
        super.onCreate();
        gamesManager = new ChessGamesManager(this);
    }
}
