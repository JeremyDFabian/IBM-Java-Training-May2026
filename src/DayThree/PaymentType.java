package DayThree;

public sealed class PaymentType permits OnlinePaymentType, OfflinePaymentType {
    private String category;
 
    public PaymentType(String category) {
        this.category = category;
    }
 
    public String getCategory() {
        return category;
    }
}
 
