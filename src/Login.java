import java.util.Scanner;

public class Login {
    Scanner scanner = new Scanner(System.in);
    private String uname, password;

    public boolean login(String uname, String password ){
        this.uname = uname;
        this.password = password;
        //Login
         if(Main.getPosition() == 1){
            return validationAdmin();
        }else{
            return validationStaff();
        }
    }



    // Login Validation for Admin
    public boolean validationAdmin() {
        Admin admin = Store.getAdminList().get(0);
        if(uname.equals(admin.getName()) && password.equals(admin.getPassword())){
            System.out.println("********************");
            System.out.println("| Login Successful |");
            System.out.println("********************\n");
            return true;
        }else{
            System.out.println("************************************************************");
            System.out.println("| Admin Username or password is wrong! Please enter again! |");
            System.out.println("************************************************************");

            return false;
        }
    }

    // Login Validation for Staff
    public boolean validationStaff() {
        Staff staff = new Staff();
        int index = 0;
        for(int i=0; i < Store.getStaffList().size(); i++){
            staff =Store.getStaffList().get(i);
            if(uname.equals(staff.getName())){
                index = i;
            }
        }
        staff = Store.getStaffList().get(index);
        if(uname.equals(staff.getName()) && password.equals(staff.getPassword())){
            System.out.println("********************");
            System.out.println("| Login Successful |");
            System.out.println("********************\n");
            return true;
        }else{
            System.out.println("************************************************************");
            System.out.println("| Staff Username or password is wrong! Please enter again! |");
            System.out.println("************************************************************");
            return false;
        }
    }


    // Getter and Setter for attribute in Login class
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
