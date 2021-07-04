import java.util.Scanner;

public class Main {
    private static int position;
    private static boolean login; //Login Status
    private static String currentUser;

    public static void main(String[] args) {
        Store.store();
        Login loginValidation = new Login();
        Scanner scanner = new Scanner(System.in);

        if (!login) {// Not logged in yet
            //Option and check for position
            System.out.println("-----------------------------");
            System.out.println("| 1. | Admin                |");
            System.out.println("| 2. | Staff                |");
            System.out.println("-----------------------------");
            System.out.print("Please choose your position: ");
            position = scanner.nextInt();

            while (position != 1 && position != 2) {
                System.out.println("***************************************");
                System.out.println("| Invalid Option! Please enter again! | ");
                System.out.println("***************************************");
                System.out.println("-----------------------------");
                System.out.println("| 1. | Admin                |");
                System.out.println("| 2. | Staff                |");
                System.out.println("-----------------------------");
                System.out.print("Please choose your position: ");
                position = scanner.nextInt();
            }

            //Set Position
            if (position == 1) {
                setPosition(1);
            } else {
                setPosition(2);
            }
            scanner.nextLine();

            //Login
            String uName, password;
            boolean validLogin;
            do {
                System.out.println("\n............................................");
                System.out.print("Enter your username: ");
                uName = scanner.nextLine();

                System.out.print("Enter your password: ");
                password = scanner.nextLine();
                System.out.println("............................................\n");
            } while (!loginValidation.login(uName, password));

            /*Set current user after login successful*/
            setCurrentUser(uName);

        }
        // Module Option For Admin
        if (position == 1) {
            int option;
            do{
                System.out.println("--------------------------------");
                System.out.println("| 1. | Category                |");
                System.out.println("| 2. | Menu                    |");
                System.out.println("| 3. | Staff                   |");
                System.out.println("| 4. | Order                   |");
                System.out.println("| 5. | Summary                 |");
                System.out.println("| 6. | Logout                  |");
                System.out.println("--------------------------------");
                System.out.print("Please enter your option: ");
                option = scanner.nextInt();
                System.out.println("................................");

                if (option == 1) {
                    Action action = getModule("categories");
                    action.actionOption();
                } else if (option == 2) {
                    Action action = getModule("menu");
                    action.actionOption();
                } else if (option == 3) {
                    Action action = getModule("staffManage");
                    action.actionOption();
                }else if(option == 4){
                    Action action = getModule("order");
                    action.actionOption();
                }else if (option == 5) {
                    Summary summary = new Summary();
                } else if(option == 6){
                    setLogin(false);
                    Main.main(null);
                }else{
                    System.out.println("***************************************");
                    System.out.println("| Invalid Option! Please enter again! | ");
                    System.out.println("***************************************");
                }
            }while(option <1 || option >5);
        }
        // Module Option For Staff
        else {
            int option;
            do{
                System.out.println("--------------------------------");
                System.out.println("| 1. | Category                |");
                System.out.println("| 2. | Menu                    |");
                System.out.println("| 3. | Order                   |");
                System.out.println("| 4. | Logout                  |");
                System.out.println("--------------------------------");
                System.out.print("Please enter your option: ");
                option = scanner.nextInt();

                if (option == 1) {
                    Action action = getModule("categories");
                    action.read();
                } else if (option == 2) {
                    Action action = getModule("menu");
                    action.read();
                } else if (option == 3) {
                    Action action = getModule("order");
                    action.actionOption();
                } else if(option == 4){
                    setLogin(false);
                    Main.main(null);
                }else{
                    System.out.println("***************************************");
                    System.out.println("| Invalid Option! Please enter again! | ");
                    System.out.println("***************************************");
                }
            }while(option <1 || option > 4);
        }


    }


    /*-------------------*/
    /* Get Module Method */
    /*-------------------*/
    public static Action getModule(String option) {
        if (option.equals("categories")) {
            return new CategoryManage();
        } else if (option.equals("menu")) {
            return new Menu();
        } else if(option.equals("staffManage")){
            return new StaffManage();
        }else{
            return new Order();
        }

    }


    /*---------------------------------------------*/
    /*Getter and Setter for attribute in Main class*/
    /*---------------------------------------------*/
    public static boolean isLogin() {
        return login;
    }

    public static void setLogin(boolean login) {
        Main.login = login;
    }

    public static void setPosition(int position) {
        Main.position = position;
    }

    public static int getPosition() {
        return position;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        Main.currentUser = currentUser;
    }
}
