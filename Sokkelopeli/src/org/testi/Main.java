package org.testi;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Main extends Activity implements SensorEventListener {
    private SensorManager manager;
    private Sensor accelerometer;
    private double ratioY;
    private double ratioX;
    private DrawView dv;
    private Pelimoottori moottori=new Pelimoottori(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        System.out.println(manager.getSensorList(Sensor.TYPE_ALL));
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        dv=new DrawView(this, moottori);
        dv.setBackgroundColor(Color.BLACK);
        setContentView(dv);
        
    }
    @Override
    protected void onResume() {
    	super.onResume();
    	manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
    	super.onPause();
    	manager.unregisterListener(this, accelerometer);    	
    }

  public void onAccuracyChanged(Sensor sensor, int accuracy) {
	  dv.invalidate();
  }
  
  @Override
  public void onConfigurationChanged(Configuration conf){
	  //ei tehd‰ mit‰‰n
  }

  public void onSensorChanged(SensorEvent event) {
      moottori.siirra(getSuunta(event));
      if(moottori.isFinished()){
    	  manager.unregisterListener(this, accelerometer);
      }
      paivita();
  } 
  private Suunta getSuunta(SensorEvent event){
	  float x=event.values[0];
	  float y=event.values[1];
	  if(Math.abs(x)<Math.abs(y)){
    	  if(Math.signum(y)<0){
    		  return Suunta.YLOS;
    	  }
    	  return Suunta.ALAS;
      }
	  if(Math.abs(x)>Math.abs(y)){
		  if(Math.signum(x)<0){
    		  return Suunta.OIKEA;
    	  }
    	  return Suunta.VASEN;
      }
	  return Suunta.PAIKALLAAN;
  }
  
  public void paivita(){
	  dv.invalidate();
  }
  
}
