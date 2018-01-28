package automate;
import java.util.ArrayList;

public class Automate {
	private String name;
	
	private Etat etatInitial;
	
	private Etat etatCourant;
	
	public Automate(String name){
		this.name = name;
		etatInitial = new Etat("0",TypeEtat.TRANSITION);
		etatCourant = etatInitial;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Etat getEtatInitial() {
		return etatInitial;
	}

	public void setEtatInitial(Etat etatInitial) {
		this.etatInitial = etatInitial;
	}

	public Etat getEtatCourant() {
		return etatCourant;
	}

	public void setEtatCourant(Etat etatCourant) {
		this.etatCourant = etatCourant;
	}
	
	public void doTransition(String transitionLabel){
		Etat etatSuivant = etatCourant.getEtatSuivant(transitionLabel);
		if(etatSuivant != null){
			etatCourant = etatSuivant;
		}		
	}
	
	/* Retourner a l'etat initial */
	public void reset(){
		etatCourant = etatInitial;
	}
	
}
