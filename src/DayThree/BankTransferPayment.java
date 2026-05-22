package DayThree;

public class BankTransferPayment extends Payment implements Verifiable {
 private String accountNumber;

 public BankTransferPayment(double amount, String accountNumber) {
     super(amount);
     this.accountNumber = accountNumber;
 }

 public boolean verify() {
	 
     if (accountNumber.length() == 10) {
         return true;
     } else {
         return false;
     }
 }

 public void executePayment() {
	 
     System.out.println("Processing bank transfer...");
 }

 public String getAccountNumber() {
	 
     return accountNumber;
 }
}
 
