import java.util.Scanner;

public abstract class Action {
    Scanner scanner = new Scanner(System.in);

    public abstract void create();

    public abstract void read();

    public abstract void update();

    public abstract void delete();

    public void actionOption(){
        Scanner scanner = new Scanner(System.in);
        int option;
        do{
            setline(25,'-');
            System.out.println("\n| 1. | Add              |");
            System.out.println("| 2. | Read             |");
            System.out.println("| 3. | Update           |");
            System.out.println("| 4. | Delete           |");
            System.out.println("| 5. | Back             |");
            setline(25,'-');
            System.out.print("\nPlease enter your choice: ");
            option = scanner.nextInt();

            if(option <1 || option >5){
                System.out.println("***************************************");
                System.out.println("| Invalid Option! Please enter again! |");
                System.out.println("***************************************");
            }

        }while(option <1 || option >5);
        scanner.nextLine();

        switch (option) {
            case 1 : create();break;
            case 2 : read();break;
            case 3 : update();break;
            case 4 : delete();break;
            case 5 : {
                Main.setLogin(true);
                Main.main(null);
                break;
            }
        }
    }

    /*---------------------------------------------------*/
    /*----Back to module option or exit system option----*/
    /*---------------------------------------------------*/
    public void backOrExit(){
        int option;
        do{
            System.out.println("\n\n1. Back To Module");
            System.out.println("2. Exit System");
            System.out.print("Enter your option: ");
            option = scanner.nextInt();
            if(option == 1){
                if(Main.getPosition() == 1){
                    actionOption();
                }else{
                    Main.setLogin(true);
                    Main.main(null);
                }
            }else if(option ==2){
                System.exit(0);
            }else{
                System.out.print("\n\n************************************\n");
                System.out.print("|Invalid Value! Please enter again.|\n");
                System.out.print("************************************\n\n");
            }
        }while(option <1 || option >2);
    }


    public void setline(int loop, char symbol){
        for (int i = 0; i <loop ; i++) {
            System.out.print(symbol);
        }
    }


}
