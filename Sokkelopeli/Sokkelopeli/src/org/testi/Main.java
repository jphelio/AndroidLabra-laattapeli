package org.testi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Main extends Activity implements SensorEventListener {
    private SensorManager manager;
    private Sensor accelerometer;
    private DrawView dv;
    private Pelimoottori moottori;
    private long startTime=0;
    
    /**
     * Kutsutaan kun ohjelma käynnistyy. Tarkemmat tiedot löytyvät
     * Activity-luokan rajapintakuvauksesta 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        System.out.println(manager.getSensorList(Sensor.TYPE_ALL));
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        uusiPeli();
        
    }
    
    /**
     * Tarkemmat tiedot löytyvät
     * Activity-luokan rajapintakuvauksesta
     */
    @Override
    protected void onResume() {
    	super.onResume();
    	registerManager();
    }

    /**
     * Tarkemmat tiedot löytyvät
     * Activity-luokan rajapintakuvauksesta
     */
    @Override
    protected void onPause() {
    	super.onPause();
    	manager.unregisterListener(this, accelerometer);    	
    }    
    
    /**
     * Luo uuden Dialogin, jossa näkyvät kulunut aika ja nappi jota painamalla alkaa uusi peli.
     */
    @Override
    protected Dialog onCreateDialog(int id){
	  switch(id){
	  	case 0: 
	  		return new AlertDialog.Builder(this)
	  		.setTitle("Voitit pelin! Aikasi oli: "+(System.currentTimeMillis()-startTime)/1000+" s")
	  		.setPositiveButton("Uusi peli", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					registerManager();
					uusiPeli();
				}
			}).create();
	  }
	  return null;
    }
    
    /**
     * Päivittää dialogin otsikon.
     */
    @Override
    protected void onPrepareDialog(int id, Dialog dialog){
    	dialog.setTitle("Voitit pelin! Aikasi oli: "+(System.currentTimeMillis()-startTime)/1000+" s");
    }
    
    /**
     * Suorittaa toimet Pelaajan likkuttamiseksi sekä tarkastelee loppuuko peli.
     * 
     */
    public void onSensorChanged(SensorEvent event) {
      moottori.siirra(getSuunta(event));
      if(moottori.isFinished()){
    	  manager.unregisterListener(this, accelerometer);
    	  showDialog(0);
      }
      paivita();
    } 
    
    /**
     * Tarkistaa, mihin suuntaan kohdistuu suurin kiihtyuvyys
     * @param event SensorEvent -olio
     * @return palauttaa suunnan
     */
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
    
    private void paivita(){
	  dv.invalidate();
    }
    
    private void registerManager(){
	  manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }
  
    private void uusiPeli(){
	  startTime=System.currentTimeMillis();
	  moottori=new Pelimoottori();
      dv=new DrawView(this, moottori);
      dv.setBackgroundColor(Color.BLACK);
      setContentView(dv);
    }
    
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}
