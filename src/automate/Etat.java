package automate;
import java.util.ArrayList;
import java.util.TreeMap;

public class Etat {
	
	private String label;
	private TypeEtat type;
	private TreeMap<String,Transition> transitions;
	
	public Etat(String label, TypeEtat type){
		
		this.label = label;
		this.type = type;
		transitions = new TreeMap<>();
	}
		
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public TypeEtat getType() {
		return type;
	}

	public void setType(TypeEtat type) {
		this.type = type;
	}

	public TreeMap<String, Transition> getTransitions() {
		return transitions;
	}

	public void addTransition(Transition transition){
		transitions.put(transition.getLabel(),transition);
	}
	
	public Etat getEtatSuivant(String transitionLabel){
		if(transitions.containsKey(transitionLabel)){
			return transitions.get(transitionLabel).getEtat();
		}else{
			System.out.println("Transtition "+transitionLabel+" n\'est pas definie pour cet etat");
			return null;
		}
	}
	
}
