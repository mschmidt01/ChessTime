package com.mikastamm.chesstime.GUI.UserInterface;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.GUI.PresentationLogic.DefaultMenuPresenter;
import com.mikastamm.chesstime.GUI.PresentationLogic.IMenuPresenter;
import com.mikastamm.chesstime.GUI.PresentationLogic.GamesAdapter;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.PersistenceManager;
import com.mikastamm.chesstime.R;

public class MenuActivity extends AppCompatActivity implements MenuView {

    private IMenuPresenter presenter = new DefaultMenuPresenter();
    private GamesAdapter adapter;
    private boolean isRegistered;
    private ProgressDialog searchingGameSpinnerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ChessTime-PWtoken", PersistenceManager.getPlayerToken(this));
        setContentView(R.layout.activity_menu);
        registerPlayer();
        presenter.setView(this);

        presenter.onCreate();
        //Bind Search button to presenter
        Button searchButton = findViewById(R.id.btn_search_game);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchingGameSpinnerDialog = ProgressDialog.show(MenuActivity.this, "",
                        "Seaching game...", true);
                presenter.findGame();
            }
        });


        final ListView lvGames = findViewById(R.id.lv_current_games);
        adapter = new GamesAdapter(this, ChessTimeApplication.gamesManager.getAllGames());
        lvGames.setAdapter(adapter);
        lvGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game clickedGame = adapter.games[position];
                Intent openGameActivityIntent = new Intent(MenuActivity.this, GameActivity.class);
                openGameActivityIntent.putExtra("GameId", clickedGame.id);
                MenuActivity.this.startActivity(openGameActivityIntent);
            }
        });
    }

    private void registerPlayer() {
        String playerName = ChessTimeApplication.userManager.getPlayerName();
        if(playerName.equals("undefined")) {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(searchingGameSpinnerDialog != null)
            searchingGameSpinnerDialog.hide();
    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onStop(){
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.onStart();
    }



    @Override
    public Context getContext() {
        return this;
    }
}
