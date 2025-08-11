public class Payment {
    private String paymentId;
    private String orderId;
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String status;

    public Payment(String paymentId, String orderId, double amount, String paymentDate, String paymentMethod, String status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Hiển thị thông tin thanh toán
    public void displayPaymentInfo() {
        System.out.println("💳 Payment ID: " + paymentId +
                           " | Order ID: " + orderId +
                           " | Amount: " + amount +
                           " | Date: " + paymentDate +
                           " | Method: " + paymentMethod +
                           " | Status: " + status);
    }
}
