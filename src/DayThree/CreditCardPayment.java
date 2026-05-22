package DayThree;

public class CreditCardPayment extends Payment implements Verifiable {
	
    private String cardNumber;
 
    public CreditCardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }
 
    public boolean verify() {

        if (cardNumber.length() == 16) {
            return true;
        } else {
            return false;
        }
    }
 
    public void executePayment() {
    	
        System.out.println("Processing credit card payment...");
    }
 
    public String getCardNumber() {
    	
        return cardNumber;
    }
}
