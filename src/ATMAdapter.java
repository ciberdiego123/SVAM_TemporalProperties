import fr.ufc.m2info.svam.ATM;
import fr.ufc.m2info.svam.Account;
import fr.ufc.m2info.svam.Card;

public class ATMAdapter {

    ATM sut = new ATM();
    
    private static final int VALID_PIN = 1234;
    
    private static final int INVALID_PIN = 4567;
    
    private static final int TOTAL_AMOUNT = 100;
    
    private static final int VALID_AMOUNT = TOTAL_AMOUNT / 2;
    
    private static final int INVALID_AMOUNT = TOTAL_AMOUNT * 2; 
    
    /** Variable representing the property monitor using automaton */
    private PropertyAutomateMonitor monitor = new PropertyAutomateMonitor();
    
    public void reset() {
        sut = new ATM();
        monitor.reset();
    }
    
    public void insertCard() {
        // create a card with pin code 1234 and associated account of 100 euros in balance
        Card c = new Card(TOTAL_AMOUNT, new Account(TOTAL_AMOUNT));
        System.out.println("-------------");
        System.out.println("Inserted card");
        sut.insertCard(c);
        monitor.doTransition("insertCard");
    }
    
    public void cancel() {
        System.out.println("Pressed cancel");
        sut.cancel();
        monitor.doTransition("cancel");
    }
    
    public void chooseAmount_OK(){
    	System.out.println("Valid Amount chosen");
    	sut.chooseAmount(VALID_AMOUNT);
    	monitor.doTransition("chooseAmount_OK");
    }
    
    public void chooseAmount_Invalid(){
    	System.out.println("Invalid Amount chosen");
    	sut.chooseAmount(INVALID_AMOUNT);
    	monitor.doTransition("chooseAmount_Invalid");
    }
    
    public void inputPin_OK(){
    	System.out.println("Valid Pin input");
    	sut.inputPin(VALID_PIN);
    	monitor.doTransition("inputPin_OK");
    }
    
    public void inputPin_Invalid(){
    	System.out.println("Invalid Pin input");
    	sut.inputPin(INVALID_PIN);
    	monitor.doTransition("inputPin_Invalid");
    }
    
    public void takeCard_OK(){ 
    	System.out.println("Card taken");
    	sut.takeCard();
    	monitor.doTransition("takeCard_OK");
    }
    
    public void takeCard_NotTaken(){ 
    	System.out.println("Card not taken");
    	monitor.doTransition("takeCard_NotTaken");
    }
    
    public void takeBills_OK(){ 
    	System.out.println("Bills taken");
    	sut.takeBills();
    	monitor.doTransition("takeBills_OK");
    }
    
    public void takeBills_NotTaken(){ 
    	System.out.println("Bills not taken");
    	monitor.doTransition("takeBills_NotTaken");
    }    
  
}