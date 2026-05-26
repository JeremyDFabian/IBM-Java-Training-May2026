public non-sealed class PaymentGateway extends Gateway {
	
    public void process(Payment payment) {
        payment.executePayment();
        System.out.println("Payment has been processed");
    }
}