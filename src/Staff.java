public class Staff extends Employee{
    private String staffID;

    public Staff(){
    }

    public Staff(String Name,String Email, String Phone, String Gender, String BirthDate, String Password) {
        super(Name,Email,Phone,Gender,BirthDate,Password);
        if(getAutoGenerate() >= 1000) {
            staffID = new String(String.valueOf('S' + getAutoGenerate()));
        }
        else if(getAutoGenerate() >= 100) {
            staffID = new String(String.valueOf('S' + "0" + getAutoGenerate()));
        }
        else if(getAutoGenerate() >= 10) {
            staffID = new String(String.valueOf('S' + "00" + getAutoGenerate()));
        }
        else {
            staffID = new String(String.valueOf('S' + "000" + getAutoGenerate()));
        }

    }



    /*----------------------------------------------------------------------*/
    /*------------ Getter and Setter for attribute in Staff class-----------*/
    /*----------------------------------------------------------------------*/

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
}

