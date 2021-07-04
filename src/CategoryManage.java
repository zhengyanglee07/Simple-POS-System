import java.lang.reflect.Array;
import java.util.*;

public class CategoryManage extends Action {
    Scanner sc = new Scanner(System.in);
    ArrayList<Category> category = Store.getCategoryList();
    ArrayList<Product> menu = Store.getMenuList();

    public CategoryManage(){
    }

    @Override
    public void create() {

        String addCategID="";
        String addCategDesc="";
        double addMinPrice=0.0;
        double addMaxPrice=0.0;
        char yesN;

        do{
            titleCreate();

            System.out.printf("\n\nEnter the category ID 		    : ");
            addCategID=sc.nextLine();

            System.out.printf("Enter the category description  : ");
            addCategDesc=sc.nextLine();

            do{
                System.out.printf("Enter the minimum price		    : ");
                addMinPrice=sc.nextDouble();

                System.out.printf("Enter the maximum price 		: ");
                addMaxPrice=sc.nextDouble();

                if(addMinPrice < 0 && addMaxPrice < 0){
                    System.out.print("\n\n**************************************************\n");
                    System.out.print("|Min Price and Max Price value can not be negative|");
                    System.out.print("\n**************************************************\n\n");
                }
                else if(addMinPrice > addMaxPrice){
                    System.out.print("\n\n**************************************************\n");
                    System.out.print("|Min Price value can not greather than Max Price.|");
                    System.out.print("\n**************************************************\n\n");
                }

            }while(addMinPrice > addMaxPrice || addMinPrice < 0 && addMaxPrice < 0);

            System.out.printf("\n\nConfirm Add this Category?\n");
            System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
            yesN = sc.next().charAt(0);

            if(yesN=='Y' || yesN=='y'){
                category.add(new Category(addCategID.toUpperCase(), addCategDesc, addMinPrice ,addMaxPrice));

                showAllCategory();

                System.out.print("\n\n########################\n");
                System.out.print("#Add Category Sucessful#\n");
                System.out.print("########################\n\n");
            }
            else if(yesN =='N' || yesN == 'n'){
                System.out.print("\n\n*******************\n");
                System.out.print("*Add Category Fail*\n");
                System.out.print("*******************\n\n");
            }else{
                System.out.print("\n\n********************\n");
                System.out.print("*  Invalid Value!  *\n");
                System.out.print("********************\n\n");
            }
            System.out.printf("\n\nAre you want to continue add Category?\n");
            System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
            yesN = sc.next().charAt(0);

            sc.nextLine();

        }while(yesN == 'Y' || yesN == 'y');


        backOrExit();
    }

    @Override
    public void read() {
        titleRead();

        if(category.size() == 0){
            System.out.print("\n\n******************\n");
            System.out.print("|No Record Inside|\n");
            System.out.print("******************\n\n");
        }

        else{

            showAllCategory();
        }

        backOrExit();

    }

    @Override
    public void update() {
        String updateCategID="";
        String updateDesc="";
        double updateMinP=0.00;
        double updateMaxP=0.00;
        int count=0;
        char yesN2;

        titleModify();
        if(category.size() != 0){

            do{
                showAllCategory();

                System.out.println("Which record are you want to change?");
                System.out.print("Choose the number : ");
                int chsCateg = sc.nextInt();

                int j = chsCateg-1;

                if(category.size() > j && chsCateg !=0){

                    System.out.println("\n\n==================================");
                    System.out.println("||			Modify Table	  	||");
                    System.out.println("==================================");
                    System.out.println("||1. Modify Category ID		  	||");
                    System.out.println("||2. Modify Category Description||");
                    System.out.println("||3. Modify Category Min Price  ||");
                    System.out.println("||4. Modify Category Max Price  ||");
                    System.out.println("||5. Modify All					||");
                    System.out.println("||6. Back						||");
                    System.out.println("==================================");
                    System.out.print("\nChoose the number : ");
                    int chs = sc.nextInt();

                    sc.nextLine();

                    switch(chs){
                        case 1:

                            for(int n=0; n < menu.size(); n++){
                                if(menu.get(n).getCateg_ID() == category.get(j).getCategID()){
                                    count++;
                                }
                            }
                            if(count == 0){

                                System.out.println("\n\nOld Category ID --> "+category.get(j).getCategID());
                                System.out.printf("\nEnter the new Category ID : ");
                                updateCategID = sc.nextLine().toUpperCase();

                                updateDesc = category.get(j).getCategDesc();
                                updateMinP = category.get(j).getMin_price();
                                updateMaxP = category.get(j).getMax_price();

                            }
                            else{
                                System.out.println("\n\n******************************************************");
                                System.out.println("*This category ID inside got product can not modify. *");
                                System.out.println("*If want modify the categoryID has no product inside.*");
                                System.out.println("******************************************************\n");
                            }
                            break;

                        case 2:
                            System.out.println("\n\nOld Category Description --> "+category.get(j).getCategDesc());
                            System.out.printf("\nEnter the modify Description : ");
                            updateDesc = sc.nextLine();

                            updateCategID = category.get(j).getCategID();
                            updateMinP = category.get(j).getMin_price();
                            updateMaxP = category.get(j).getMax_price();

                            break;

                        case 3:
                            do{
                                System.out.println("\n\nOld Category Min Price --> "+category.get(j).getMin_price());
                                System.out.println("\n\nOld Category Max Price --> "+category.get(j).getMax_price());
                                System.out.printf("\nEnter the new Min Price : ");
                                updateMinP = sc.nextDouble();

                                if(category.get(j).getMax_price() < updateMinP){

                                    System.out.print("\n\n*******************************************\n");
                                    System.out.print("|Min Price can not greater than Max Price|\n");
                                    System.out.print("*******************************************\n\n");
                                }
                            }while(category.get(j).getMax_price()< updateMinP);

                            updateCategID = category.get(j).getCategID();
                            updateDesc = category.get(j).getCategDesc();
                            updateMaxP = category.get(j).getMax_price();

                            break;

                        case 4:
                            do{
                                System.out.println("\n\nOld Category Min Price --> "+category.get(j).getMin_price());
                                System.out.println("\n\nOld Category Max Price --> "+category.get(j).getMax_price());
                                System.out.printf("\nEnter the new Max Price : ");
                                updateMaxP = sc.nextDouble();

                                if(category.get(j).getMin_price() > updateMaxP){

                                    System.out.print("\n\n*******************************************\n");
                                    System.out.print("|Min Price can not greater than Max Price|\n");
                                    System.out.print("*******************************************\n\n");

                                }

                            }while(category.get(j).getMin_price() > updateMaxP);

                            updateCategID = category.get(j).getCategID();
                            updateDesc = category.get(j).getCategDesc();
                            updateMinP = category.get(j).getMin_price();

                            break;
                        case 5:

                            for(int n=0; n < menu.size(); n++){
                                if(menu.get(n).getCateg_ID() == category.get(j).getCategID()){
                                    count++;
                                }
                            }
                            if(count == 0){
                                System.out.println("\n\nOld Category ID --> "+category.get(j).getCategID());
                                System.out.println("Old Category Description --> "+category.get(j).getCategDesc());
                                System.out.println("Old Category Min Price --> "+category.get(j).getMin_price());
                                System.out.println("Old Category Max Price --> "+category.get(j).getMax_price());

                                System.out.printf("\nEnter the modify ID 			: ");
                                updateCategID = sc.nextLine();

                                System.out.printf("Enter the modify Description 	: ");
                                updateDesc = sc.nextLine();

                                do{

                                    System.out.printf("Enter the new Min Price 		: ");
                                    updateMinP = sc.nextDouble();

                                    System.out.printf("Enter the new Max Price 		: ");
                                    updateMaxP = sc.nextDouble();

                                    if(updateMinP > updateMaxP){

                                        System.out.print("\n\n*******************************************\n");
                                        System.out.print("|Min Price can not greater than Max Price|\n");
                                        System.out.print("*******************************************\n\n");

                                    }
                                }while(updateMinP > updateMaxP);
                            }
                            else{
                                System.out.println("\n\n******************************************************");
                                System.out.println("*This category ID inside got product can not modify. *");
                                System.out.println("*If want modify the categoryID has no product inside.*");
                                System.out.println("******************************************************\n");
                            }
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

                    if(chs > 0 && chs < 5 && count ==0){
                        System.out.println("\n\nConfirm Modify?");
                        System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
                        char yesN = sc.next().charAt(0);

                        if(yesN == 'Y' || yesN == 'y'){
                            category.set(j, new Category(updateCategID, updateDesc, updateMinP ,updateMaxP));

                            showAllCategory();

                            System.out.print("\n############################\n");
                            System.out.print("#Modify Category Successful#\n");
                            System.out.print("############################\n");
                        }
                        else if(yesN == 'N' || yesN == 'n'){
                            System.out.print("\n\n*************\n");
                            System.out.print("|Modify Fail|\n");
                            System.out.print("*************\n\n");

                        }else{
                            System.out.print("\n\n*************************************\n");
                            System.out.print("* Invalid Value! Please enter again *\n");
                            System.out.print("*************************************\n\n");
                        }
                    }

                }
                else{

                    System.out.print("\n\n********************\n");
                    System.out.print("|Unknown Catgory ID|\n");
                    System.out.print("********************\n\n");
                }
                count=0;
                System.out.println("\n\nDo you want to Modify another category?");
                System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
                yesN2 = sc.next().charAt(0);

                sc.nextLine();
            }while(yesN2 == 'Y' || yesN2 == 'y');


        }
        else{
            System.out.print("\n\n******************\n");
            System.out.print("|No Record Inside|\n");
            System.out.print("******************\n\n");
        }

        backOrExit();

    }

    @Override
    public void delete(){
        int countDtlP = 0;
        int chsDt;
        char yesN;
        int i;


        if(category.size() == 0){
            System.out.print("\n\n*********************************\n");
            System.out.print("|No Record Inside Can not Remove|\n");
            System.out.print("*********************************\n\n");
        }
        else{
            do{
                if(category.size() == 0){
                    System.out.print("\n\n*********************************\n");
                    System.out.print("|No Record Inside Can not Remove|\n");
                    System.out.print("*********************************\n\n");
                }
                else{

                    do{
                        titleDelete();

                        showAllCategory();

                        System.out.println("Which record is you want remove?");
                        System.out.print("Choose the number : ");
                        chsDt = sc.nextInt();

                        i = chsDt-1;
                        if( i < 0 || i > category.size()){
                            System.out.print("\n\n**************************************\n");
                            System.out.print("* Invalid Value! Please enter again. *\n");
                            System.out.print("***************************************\n\n");
                        }

                    }while(i < 0 || i > category.size());

                    for(int j=0; j < menu.size(); j++){
                        if(category.get(i).getCategID() == category.get(j).getCategID()){
                            countDtlP++;
                            countDtlP=+countDtlP;
                        }
                    }
                    System.out.printf("\n\nThis %s category ID got %d quantity product.",category.get(i).getCategID(),countDtlP);

                    System.out.println("\n\nCategory ID 		 : "+category.get(i).getCategID());
                    System.out.println("Category Description : "+category.get(i).getCategDesc());
                    System.out.println("Category Min Price 	 : "+category.get(i).getMin_price());
                    System.out.println("Category Max Price 	 : "+category.get(i).getMax_price());

                    for(int x=0; x < menu.size(); x++){
                        if(category.get(i).getCategID() == menu.get(x).getCateg_ID()){
                            System.out.println("\n\nProduct ID    : " + menu.get(x).getProd_ID());
                            System.out.println("Product Name  : " + menu.get(x).getProd_Name());
                            System.out.println("Product Price : " + menu.get(x).getProd_Price());
                        }
                    }

                    if(countDtlP != 0){
                        System.out.println("\n\n*****************************************************************************************");
                        System.out.println("*REMEMBER IF YOU DELETED CATEGORY,IT WILL ALSO DELETED ALL PRODUCT RELATED THIS CATEGORY*");
                        System.out.print("*****************************************************************************************");
                        countDtlP = 0;
                    }

                    System.out.println("\n\nAre you sure remove this record?");
                    System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
                    yesN = sc.next().charAt(0);

                    if(yesN == 'Y' || yesN == 'y'){

                        for (int z = 0; z <menu.size(); z++) {
                            if (menu.get(z).getCateg_ID().equals(category.get(i).getCategID())) {
                                menu.remove(z);
                                z=-1;
                            }
                        }

                        category.remove(i);
                        showAllCategory();

                        System.out.println("\n\n############################");
                        System.out.println("#Remove Category Successful#");
                        System.out.print("############################\n");
                    }
                    else if(yesN == 'N' || yesN == 'n'){
                        System.out.print("\n\n*************\n");
                        System.out.print("|Remove Fail|\n");
                        System.out.print("*************\n\n");

                    }else{
                        System.out.print("\n\n**************************************\n");
                        System.out.print("* Invalid Value! Please enter again. *\n");
                        System.out.print("***************************************\n\n");
                    }
                }
                System.out.println("\n\nDo you want Remove another category?");
                System.out.print("Press (Y/y) to confirm or press (N/n) to cancel)");
                yesN = sc.next().charAt(0);

                sc.nextLine();

            }while(yesN == 'Y' || yesN == 'y');

        }
        backOrExit();
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
        System.out.println("\n\n================================");
        System.out.println("||      Read All Category     ||");
        System.out.println("================================");
    }
    public void titleCreate(){
        System.out.println("\n\n===============================");
        System.out.println("||      Add New Category     ||");
        System.out.println("===============================");
    }
    public void titleModify(){
        System.out.println("================================");
        System.out.println("||       Modify Category      ||");
        System.out.println("================================");
    }
    public void titleDelete(){
        System.out.println("\n\n================================");
        System.out.println("||       Detele Category      ||");
        System.out.println("================================");
    }

}
