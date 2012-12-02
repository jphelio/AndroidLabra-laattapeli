package com.example.pallosokkelo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Highscore extends Activity{
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_highscore);
	    }
	 
	 public void onClick(View v){
		 startActivity(new Intent(this, Main.class));
	 }
}
