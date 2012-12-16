package org.testi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

public class DrawView extends View{

	Paint paint=new Paint();
	
	private ShapeDrawable tokanelio;
	private Pelimoottori moottori;
	private Taso taso;
	
	/**
	 * Konstruktori
	 * 
	 * @param context Context olio
	 * @param moottori Pelimoottori olio
	 */
	public DrawView(Context context, Pelimoottori moottori) {
		super(context);
		this.moottori=moottori;
		taso=moottori.getTaso();
		tokanelio=new ShapeDrawable(new OvalShape());
	} 
	
	/**
	 * Piirt‰‰ pelialueen uudelleen
	 * 
	 * @param canvas Canvas olio
	 */
	@Override
	public void onDraw(Canvas canvas){
        int apurivi=0;
        for(int rivi=0;rivi<taso.getKorkeus();rivi++){
        	int apusarake=0;
        	for(int sarake=0;sarake<taso.getLeveys();sarake++){
        		paint.setColor(taso.getRuudunVari(rivi, sarake));
        		Rect apunelio=new Rect(apusarake, apurivi, taso.ruudunKoko()+apusarake, taso.ruudunKoko()+apurivi);
        		canvas.drawRect(apunelio, paint);
        		apusarake+=taso.ruudunKoko();
        	}
        	apurivi+=taso.ruudunKoko();
        }
        tokanelio.getPaint().setColor(Color.WHITE);
        changeTokaNelioXY();
        tokanelio.draw(canvas);
	}
	
	private void changeTokaNelioXY(){
		int apusarake=moottori.getPelaaja().getSarake()*taso.ruudunKoko();
		int apurivi=moottori.getPelaaja().getRivi()*taso.ruudunKoko();
		tokanelio.setBounds(apusarake, apurivi, apusarake+taso.ruudunKoko(), apurivi+taso.ruudunKoko());
	}
}
