import java.util.ArrayList;

public class OrderInfo extends PaymentInfo {
    private static int autoGenerate =0;
    private int orderMo;
    private String custName;
    private ArrayList<String> prod_id = new ArrayList<>();
    private ArrayList<Integer> prod_qty = new ArrayList<>();


    public OrderInfo() {

    }

    public OrderInfo(String custName, ArrayList<String> prod_id, ArrayList<Integer> prod_qty,ArrayList<Double> subtotal, double totalAmount) {
        this.orderMo = autoGenerate++;
        this.custName = custName;
        this.prod_id = prod_id;
        this.prod_qty = prod_qty;
        super.setSubtotal(subtotal);
        super.setTotalAmount(totalAmount);
    }

    /*-----------------------------------------------------------------------*/
    /*------------ Getter and Setter for attribute in Order class------------*/
    /*-----------------------------------------------------------------------*/

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }


    public static int getAutoGenerate() {
        return autoGenerate;
    }

    public static void setAutoGenerate(int autoGenerate) {
        OrderInfo.autoGenerate = autoGenerate;
    }

    public int getOrderMo() {
        return orderMo;
    }

    public void setOrderMo(int orderMo) {
        this.orderMo = orderMo;
    }

    public ArrayList<String> getProd_id() {
        return prod_id;
    }

    public void setProd_id(ArrayList<String> prod_id) {
        this.prod_id = prod_id;
    }

    public ArrayList<Integer> getProd_qty() {
        return prod_qty;
    }

    public void setProd_qty(ArrayList<Integer> prod_qty) {
        this.prod_qty = prod_qty;
    }


}
