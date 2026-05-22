package DayThree;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Main {
	
    public static void main(String[] args) {

        CreditCardPayment p1 = new CreditCardPayment(5555.0, "1234567812345678");
        PayPalPayment p2 = new PayPalPayment(12345.00, "Jeremy.Fabian@ibm.com");
        BankTransferPayment p3 = new BankTransferPayment(54321.20, "9876543210");

        ArrayList<Payment> payments = new ArrayList<>();
        
        payments.add(p1);
        payments.add(p2);
        payments.add(p3);

        OnlinePaymentType online = new OnlinePaymentType();
        OfflinePaymentType offline = new OfflinePaymentType();

        Gateway gateway = new PaymentGateway();

        ArrayList<PaymentDetails> records = new ArrayList<>();

        System.out.println("Credit Card Payment");
        System.out.println("Category: " + online.getCategory());
        
        p1.displayAmount();
        
        if (p1.verify()) {
        	
            gateway.process(p1);
            LocalDateTime time1 = LocalDateTime.now();
            PaymentDetails d1 = new PaymentDetails("IBM01", p1.getAmount(), "Credit Card", time1);
            records.add(d1);
            
        } else {
            System.out.println("Verification failed.");
        }
        
        System.out.println();
        System.out.println("PayPal Payment");
        System.out.println("Category: " + online.getCategory());
        
        p2.displayAmount();
        
        if (p2.verify()) {
        	
            gateway.process(p2);
            LocalDateTime time2 = LocalDateTime.now();
            PaymentDetails d2 = new PaymentDetails("IBM02", p2.getAmount(), "PayPal", time2);
            records.add(d2);
            
        } 
        
        else {
        	
            System.out.println("Verification failed.");
        }
        
        System.out.println();
        System.out.println("Bank Transfer Payment");
        System.out.println("Category: " + offline.getCategory());
        
        p3.displayAmount();
        
        if (p3.verify()) {
        	
            gateway.process(p3);
            
            LocalDateTime time3 = LocalDateTime.now();
            
            PaymentDetails d3 = new PaymentDetails("IBM03", p3.getAmount(), "Bank Transfer", time3);
            records.add(d3);
        } 
        
        else {
        	
            System.out.println("Verification failed.");
        }
        
        System.out.println();
        System.out.println("All Payment Records:");
        
        for (PaymentDetails d : records) {
        	
            System.out.println(d);
        }
    }
}