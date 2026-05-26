public class PayPalPayment extends Payment implements Verifiable {
 private String email;

 public PayPalPayment(double amount, String email) {
     super(amount);
     this.email = email;
 }

 public boolean verify() {
     if (email.contains("@")) {
         return true;
     } else {
         return false;
     }
 }

 public void executePayment() {
     System.out.println("Processing PayPal payment...");
 }

 public String getEmail() {
     return email;
 }
}
		