package org.testi;

public class Pelimoottori {
	private Taso taso;
	private Pelaaja pelaaja;
	private boolean loppu=false;
	
	public Pelimoottori(){
		taso=new Taso();
		pelaaja=taso.getPelaaja();
	}
	
	/**
	 * Tarkastaa ensin, voiko pelaajaa siirt‰‰ ruutuun, johon pelaaja on liikkumassa ja sitten siirt‰‰ pelaajan siihen,
	 * jos ruutu oli tyhj‰ tai maali.
	 * @param suunta siirra -metodi saa parametrinaan suunnan, johon ollaan siirtym‰ss‰.
	 */
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
	}
	
	/**
	 * Tarkistaa, loppuuko peli, vertaamalla pelaajan koordinaateissa olevan ruudun tilaa
	 * Loppu -ruudun tilaan  
	 * 
	 * @param rivi pelaajan rivi koordinaatti
	 * @param sarake pelaajan sarake koordinaatti
	 * @return true tai false
	 */
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
