package org.testi;

public class Pelimoottori {
	private Taso taso;
	private Main main;
	private Pelaaja pelaaja;
	private boolean loppu=false;
	
	public Pelimoottori(Main main){
		this.main=main;
		taso=new Taso();
		pelaaja=taso.getPelaaja();
	}
	public void siirra(Suunta suunta){
		if(suunta==Suunta.ALAS){
			if(onLoppu(pelaaja.getRivi()+1, pelaaja.getSarake())) return;
			if(taso.getRuudunTila(pelaaja.getRivi()+1, pelaaja.getSarake())==RuudunTila.TYHJA){
				pelaaja.setRivi((pelaaja.getRivi()+1));
			}
		}
		else if(suunta==Suunta.YLOS){
			if(onLoppu(pelaaja.getRivi()-1, pelaaja.getSarake())) return;
			if(taso.getRuudunTila(pelaaja.getRivi()-1, pelaaja.getSarake())==RuudunTila.TYHJA){
				pelaaja.setRivi((pelaaja.getRivi()-1));
			}
		}
		else if(suunta==Suunta.OIKEA){
			if(onLoppu(pelaaja.getRivi(), pelaaja.getSarake()+1)) return;
			if(taso.getRuudunTila(pelaaja.getRivi(), pelaaja.getSarake()+1)==RuudunTila.TYHJA){
				pelaaja.setSarake(pelaaja.getSarake()+1);
			}
		}
		else if(suunta==Suunta.VASEN){
			if(onLoppu(pelaaja.getRivi(), pelaaja.getSarake()-1)) return;
			if(taso.getRuudunTila(pelaaja.getRivi(), pelaaja.getSarake()-1)==RuudunTila.TYHJA){
				pelaaja.setSarake(pelaaja.getSarake()-1);
			}
		}
//		main.paivita();
	}
	public boolean onLoppu(int rivi, int sarake){
		if(taso.getRuudunTila(rivi, sarake)==RuudunTila.LOPPU){
			loppu=true;
			pelaaja.setRivi(rivi);
			pelaaja.setSarake(sarake);
		}
		return loppu;
	}
	public Taso getTaso(){
		return taso;
	}
	public boolean isFinished(){
		return loppu;
	}
	public Pelaaja getPelaaja(){
		return pelaaja;
	}
}
