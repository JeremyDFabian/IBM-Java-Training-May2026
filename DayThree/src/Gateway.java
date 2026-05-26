public sealed abstract class Gateway permits PaymentGateway {
	
    public abstract void process(Payment payment);
    
}