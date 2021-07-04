import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Summary {
    private ArrayList<OrderInfo> orderInfo = Store.getOrderList();
    private ArrayList<Product> menuList = Store.getMenuList();

    public Summary() {
        LocalDateTime current = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String formatted = current.format(formatter);

        System.out.println("========================================================================================");
        System.out.println("|                        SUMMARY OF THE DAY : " + formatted + "                   |");
        System.out.println("========================================================================================");
        /*Read Order Detail: Store.orderList.get(index);*/


        System.out.println("Total Product Listed: " + menuList.size());
        System.out.println("Total Customer:  " + orderInfo.size());


        for (OrderInfo info : orderInfo) {
            System.out.printf("\n\nOrder No: %d\t\tCustomer Name: %s\n", info.getOrderMo() + 1, info.getCustName());
            System.out.println("========================================================================================");
            for (int j = 0; j < info.getProd_id().size(); j++) {
                System.out.printf("%d. Product ID: %s\t\t, Product QTY: %d\t\tProduct Subtotal: %.2f\n", j + 1, info.getProd_id().get(j), info.getProd_qty().get(j), info.getSubtotal().get(j));
            }
            System.out.printf("Total Amount %.2f", info.getTotalAmount());
        }

        menuSummary();

        backOrExit();

    }

    public void menuSummary() {
        ArrayList<Integer> totalQty = new ArrayList<>();
        ArrayList<Double> subtotal = new ArrayList<>();
        double total = 0.0;

        for (Product product : menuList) {
            int qty = 0;
            for (OrderInfo info : orderInfo) {
                for (int k = 0; k < info.getProd_id().size(); k++) {
                    if (info.getProd_id().get(k).equals(product.getProd_ID())) {
                        qty += info.getProd_qty().get(k);
                    }
                }
            }
            totalQty.add(qty);
        }

        System.out.println("\n\n======================================");
        System.out.println("              Menu Summary");
        System.out.println("======================================");
        System.out.println("Product Name\tQuantity\tSubtotal");
        System.out.println("======================================");
        for (int i = 0; i <menuList.size() ; i++) {
            subtotal.add(menuList.get(i).getProd_Price()*totalQty.get(i) );
            System.out.printf("%-15s\t\t%-3d\t\t%8.2f\n", menuList.get(i).getProd_Name(),totalQty.get(i), subtotal.get(i));
        }
        for (Double subttl : subtotal) {
            total += subttl;
        }
        System.out.println("======================================");
        System.out.printf("\t\t\t\t   Total\t%8.2f\n", total);
        System.out.println("======================================");


    }


    /*---------------------------------------------------*/
    /*----Back to module option or exit system option----*/
    /*---------------------------------------------------*/
    public void backOrExit() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n\n1. Back To Module");
            System.out.println("2. Exit System");
            System.out.print("Enter your option: ");
            option = scanner.nextInt();
            if (option == 1) {
                Main.setLogin(true);
                Main.main(null);
            } else if (option == 2) {
                System.exit(0);
            } else {
                System.out.print("\n\n************************************\n");
                System.out.print("|Invalid Value! Please enter again.|\n");
                System.out.print("************************************\n\n");
            }
        } while (option < 1 || option > 2);
    }

    public ArrayList<OrderInfo> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(ArrayList<OrderInfo> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public ArrayList<Product> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<Product> menuList) {
        this.menuList = menuList;
    }
}
