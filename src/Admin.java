public class Admin extends Employee{
    private String adminID;

    public Admin(String Name,String Email, String Phone, String Gender, String BirthDate, String Password) {
        super(Name,Email,Phone,Gender,BirthDate,Password);
        if(getAutoGenerate() >= 1000)
            adminID = new String(String.valueOf('A' + getAutoGenerate()));
        else if(getAutoGenerate() >= 100)
            adminID = new String(String.valueOf('A' + "0" + getAutoGenerate()));
        else if(getAutoGenerate() >= 10)
            adminID = new String(String.valueOf('A' + "00" + getAutoGenerate()));
        else
            adminID = new String(String.valueOf('A' + "000" + getAutoGenerate()));


    }

    /*-------------------------------------------------------------------------*/
    /*------------ Getter and Setter for attribute in Admin class--------------*/
    /*-------------------------------------------------------------------------*/

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}
