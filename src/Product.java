public class Product {
    private String categ_ID;
    private String prod_ID;
    private String prod_Name;
    private double prod_Price;

    public Product(){

    }


    public Product(String categ_ID, String prod_ID, String prod_Name, double prod_Price) {
        this.categ_ID = categ_ID;
        this.prod_ID = prod_ID;
        this.prod_Name = prod_Name;
        this.prod_Price = prod_Price;
    }


    /*-------------------------------------------------------------------------*/
    /*------------ Getter and Setter for attribute in Product class------------*/
    /*-------------------------------------------------------------------------*/

    public String getCateg_ID(){
        return categ_ID;
    }
    public void setCateg_ID(String categ_ID){
        this.categ_ID = categ_ID;
    }

    public String getProd_ID() {
        return prod_ID;
    }

    public void setProd_ID(String prod_ID) {
        this.prod_ID = prod_ID;
    }

    public String getProd_Name() {
        return prod_Name;
    }

    public void setProd_Name(String prod_Name) {
        this.prod_Name = prod_Name;
    }

    public double getProd_Price() {
        return prod_Price;
    }

    public void setProd_Price(double prod_Price) {
        this.prod_Price = prod_Price;
    }
}
