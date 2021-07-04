public class Employee {

    private String name;
    private String email;
    private String phone;
    private String gender;
    private String birthDate;
    private String password;
    private static int autoGenerate = 0;

    public Employee(){

    }

    public Employee(String name, String email, String phone, String gender, String birthDate, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static int getAutoGenerate() {
        return autoGenerate;
    }

    public static void setAutoGenerate(int autoGenerate) {
        Employee.autoGenerate = autoGenerate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}