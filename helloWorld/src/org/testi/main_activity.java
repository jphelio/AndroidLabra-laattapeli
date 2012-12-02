package org.testi;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class main_activity extends Activity implements SensorEventListener {
    private SensorManager mgr;
    private Sensor accelerometer;
    private TextView text0;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private float[] gravity = new float[3];
//  private float[] motion = new float[3];
//  private double ratio;
//  private double mAngle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        accelerometer = mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        text0 = (TextView) findViewById(R.id.text12);     
    }
    @Override
    protected void onResume() {
        mgr.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
      super.onResume();
    }

    @Override
    protected void onPause() {
        mgr.unregisterListener(this, accelerometer);
      super.onPause();
    }

  public void onAccuracyChanged(Sensor sensor, int accuracy) {
  }
  
  @Override
  public void onConfigurationChanged(Configuration conf){
	  //ei tehd‰ mit‰‰n
  }

  public void onSensorChanged(SensorEvent event) {
    for(int i=0; i<3; i++) {
            gravity [i] = (float) (0.1 * event.values[i] + 0.9 * gravity[i]);
//            motion[i] = event.values[i] - gravity[i];
    }
//      ratio = gravity[1]/SensorManager.GRAVITY_EARTH;
//      if(ratio > 1.0) ratio = 1.0;
//      if(ratio < -1.0) ratio = -1.0;
//      mAngle = Math.toDegrees(Math.acos(ratio));
//      if(gravity[2] < 0) {
//        mAngle = -mAngle;
//      }
    	  boolean tosi=false;
    	  if(gravity[0]>gravity[1]){
    		  text1 = (TextView) findViewById(R.id.text10);
    		  text2 = (TextView) findViewById(R.id.text11);
    		  text3 = (TextView) findViewById(R.id.text13);
    		  text4 = (TextView) findViewById(R.id.text14);
    		  findViewById(R.id.text2).setBackgroundColor(Color.BLACK);
    		  findViewById(R.id.text7).setBackgroundColor(Color.BLACK);
    		  findViewById(R.id.text17).setBackgroundColor(Color.BLACK);
    		  findViewById(R.id.text22).setBackgroundColor(Color.BLACK);
    		  tosi=true;
    	  }
    	  if(gravity[0]<gravity[1]){
    		  text1 = (TextView) findViewById(R.id.text2);
    		  text2 = (TextView) findViewById(R.id.text7);
    		  text3 = (TextView) findViewById(R.id.text17);
    		  text4 = (TextView) findViewById(R.id.text22);
    		  findViewById(R.id.text10).setBackgroundColor(Color.BLACK);
    		  findViewById(R.id.text11).setBackgroundColor(Color.BLACK);
    		  findViewById(R.id.text13).setBackgroundColor(Color.BLACK);
    		  findViewById(R.id.text14).setBackgroundColor(Color.BLACK);
    		  tosi=true;
    	  }
    	  if(tosi){
	    	  text0.setBackgroundColor(Color.RED);
	    	  text1.setBackgroundColor(Color.RED);
	    	  text2.setBackgroundColor(Color.RED);
	    	  text3.setBackgroundColor(Color.RED);
	    	  text4.setBackgroundColor(Color.RED);
    	  }
        text0.invalidate();
  }
}
