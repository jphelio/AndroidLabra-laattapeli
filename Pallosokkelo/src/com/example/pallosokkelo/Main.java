package com.example.pallosokkelo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Main extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClickHighscore(View v){
    	startActivity(new Intent(this, Highscore.class));
    }
    
    public void onClickNewGame(View v){
    	startActivity(new Intent(this, Game.class));
    }
}
