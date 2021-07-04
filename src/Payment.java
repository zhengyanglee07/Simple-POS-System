
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Payment extends PaymentInfo{
    Scanner scanner = new Scanner(System.in);

    public Payment() {

    }

    public void transaction(int index, double totalAmt){
        int payMtd;
        /* Pay Method */
        do{
            System.out.println("\n======================");
            System.out.println("||  Payment Method  ||");
            System.out.println("======================");
            System.out.println("|| 1. | Cash        ||");
            System.out.println("|| 2. | Credit Card ||");
            System.out.println("|| 3. | TNG eWallet ||");
            System.out.println("======================");
            System.out.print("Enter customer pay method: ");
            payMtd = scanner.nextInt();
            scanner.nextLine();
            if(payMtd == 1){
                System.out.printf("Total Amount : %.2f" , totalAmt);
                /*Get Pay amount from customers*/
                System.out.print("\nEnter Total Pay by Customer : ");
                double payAmt = scanner.nextDouble();

                /*Calculate Balance*/
                double balance = calcBalance(totalAmt, payAmt);
                System.out.printf("\nBalance = %+5.2f\n", Math.abs(balance));

                System.out.println("\n---------------------");
                System.out.println("|  Pay Successful  |");
                System.out.println("---------------------");

                /*Set the record*/
                Store.getOrderList().get(index).setPayAmount(payAmt);
                Store.getOrderList().get(index).setBalance(balance);
                Store.getOrderList().get(index).setPayMethod("Cash");

            }else if(payMtd == 2){
                System.out.print("\nReading Card Information");
                for (int i = 0; i <3 ; i++) {
                    System.out.print('.');
                    delay(1);
                }
                System.out.println("\n---------------------");
                System.out.println("|  Pay Successful  |");
                System.out.println("---------------------");

                System.out.printf("\nTotal Amount -RM %.2f- received\n\n", totalAmt);
                Store.getOrderList().get(index).setPayAmount(totalAmt);
                Store.getOrderList().get(index).setBalance(0);
                Store.getOrderList().get(index).setPayMethod("Credit Card");
            }else if(payMtd == 3){
                System.out.print("\nReading bar code or QR code");
                for (int i = 0; i <3 ; i++) {
                    System.out.print('.');
                    delay(1);
                }
                System.out.println("\n---------------------");
                System.out.println("|  Pay Successful  |");
                System.out.println("---------------------");

                System.out.printf("\nTotal Amount -RM %.2f- received\n\n",totalAmt);
                Store.getOrderList().get(index).setPayAmount(totalAmt);
                Store.getOrderList().get(index).setBalance(0);
                Store.getOrderList().get(index).setPayMethod("TNG eWallet");
            }else{
                System.out.println("Invalid Value! Please enter again.");

            }
        }while(payMtd < 1 || payMtd >3 );
    }


    /*--------------------------------------------------------------------------------------------*/
    /*------------------------------------------Calculation---------------------------------------*/
    /*--------------------------------------------------------------------------------------------*/

    public double calcSubtotal(String prodID, int qty) {
        double product_price = 0.0;
        for (int i = 0; i < Store.getMenuList().size(); i++) {
            if (Store.getMenuList().get(i).getProd_ID().equals(prodID)) {
                product_price = Store.getMenuList().get(i).getProd_Price();
            }
        }
        return product_price * qty;
    }


    public double calcTotal(ArrayList<Double> subtotal, int discRate) {
        double total = 0.0;

        for (Double aDouble : subtotal) {
            total += aDouble;
        }

        double discount = total * ((double)discRate/ 100);
        double serviceCharge = total * ((double)getService_charge()/ 100);

        return total - discount + serviceCharge;
    }

    public double calcBalance(double total, double totalPay) {
        return total - totalPay;
    }


    /*Delay Method*/
    public void delay(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
