package CruzeX.webapp.Model;

public class Payment {
    private int paymentId;
    private int bookingId;
    private double price;
    private String cardholderName;
    private String cardNumber;
    private String expiryDate;
    private String cvcNumber;
    private String paymentDate;
    private int customerId;

    // Constructor for retrieving data from DB
    public Payment(int paymentId, int bookingId, double price, String cardholderName, String cardNumber, String expiryDate, String cvcNumber, String paymentDate, int customerId) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.price = price;
        this.cardholderName = cardholderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvcNumber = cvcNumber;
        this.paymentDate = paymentDate;
        this.customerId = customerId;
    }

    // Constructor for inserting new payments (No PaymentID as it's auto-generated)
    public Payment(int bookingId, double price, String cardholderName, String cardNumber, String expiryDate, String cvcNumber, int customerId) {
        this.bookingId = bookingId;
        this.price = price;
        this.cardholderName = cardholderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvcNumber = cvcNumber;
        this.customerId = customerId;
    }

    // Default constructor
    public Payment() {
    }

    // Getters and Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCardholderName() { return cardholderName; }
    public void setCardholderName(String cardholderName) { this.cardholderName = cardholderName; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public String getCvcNumber() { return cvcNumber; }
    public void setCvcNumber(String cvcNumber) { this.cvcNumber = cvcNumber; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    // Masking sensitive card details in toString() for security reasons
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", bookingId=" + bookingId +
                ", price=" + price +
                ", cardholderName='" + cardholderName + '\'' +
                ", cardNumber='**** **** **** " + (cardNumber.length() > 4 ? cardNumber.substring(cardNumber.length() - 4) : "XXXX") + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvcNumber='*'" +
                ", paymentDate='" + paymentDate + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}