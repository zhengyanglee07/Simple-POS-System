import java.util.ArrayList;

public class PaymentInfo{
    private ArrayList<Double> subtotal = new ArrayList<>();
    private int discount_rate;
    private static int service_charge;
    private double totalAmount;
    private double payAmount;
    private double balance;
    private String payMethod;

    public PaymentInfo(){
    }

    public PaymentInfo(ArrayList<Double> subtotal, int discount_rate, double totalAmount, double payAmount, double balance, String payMethod) {
        this.subtotal = subtotal;
        this.discount_rate = discount_rate;
        this.totalAmount = totalAmount;
        this.payAmount = payAmount;
        this.balance = balance;
        this.payMethod = payMethod;
    }

    /*-----------------------------------------------------------------------------*/
    /*------------ Getter and Setter for attribute in PaymentInfo class------------*/
    /*-----------------------------------------------------------------------------*/

    public ArrayList<Double> getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(ArrayList<Double> subtotal) {
        this.subtotal = subtotal;
    }

    public int getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(int discount_rate) {
        this.discount_rate = discount_rate;
    }

    public static int getService_charge() {
        return service_charge;
    }

    public static void setService_charge(int service_charge) {
        PaymentInfo.service_charge = service_charge;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}
