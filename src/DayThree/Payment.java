package DayThree;

		public abstract class Payment {
			
			
	    private double amount; 

	    public Payment(double amount) {
	        this.amount = amount;
	    }

	    public abstract void executePayment();

	    public void displayAmount() {
	        System.out.println("Payment amount: " + amount);
	    }

	    public double getAmount() {
	        return amount;
	    }
	}