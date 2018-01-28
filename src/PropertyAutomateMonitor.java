import java.util.TreeMap;

import automate.Automate;
import automate.Etat;
import automate.Transition;
import automate.TypeEtat;


public class PropertyAutomateMonitor {
	/**
	 * NEVER takeBills_OK
	 * AFTER cancel
	 * UNTIL inputPin_OK
	 */
	private Automate p1;
	
	/**
	 * NEVER  takeBills_OK
	 * BEFORE inputPin_OK
	 */
	private Automate p2;
	
	/**
	 * NEVER insertCard
	 * AFTER insertCard
	 * UNTIL takeCard_OK  || takeCard_NotTaken
	 */
	private Automate p3;
	
	public PropertyAutomateMonitor(){
		buildAutomateP1();
		buildAutomateP2();
		buildAutomateP3();
	}
	
	public void buildAutomateP1(){
		p1 = new Automate("P1");
		Etat e0 = p1.getEtatInitial();
		Etat e1 = new Etat("1",TypeEtat.TRANSITION);
		Etat e2 = new Etat("2",TypeEtat.ACCEPTATION);
		Etat ex = new Etat("x",TypeEtat.ERREUR);
		e0.addTransition(new Transition("cancel", e1));
		e0.addTransition(new Transition("OTHERS",e0));
		e1.addTransition(new Transition("inputPin_OK",e2));
		e1.addTransition(new Transition("takeBills_OK",ex));
		e1.addTransition(new Transition("OTHERS",e1));
		e2.addTransition(new Transition("OTHERS",e2));	
	}
	
	public void buildAutomateP2(){
		p2 = new Automate("P2");
		Etat e0 = p2.getEtatInitial();
		Etat e1 = new Etat("1",TypeEtat.TRANSITION);
		Etat e2 = new Etat("2",TypeEtat.ACCEPTATION);
		Etat ex = new Etat("x",TypeEtat.ERREUR);
		e0.addTransition(new Transition("takeBills_OK", e1));
		e0.addTransition(new Transition("inputPin_OK", e2));
		e0.addTransition(new Transition("OTHERS",e0));
		e1.addTransition(new Transition("inputPin_OK",ex));
		e1.addTransition(new Transition("OTHERS",e1));
		e2.addTransition(new Transition("OTHERS",e2));	
	}
	
	public void buildAutomateP3(){
		p3 = new Automate("P3");
		Etat e0 = p3.getEtatInitial();
		Etat e1 = new Etat("1",TypeEtat.TRANSITION);
		Etat e2 = new Etat("2",TypeEtat.ACCEPTATION);
		Etat ex = new Etat("x",TypeEtat.ERREUR);
		e0.addTransition(new Transition("insertCard", e1));
		e0.addTransition(new Transition("OTHERS",e0));
		e1.addTransition(new Transition("takeCard_OK",e2));
		e1.addTransition(new Transition("takeCard_NotTaken",e2));
		e1.addTransition(new Transition("insertCard",ex));
		e1.addTransition(new Transition("OTHERS",e1));
		e2.addTransition(new Transition("OTHERS",e2));	
	}
	
	public void reset(){
		p1.reset();
		p2.reset();
		p3.reset();
	}
	
	public void doTransition(Automate automate,String transitionLabel){
		if(automate.getEtatCourant().getType() == TypeEtat.ERREUR){
			System.out.println("The property "+automate.getName()+" is NOT VALID");
			return;
		}
		TreeMap<String, Transition> transitions = automate.getEtatCourant().getTransitions();
		if(transitions.containsKey(transitionLabel))
			automate.doTransition(transitionLabel);
		else
			automate.doTransition("OTHERS");
		System.out.println(automate.getName()+":  State = "+automate.getEtatCourant().getLabel()+"  Type = "+automate.getEtatCourant().getType());
		if(automate.getEtatCourant().getType() == TypeEtat.ERREUR)
			System.out.println("The property "+automate.getName()+" is NOT VALID");
	}
	
	/*
	 * To do transition for all the automatons
	 */
	public void doTransition(String transitionLabel){
		doTransition(p1,transitionLabel);
		doTransition(p2,transitionLabel);
		doTransition(p3,transitionLabel);
	}
	
}
