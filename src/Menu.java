import java.util.*;

public class Menu extends Action {
    Scanner sc = new Scanner(System.in);
    ArrayList<Product> menu = Store.getMenuList();
    ArrayList<Category> category = Store.getCategoryList();

    @Override
    public void create() {
        String addCategID = "";
        String addProdID = "";
        String addProdName = "";
        double addProdPrice = 0.0;
        int chooseCatID;
        char yesN;


        if(category.size() != 0){
            do {
                titleCreate();

                showAllCategory();

                do{
                    System.out.println("\nChoose the what product are belong to categories\n");
                    System.out.print("Enter the number : ");
                    chooseCatID = sc.nextInt();

                    sc.nextLine();

                    if(chooseCatID <= 0 || chooseCatID > category.size()){

                        System.out.print("\n\n*************************************\n");
                        System.out.print("|Invalid Value! Please enter again. |\n");
                        System.out.print("*************************************\n\n");

                    }
                    else{
                        addCategID = category.get(chooseCatID-1).getCategID();
                    }
                }while(chooseCatID <= 0 || chooseCatID > category.size());



                System.out.print("\n\nEnter the product ID    : ");
                addProdID = sc.nextLine();

                System.out.print("Enter the product Name  : ");
                addProdName = sc.nextLine();

                do {
                    System.out.print("Enter the product Price : ");
                    addProdPrice = sc.nextDouble();

                    if (addProdPrice < 0) {
                        System.out.print("\n\n***************************************\n");
                        System.out.print("|Product price can not insert negative|\n");
                        System.out.print("***************************************\n\n");
                    }

                } while (addProdPrice < 0);

                System.out.println("\n\nAre you sure add this product?");
                System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
                yesN = sc.next().charAt(0);

                if (yesN == 'Y' || yesN == 'y') {
                    menu.add(new Product(addCategID.toUpperCase(), addProdID, addProdName, addProdPrice));

                    showAllProduct();

                    System.out.print("\n\n##########################\n");
                    System.out.print("|Add Product Successfull!|\n");
                    System.out.print("##########################\n\n");
                } else {
                    addCategID = "";
                    addProdID = "";
                    addProdName = "";
                    addProdPrice = 0.0;

                    System.out.print("\n\n******************\n");
                    System.out.print("|Add Product Fail|\n");
                    System.out.print("******************\n\n");
                }

                System.out.println("Do you want add more another product?");
                System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
                yesN = sc.next().charAt(0);

                sc.nextLine();
            } while (yesN == 'Y' || yesN == 'y');

        }
        else{
            System.out.println("\n\n******************************************************************************");
            System.out.println("|Can not add product because the Category List has no Category ID is empty!!!|");
            System.out.println("******************************************************************************");
        }

        backOrExit();
    }

    @Override
    public void read() {
        if(menu.size() != 0){
            titleRead();
            showAllProduct();

        }
        else{

            System.out.print("\n\n******************\n");
            System.out.print("|No Record Inside|\n");
            System.out.print("******************\n\n");

        }

        backOrExit();
    }

    @Override
    public void update() {
        int updatechs = 0;
        int chsUpCatID;
        char yesNo;

        if(menu.size() == 0){
            System.out.print("\n\n******************\n");
            System.out.print("|No Record Inside|\n");
            System.out.print("******************\n\n");
        }
        else{

            do {
                titleModify();
                showAllProduct();

                System.out.println("Which record are you want to modify?");
                System.out.print("Choose the number : ");
                updatechs = sc.nextInt();

                int i = updatechs - 1;

                if (menu.size() > i && updatechs != 0) {

                    System.out.println("==================================");
                    System.out.println("||			Modify Table	  	||");
                    System.out.println("==================================");
                    System.out.println("||1. Modify Product Type		||");
                    System.out.println("||2. Modify Product ID			||");
                    System.out.println("||3. Modify Product Name		||");
                    System.out.println("||4. Modify Product Price		||");
                    System.out.println("||5. Modify All					||");
                    System.out.println("||6. Back						||");
                    System.out.println("==================================");

                    System.out.println("\nHow do you want modify?");
                    System.out.print("Choose the number : ");
                    int chstype = sc.nextInt();

                    sc.nextLine();

                    String categID = menu.get(i).getCateg_ID();
                    String prodID = menu.get(i).getProd_ID();
                    String prodName = menu.get(i).getProd_Name();
                    double prodPrice = menu.get(i).getProd_Price();

                    String updateCategID = categID;
                    String updateProdID = prodID;
                    String updateProdName = prodName;
                    double updateProdPrice = prodPrice;

                    switch (chstype) {
                        case 1:
                            showAllCategory();
                            System.out.print("Choose the new category type : ");
                            chsUpCatID = sc.nextInt();
                            sc.nextLine();

                            for(int j=0; j < category.size(); j++){
                                updateCategID = category.get(chsUpCatID-1).getCategID();
                            }
                            break;

                        case 2:
                            System.out.println("\nOld Product ID --> " + prodID);
                            System.out.print("Enter the new Product ID : ");
                            updateProdID = sc.nextLine().toUpperCase();
                            break;

                        case 3:
                            System.out.println("\nOld Product Name --> " + prodName);
                            System.out.print("Enter the new Product Name : ");
                            updateProdName = sc.nextLine();
                            break;

                        case 4:

                            do {
                                System.out.println("\nOld Product Price --> " + prodPrice);
                                System.out.print("Enter the new Product Price : ");
                                updateProdPrice = sc.nextDouble();

                                if (updateProdPrice < 0) {
                                    System.out.println("\n*******************************\n");
                                    System.out.println("|Can not insert negative value|");
                                    System.out.print("*******************************\n\n");
                                }

                            } while (updateProdPrice < 0);

                            break;

                        case 5:
                            System.out.println("\nOld Product ID    --> " + categID);
                            System.out.println("\nOld Product ID    --> " + prodID);
                            System.out.println("\nOld Product Name  --> " + prodName);
                            System.out.println("\nOld Product Price --> " + prodPrice);

                            showAllCategory();
                            System.out.print("Choose the new category type : ");
                            chsUpCatID = sc.nextInt();
                            sc.nextLine();

                            for(int j=0; j < category.size(); j++){
                                updateCategID = category.get(chsUpCatID-1).getCategID();
                            }

                            System.out.print("\n\nEnter the new Product ID : ");
                            updateProdID = sc.nextLine().toUpperCase();

                            System.out.print("Enter the new Product Name : ");
                            updateProdName = sc.nextLine();

                            do {
                                System.out.print("Enter the new Product Price : ");
                                updateProdPrice = sc.nextDouble();

                                if (updateProdPrice < 0) {
                                    System.out.println("\n*******************************");
                                    System.out.println("|Can not insert negative value|");
                                    System.out.print("*******************************\n\n");
                                }
                            } while (updateProdPrice < 0);
                            break;

                        case 6:
                            actionOption();
                            break;

                        default:
                            System.out.print("\n\n*************************************\n");
                            System.out.print("|Invalid Value! Please enter again. |\n");
                            System.out.print("*************************************\n\n");
                            break;
                    }
                    if (chstype > 0 && chstype < 6)

                        System.out.println("\n\nConfirm Modify?");
                    System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
                    char yesN = sc.next().charAt(0);

                    if (yesN == 'Y' || yesN == 'y') {

                        menu.set(i, new Product(updateCategID.toUpperCase(),updateProdID, updateProdName, updateProdPrice));

                        showAllProduct();

                        System.out.println("\n\n#################");
                        System.out.println("|Modify Complete|");
                        System.out.print("#################\n\n");
                    } else {
                        updateProdID = "";
                        updateProdName = "";
                        updateProdPrice = 0.0;

                        System.out.println("\n\n***************");
                        System.out.println("|Modify Failed|");
                        System.out.print("***************\n\n");
                    }

                } else {
                    System.out.println("\n\n*****************************************");
                    System.out.println("|This record is not exist at above table|");
                    System.out.print("*****************************************\n\n");

                }

                System.out.println("Do you want modify another product?");
                System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
                yesNo = sc.next().charAt(0);

                sc.nextLine();
            } while (yesNo == 'Y' || yesNo == 'y');
        }


        backOrExit();
    }

    @Override
    public void delete() {
        int chsPrd = 0;
        char yesN;



        if (menu.size() == 0) {
            System.out.print("\n\n**********************************\n");
            System.out.print("|No Record Inside, Can not Remove|\n");
            System.out.print("**********************************\n\n");
        } else {

            do {

                if (menu.size() == 0) {
                    System.out.print("\n\n**********************************\n");
                    System.out.print("|No Record Inside, Can not Remove|\n");
                    System.out.print("**********************************\n\n");
                } else {
                    titleDelete();

                    showAllProduct();

                    System.out.println("Which record  do you want remove?");
                    System.out.print("Choose the number on above : ");
                    chsPrd = sc.nextInt();

                    int i = chsPrd - 1;

                    if (i<0 || i > menu.size()){

                        System.out.println("\n\n*****************************************");
                        System.out.println("|This record is not exist at above table|");
                        System.out.println("*****************************************\n\n");

                    } else {

                        System.out.println("\n\nAre you sure to remove this record?");
                        System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
                        yesN = sc.next().charAt(0);

                        if (yesN == 'Y' || yesN == 'y') {

                            menu.remove(i);

                            showAllProduct();

                            System.out.println("\n\n##################");
                            System.out.println("|Remove Sucessful|");
                            System.out.println("##################\n\n");
                        }else if(yesN == 'N' || yesN == 'n'){
                            System.out.print("\n\n*************\n");
                            System.out.print("|Remove Fail|\n");
                            System.out.print("*************\n\n");

                        }else{
                            System.out.print("\n\n**************************************\n");
                            System.out.print("* Invalid Value! Please enter again. *\n");
                            System.out.print("***************************************\n\n");
                        }
                    }
                }

                System.out.println("\n\nDo you want remove another record?");
                System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
                yesN = sc.next().charAt(0);

                sc.nextLine();
            } while (yesN == 'Y' || yesN == 'y');
        }
        backOrExit();
    }

    public  void showAllProduct() {
        int count = 0;

        System.out.println("===========================================================================");
        System.out.printf("|| No | Categories ID | Product ID | %-20s | Product Price||\n", "Product Name");
        System.out.println("===========================================================================");
        for (int i = 0; i < menu.size(); i++) {
            count++;
            System.out.printf("|| %02d | %-13s | %-10s | %-20s | %-12.2f ||\n", count,menu.get(i).getCateg_ID(), menu.get(i).getProd_ID(), menu.get(i).getProd_Name(), menu.get(i).getProd_Price());
        }
        System.out.println("===========================================================================");
        System.out.printf("\nRecord %d(s)\n\n", count);
    }


    public void showAllCategory(){
        System.out.printf("\n\n================================================================\n");
        System.out.printf("|| No | Cat_ID | %-20s | Min Price | Max Price ||\n","Cat_Description");
        System.out.printf("================================================================\n");

        int no=0;
        for(int i =0; i < category.size() ;i++){
            no++;

            System.out.printf("|| %02d | %-6s | %-20s | %9.2f | %9.2f ||\n",no,category.get(i).getCategID(),category.get(i).getCategDesc(),category.get(i).getMin_price(),category.get(i).getMax_price());

        }

        System.out.printf("================================================================\n");
        System.out.printf("\nRecord %d(s)\n\n",no);

    }
    public void titleRead(){
        System.out.println("\n\n===============================");
        System.out.println("||      Read All Product     ||");
        System.out.println("===============================");
    }
    public void titleCreate(){
        System.out.println("\n\n==============================");
        System.out.println("||      Add New Product     ||");
        System.out.println("==============================");
    }
    public void titleModify(){
        System.out.println("\n\n===============================");
        System.out.println("||       Modify Product      ||");
        System.out.println("===============================");
    }
    public void titleDelete(){
        System.out.println("\n\n===============================");
        System.out.println("||       Detele Product      ||");
        System.out.println("===============================");
    }
}


