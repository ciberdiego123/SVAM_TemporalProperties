package automate;


public class Transition {
	
	private String label;
	private Etat etat;
	
	public Transition(String label, Etat etat){
		this.label = label;
		this.etat=etat;
	}
	
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
