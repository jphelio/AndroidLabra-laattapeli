package org.testi;

public class Pelaaja {
	private int rivi;
	private int sarake;
	
	public Pelaaja(int rivi, int sarake){
		this.rivi=rivi;
		this.sarake=sarake;
	}
	
	public void setRivi(int rivi){
		this.rivi=rivi;
	}
	public void setSarake(int sarake){
		this.sarake=sarake;
	}
	public int getRivi(){
		return rivi;
	}
	public int getSarake(){
		return sarake;
	}
}
