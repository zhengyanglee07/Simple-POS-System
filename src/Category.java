public class Category {
    private String categID;
    private String categDesc;
    private double min_price;
    private double max_price;

    public Category(){
    }

    public Category(String categID, String categDesc, double min_price, double max_price){
        this.categID=categID;
        this.categDesc=categDesc;
        this.min_price=min_price;
        this.max_price=max_price;
    }


    /*--------------------------------------------------------------------------*/
    /*------------ Getter and Setter for attribute in Category class------------*/
    /*--------------------------------------------------------------------------*/
    public String getCategID() {
        return categID;
    }

    public void setCategID(String categID) {
        this.categID = categID;
    }

    public String getCategDesc() {
        return categDesc;
    }

    public void setCategDesc(String categDesc) {
        this.categDesc = categDesc;
    }

    public double getMin_price() {
        return min_price;
    }

    public void setMin_price(double min_price) {
        this.min_price = min_price;
    }

    public double getMax_price() {
        return max_price;
    }

    public void setMax_price(double max_price) {
        this.max_price = max_price;
    }
}
