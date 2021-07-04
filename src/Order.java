import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Order extends Action {
    Scanner scanner = new Scanner(System.in);
    ArrayList<OrderInfo> orderInfo = Store.getOrderList();
    ArrayList<Product> menuList = Store.getMenuList();
    Payment payment = new Payment();
    private static boolean executed;


    public Order() {
        PaymentInfo.setService_charge(6);
        if (!executed) {
            /*Add some example of order, just for references*/
            ArrayList<String> orderProd = new ArrayList<>();
            ArrayList<Integer> orderQty = new ArrayList<>();
            ArrayList<Double> subTotal = new ArrayList<>();

            /*Order 1 */
            orderProd.add("M00001");orderProd.add("M00003"); orderProd.add("D00001");
            orderQty.add(3);orderQty.add(10);orderQty.add(7);
            subTotal.add(payment.calcSubtotal("M00001", 3));
            subTotal.add(payment.calcSubtotal("M00003", 10));
            subTotal.add(payment.calcSubtotal("D00001", 7));
            double total = payment.calcTotal(subTotal, 10);
            orderInfo.add(new OrderInfo("Kay yee", orderProd, orderQty, subTotal, total));
            orderInfo.get(0).setDiscount_rate(10);
            Store.getOrderList().get(0).setPayMethod("Credit Card");
            orderInfo.get(0).setDiscount_rate(10);
            orderInfo.get(0).setPayAmount(total);
            orderInfo.get(0).setBalance(payment.calcBalance(total, total));

            //Order 2
            total=0;
            ArrayList<String> orderProd2 = new ArrayList<>();
            ArrayList<Integer> orderQty2 = new ArrayList<>();
            ArrayList<Double> subTotal2 = new ArrayList<>();
            orderProd2.add("D00002");orderProd2.add("D00001"); orderQty2.add(5);orderQty2.add(2);
            subTotal2.add(payment.calcSubtotal("D00002", 5));
            subTotal2.add(payment.calcSubtotal("D00001", 2));
            total = payment.calcTotal(subTotal2, 20);
            orderInfo.add(new OrderInfo("Sheng Zhen", orderProd2, orderQty2, subTotal2, total));
            Store.getOrderList().get(1).setPayMethod("TNG eWallet");
            orderInfo.get(1).setDiscount_rate(20);
            orderInfo.get(1).setPayAmount(total);
            orderInfo.get(1).setBalance(payment.calcBalance(total, total));
            OrderInfo.setAutoGenerate(2);

            executed = true;
        }

    }

    @Override
    public void create() {
        int index = OrderInfo.getAutoGenerate();
        boolean validation;
        String prodID;
        int qty = 0;
        /*Use to temporary store the product id and qty order by customers*/
        ArrayList<String> orderProd = new ArrayList<>();
        ArrayList<Integer> orderQty = new ArrayList<>();
        ArrayList<Double> subTotal = new ArrayList<>();

        char yesNo;

        System.out.print("=================================\n");
        System.out.println("\n\t<<Add Order>>");
        System.out.printf("\tOrderNo: %d", OrderInfo.getAutoGenerate() + 1);

        System.out.print("\n\tCustomer Name: ");
        String cust_name = scanner.nextLine();

        do {
            do {
                validation = false;

                System.out.print("\n\tEnter Product ID : ");
                prodID = scanner.next();

                for (Product menuList1 : menuList) {
                    if (menuList1 != null) {
                        if (prodID.equals(menuList1.getProd_ID())) {
                            validation = true;
                        }
                    }
                }
                if (!validation) {

                    System.out.print("\n\tPlease Enter Correct Product ID!\n");
                    System.out.print("\t================================");
                }
            } while (!validation);


            do {
                validation = false;
                try {
                    System.out.print("\n\tEnter Quantity : ");
                    qty = scanner.nextInt();

                } catch (InputMismatchException ex) {
                    System.out.println("\n\tPlease Enter Number of Quantity!\n");
                    System.out.print("\t================================\n\n");
                    scanner.nextLine();
                    validation = true;
                }
            } while (validation);

            /*Show the subtotal for product ordered*/
            System.out.printf("\n\tSubtotal = %.2f", payment.calcSubtotal(prodID, qty));

            /*Temporary store all the product have been ordered and subtotal to arraylist*/
            orderProd.add(prodID);
            orderQty.add(qty);
            subTotal.add(payment.calcSubtotal(prodID, qty));


            System.out.print("\n=========================================\n");

            System.out.print("Continue? (Y/N) : ");
            yesNo = scanner.next().charAt(0);

            if (yesNo == 'N' || yesNo == 'n') {
                System.out.println("********************");
                System.out.println("|   Order ended    |");
                System.out.println("********************\n");
            } else if (yesNo != 'Y' && yesNo != 'y') {
                System.out.println("*********************");
                System.out.println("|   Invalid Value!   |");
                System.out.println("*********************\n");

                System.out.println("Continue? (Y/N)");
                yesNo = scanner.next().charAt(0);
            }

        } while (yesNo == 'Y' || yesNo == 'y');

        /*To show ordered product and price before payment */
        for (int i = 0; i < orderProd.size(); i++) {
            System.out.printf("Product ID = %s, Product Category = %s \n", orderProd.get(i), orderQty.get(i));
            System.out.printf("Subtotal = %.2f\n", subTotal.get(i));
        }


        /*Calculate and set Total Amount*/
        double total = 0.0;

        for (Double aDouble : subTotal) {
            total += aDouble;
        }
        if (total > 500) {
            payment.setDiscount_rate(30);
        } else if (total > 300) {
            payment.setDiscount_rate(20);
        } else if (total > 100) {
            payment.setDiscount_rate(10);
        } else {
            payment.setDiscount_rate(0);
        }

        double totalAmt = payment.calcTotal(subTotal, payment.getDiscount_rate());
        System.out.printf("Total Amount : %.2f\n", totalAmt);

        orderInfo.add(new OrderInfo(cust_name, orderProd, orderQty, subTotal, totalAmt));
        orderInfo.get(index).setDiscount_rate(payment.getDiscount_rate());

        payment.transaction(index, totalAmt);

        printReceipt(index);

        backOrExit();
    }

    @Override
    public void read() {
        System.out.println("1. Show all order");
        System.out.println("2. Search order");
        System.out.print("Enter your option : ");
        int option = scanner.nextInt();

        if (option == 1) {
            showOrder(-1);
        } else if (option == 2) {
            char searchConfirm;
            do {
                System.out.print("=================================\n");
                System.out.println("\n\n\t<<Search Order>>\n");
                System.out.print("\tEnter order number to search your order: ");
                int ordernum = scanner.nextInt();
                int index = ordernum - 1;

                showOrder(index);

                System.out.print("\n\tDo u want search other Order?(Y = yes): ");
                searchConfirm = scanner.next().charAt(0);
            } while (Character.toUpperCase(searchConfirm) == 'Y');

        }
        scanner.nextLine();
        backOrExit();
    }


    @Override
    public void update() {
        boolean validation;
        String productID;
        int quantity = 0;
        int orderNo = 0;

        System.out.print("=================================\n");
        System.out.println("\n\t<<Modify Order>>\n");

        /*Show order exist*/
        for (int i = 0; i < orderInfo.size(); i++) {
            System.out.printf("Order No: %d\t\t\tCustomer Name: %s\n", i + 1, orderInfo.get(i).getCustName());
        }

        System.out.print("\tEnter Order No to modify: ");
        int ordernum = scanner.nextInt();

        /*Check index of order to modify from orderList*/
        orderNo = -1;
        for (int i = 0; i < orderInfo.size(); i++) {
            if (ordernum - 1 == orderInfo.get(i).getOrderMo()) {
                orderNo = i;
            }
        }
        scanner.nextLine();
        showOrder(orderNo);
        int prodNo = 0;
        /*If product in order is more than one*/
        if (orderInfo.get(orderNo).getProd_id().size() > 1) {
            System.out.print("\nEnter number of product to modify: ");
            int productNo = scanner.nextInt();
            prodNo = productNo - 1;
        }
        if (orderNo >= 0) {
            do {
                validation = false;
                System.out.println("\nOld ProductID: " + orderInfo.get(orderNo).getProd_id().get(prodNo));
                System.out.print("\tEnter New ProductID: ");
                productID = scanner.next();

                for (Product menuList1 : menuList) {
                    if (menuList1 != null) {
                        if (productID.equals(menuList1.getProd_ID())) {
                            validation = true;
                        }
                    }
                }
                if (!validation) {

                    System.out.print("\n\tNo This Product!\n");
                    System.out.print("\t================\n\n");
                }
            } while (!validation);


            do {
                validation = false;
                try {
                    System.out.println("\nOld Quantity: " + orderInfo.get(orderNo).getProd_qty().get(prodNo));
                    System.out.print("\tEnter New Quantity: ");
                    quantity = scanner.nextInt();

                } catch (InputMismatchException ex) {
                    System.out.println("\n\tPlease Enter Number of New Quantity!");
                    System.out.print("\t====================================\n\n");
                    scanner.nextLine();
                    validation = true;
                }
            } while (validation);

            System.out.println("\n\nAfter Modify");
            for (int i = 0; i < 40; i++) {
                System.out.print("=");
            }
            System.out.printf("\nProduct No: %d\n", prodNo + 1);
            System.out.printf("Product ID: %s\n", productID);
            System.out.printf("Product Qty: %d\n", quantity);

            char confirm;
            do {
                for (int i = 0; i < 40; i++) {
                    System.out.print("=");
                }
                System.out.print("\nConfirm to modify?(Y/N): ");
                confirm = scanner.next().charAt(0);

                if (confirm == 'N' || confirm == 'n') {
                    System.out.println("\n**************************");
                    System.out.println("*      Invalid Value!    *");
                    System.out.println("**************************");
                }
            } while (Character.toUpperCase(confirm) != 'Y' && Character.toUpperCase(confirm) != 'N');

            if (confirm == 'Y' || confirm == 'y') {
                /*Set latest id and qty*/
                orderInfo.get(orderNo).getProd_id().set(prodNo, productID);
                orderInfo.get(orderNo).getProd_qty().set(prodNo, quantity);
                orderInfo.get(orderNo).getSubtotal().set(prodNo, payment.calcSubtotal(productID, quantity));

                /*Calculate Total Amount after modify*/
                double total = 0.0;
                for (Double aDouble : orderInfo.get(orderNo).getSubtotal()) {
                    total += aDouble;
                }
                if (total > 500) {
                    payment.setDiscount_rate(30);
                } else if (total > 300) {
                    payment.setDiscount_rate(20);
                } else if (total > 100) {
                    payment.setDiscount_rate(10);
                } else {
                    payment.setDiscount_rate(0);
                }

                double totalAmt = payment.calcTotal(orderInfo.get(orderNo).getSubtotal(), payment.getDiscount_rate());

                System.out.printf("\nDiscount Rate (%%%d)  : %.2f\n", orderInfo.get(orderNo).getDiscount_rate(), total / orderInfo.get(orderNo).getDiscount_rate());
                System.out.printf("Service Charge (%%%d) : %.2f\n", OrderInfo.getService_charge(), total / OrderInfo.getService_charge());
                System.out.println("=======================================");
                System.out.printf("\nTotal Amount :        %.2f\n", totalAmt);

                System.out.println("Order Updated.\n");


                orderInfo.get(orderNo).setTotalAmount(totalAmt);
                payment.transaction(orderNo, totalAmt);

                printReceipt(orderNo);
            }
        } else {
            System.out.println("No record found!");
        }
        backOrExit();
    }

    @Override
    public void delete() {
        int ordernum;
        char deleteConfirm;

        setline(25, '=');
        System.out.println("\n\t<<Delete Product>>");
        showOrder(-1);


        System.out.print("\n\tEnter ordernum to delete: ");
        ordernum = scanner.nextInt();

        showOrder(ordernum - 1);

        System.out.print("\tConfirm to Delete Order show above?(Y = yes): ");
        deleteConfirm = scanner.next().charAt(0);

        if (Character.toUpperCase(deleteConfirm) == 'Y') {
            for (int i = 0; i < orderInfo.size(); i++) {
                if (orderInfo.get(i).getOrderMo() == ordernum - 1) {
                    orderInfo.remove(ordernum - 1);

                    System.out.println("Delete successful");
                }
            }
        } else {
            System.out.print("\tDelete Canceled !!!");
        }

        backOrExit();
    }

    public void showOrder(int index) {
        ArrayList<OrderInfo> orderInfo = Store.getOrderList();
        double total = 0.0;

        if (index >= 0) {
            for (Double subtotal : orderInfo.get(index).getSubtotal()) {
                total += subtotal;
            }
            setline(55, '-');
            System.out.printf("\n\nOrder No: %d\t\tCustomer Name: %s\n", orderInfo.get(index).getOrderMo() + 1, orderInfo.get(index).getCustName());
            System.out.println("No.\tProduct ID\t\tProduct QTY\t\tProduct Subtotal");
            setline(55, '-');
            for (int j = 0; j < orderInfo.get(index).getProd_id().size(); j++) {
                System.out.printf("\n%-2d\t%-15s\t\t%-3d\t\t\t%6.2f", j + 1, orderInfo.get(index).getProd_id().get(j), orderInfo.get(index).getProd_qty().get(j), orderInfo.get(index).getSubtotal().get(j));
            }
            System.out.println();
            setline(55, '-');
            System.out.printf("\n\t\t\t\tDiscount Rate(%-2d%%) %8.2f\n", orderInfo.get(index).getDiscount_rate(), total / orderInfo.get(index).getDiscount_rate());
            System.out.printf("\t\t\t\tService Charge(%2d%%)%8.2f\n", OrderInfo.getService_charge(), total / OrderInfo.getService_charge());
            setline(55, '-');
            System.out.printf("\n\t\t\t\tTotal Amount        %8.2f\n", orderInfo.get(index).getTotalAmount());
            setline(55, '-');
            System.out.println("\n");

        } else {
            for (OrderInfo value : orderInfo) {
                for (Double subtotal : value.getSubtotal()) {
                    total += subtotal;
                }
            }

            for (OrderInfo info : orderInfo) {
                for (Double subtotal : info.getSubtotal()) {
                    total += subtotal;
                }
                setline(55, '-');
                System.out.printf("\nOrder No: %d\t\tCustomer Name: %s\n", info.getOrderMo() + 1, info.getCustName());
                System.out.println("No.\tProduct ID\t\tProduct QTY\t\tProduct Subtotal");
                setline(55, '-');
                for (int j = 0; j < info.getProd_id().size(); j++) {
                    System.out.printf("\n%-2d\t%-15s\t\t%-3d\t\t\t%6.2f", j + 1, info.getProd_id().get(j), info.getProd_qty().get(j), info.getSubtotal().get(j));
                }
                System.out.println();
                setline(55, '-');
                System.out.printf("\n\t\t\t\tDiscount Rate(%-2d%%) %8.2f\n", info.getDiscount_rate(), total / info.getDiscount_rate());
                System.out.printf("\t\t\t\tService Charge(%2d%%)%8.2f\n", OrderInfo.getService_charge(), total / OrderInfo.getService_charge());
                setline(55, '=');
                System.out.printf("\n\t\t\t\tTotal Amount        %8.2f\n", info.getTotalAmount());
                setline(55, '=');
                System.out.println("\n");
            }
        }
    }

    public void printReceipt(int index) {
        char printRcp;
        do {
            System.out.print("Need to print receipt? (Y/N) : ");
            printRcp = scanner.next().charAt(0);

            if (Character.toUpperCase(printRcp) == 'Y') {
                DateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
                Date dateObj = new Date();
                double total = 0.0;
                for (Double subtotal : orderInfo.get(index).getSubtotal()) {
                    total += subtotal;
                }

                setline(56, '-');
                System.out.println("\n|  \t\t\t\t\t\tReceipt\t\t\t\t\t\t  |");
                System.out.println("|  \t\t\t\t\t" + df.format(dateObj) + "\t\t\t\t  |");
                setline(56, '-');
                System.out.printf("\n| Receipt No:   REC00%d\t\t\t\t\t\t\t\t   |\n", index + 1);
                System.out.printf("| Cashier :     %-20s\t\t\t\t   |\n", Main.getCurrentUser());
                setline(56, '-');
                System.out.println("\n| No   \t\tITEM\t\t    PRICE     QTY   SUBTOTAL |");
                setline(56, '-');

                String prodName = "";
                double prodPice = 0.0;
                for (int j = 0; j < orderInfo.get(index).getProd_id().size(); j++) {
                    for (Product product : menuList) {
                        if (product.getProd_ID().equals(orderInfo.get(index).getProd_id().get(j))) {
                            prodName = product.getProd_Name();
                            prodPice = product.getProd_Price();
                        }
                    }
                    System.out.printf("\n| %-2d   %-20s   %-7.2f   %-3d   %8.2f |\n", j + 1, prodName, prodPice, orderInfo.get(index).getProd_qty().get(j), orderInfo.get(index).getSubtotal().get(j));
                }
                setline(56, '=');
                System.out.printf("\n| \t\t\t\t\t\t\t   Total Amount | %8.2f |\n", total);
                setline(56, '=');
                System.out.printf("\n| \t\t\t\t\t\t  Discount Rate(%-2d%%)  %8.2f |\n", orderInfo.get(index).getDiscount_rate(), total / orderInfo.get(index).getDiscount_rate());
                System.out.printf("| \t\t\t\t\t\t  Service Charge(%2d%%) %8.2f |\n", OrderInfo.getService_charge(), total / OrderInfo.getService_charge());
                System.out.printf("| \t\t\t\t\t\t  Bill Total          %8.2f |\n", orderInfo.get(index).getTotalAmount());
                System.out.printf("| \t\t\t\t\t\t  Paid Amount         %8.2f |\n", orderInfo.get(index).getPayAmount());
                System.out.printf("| \t\t\t\t\t\t  Balance             %8.2f |\n", Math.abs(orderInfo.get(index).getBalance()));
                setline(56, '-');
            } else if (Character.toUpperCase(printRcp) == 'N') {
                backOrExit();
            } else {
                System.out.println("Invalid Value! Please enter again.");
            }
        } while (Character.toUpperCase(printRcp) != 'Y' && Character.toUpperCase(printRcp) != 'N');
    }

}


