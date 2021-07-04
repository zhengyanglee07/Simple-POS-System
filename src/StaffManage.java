import java.util.Scanner;
import java.util.*;
import java.util.regex.Pattern;

public class StaffManage extends Action{
    ArrayList<Staff> staff = Store.getStaffList();
    boolean check;
    
    
    public StaffManage() {
    }

    @Override
    public void create() {
        String addName = "";
        String addEmail = "";
        String addPhone = "";
        String addGender = "";
        String addBirthDate = "";
        String addPassword = "";
        String confirmation = "";
        String confirmationYes ="Y";



        System.out.println("Add Staff");
        System.out.println("==========================================");
     
        //NAME
        do
        {
            check=false;
            
            System.out.print("Enter Employee Name : ");
            addName = scanner.nextLine();
            
            if(isName(addName))
            {
                check = true;
            }
            else
            {
                System.out.println("Invalid Name, Please enter character only!"); 
            }
            
        }while(!check);
       
       //EMAIL
       do
        {
            check=false;
            
            System.out.print("Enter Employee Email : ");
            addEmail = scanner.nextLine();
            
            if(isEmail(addEmail))
            {
                check = true;
            }
            else
            {
                System.out.println("Invalid Email Format!!"); 
            }   
        }while(!check);
       
      //PHONE
      do
        {
            check=false;
            
            System.out.print("Enter Employee Phone : ");
            addPhone = scanner.nextLine();
            
            if(isPhone(addPhone))
            {
                check = true;
            }
            else
            {
                System.out.println("Invalid Phone Format!!"); 
            }   
        }while(!check);
       
      //GENDER
        do
        {
            check=false;
            
            System.out.print("Enter Employee Gender (M / F) : ");
            addGender = scanner.nextLine();
            
            if(isGender(addGender))
            {
                check = true;
            }
            else
            {
                System.out.println("Invalid Gender!!"); 
            }   
        }while(!check);
        
        //BIRTHDATE
        do
        {
            check=false;
            
            System.out.print("Enter Employee BirthDate (00-00-0000) : ");
            addBirthDate = scanner.nextLine();
            
            if(isBirthDate(addBirthDate))
            {
                check = true;
            }
            else
            {
                System.out.println("Invalid BirthDate!!!"); 
            }   
        }while(!check);
        
        //PASSWORD
        System.out.print("Enter Employee Password : ");
        addPassword = scanner.nextLine();

        System.out.print("\n==========================================\n");

        System.out.print("Do you want to make this changes? (Y or N) : ");
        confirmation = scanner.next();

            if(confirmation.equals(confirmationYes)){

                staff.add(new Staff(addName,addEmail,addPhone,addGender,addBirthDate,addPassword));
                System.out.println("Employee added!!!");
            }
            else{

                System.out.println("Add Employee cancelled! ");
            }

            //Rewind
            scanner.nextLine();

            backOrExit();
     }
    
    

    @Override
    public void read() {
        /*Read: staff.get(index);*/
        showAllStaff();

        backOrExit();
    }

    @Override
    public void update() {
        /*Update: staff.set(index, new Value)*/


        String updateName = "";
        String updateEmail = "";
        String updatePhone = "";
        String updateGender = "";
        String updateBirthDate = "";
        String updatePassword = "";
        int choice ;
        String updateConfirmation = "";

        showAllStaff();

        System.out.println("Update Staff");
        System.out.println("==========================================\n");


        System.out.print("Please select the employee's (number) you want to edit : ");
        choice = scanner.nextInt();

        //Rewind
        scanner.nextLine();
        if(staff.size() >= choice || staff.size() <= 0)
        {


                    //NAME
        do
        {
            check=false;
            
            System.out.print("Enter Employee Name : ");
            updateName = scanner.nextLine();
            
            if(isName(updateName))
            {
                check = true;
            }
            else
            {
                System.out.println("Invalid Name, Please enter character only!"); 
            }
            
        }while(check != true);
       
       //EMAIL
       do
        {
            check=false;
            
            System.out.print("Enter Employee Email : ");
            updateEmail = scanner.nextLine();
            
            if(isEmail(updateEmail))
            {
                check = true;
            }
            else
            {
                System.out.println("Invalid Email Format!!"); 
            }   
        }while(!check);
       
      //PHONE
      do
        {
            check=false;
            
            System.out.print("Enter Employee Phone : ");
            updatePhone = scanner.nextLine();
            
            if(isPhone(updatePhone))
            {
                check = true;
            }
            else
            {
                System.out.println("Invalid Phone Format!!"); 
            }   
        }while(!check);
       
      //GENDER
        do
        {
            check=false;
            
            System.out.print("Enter Employee Gender (M / F) : ");
            updateGender = scanner.nextLine();
            
            if(isGender(updateGender))
            {
                check = true;
            }
            else
            {
                System.out.println("Invalid Gender!!"); 
            }   
        }while(!check);
        
        //BIRTHDATE
        do
        {
            check=false;
            
            System.out.print("Enter Employee BirthDate (00-00-0000) : ");
            updateBirthDate = scanner.nextLine();
            
            if(isBirthDate(updateBirthDate))
            {
                check = true;
            }
            else
            {
                System.out.println("Invalid BirthDate!!!"); 
            }   
        }while(!check);
        
        //PASSWORD
        System.out.print("Enter Employee Password : ");
        updatePassword = scanner.nextLine();

            System.out.print("\n==========================================\n");

            System.out.print("Do you want to make this changes? (Y or N) : ");
            updateConfirmation = scanner.next();

            if(updateConfirmation.equals("Y") || updateConfirmation.equals("y") ){
                staff.set(choice-1, new Staff(updateName,updateEmail,updatePhone,updateGender,updateBirthDate,updatePassword));

                System.out.println("Employee changes updated");

            }
            else{

                System.out.println("update Employee cancelled! ");
            }
            //Rewind
            scanner.nextLine();
        }
        else
        {
            System.out.println("Unknown employee!!!");
        }

        backOrExit();
    }

    @Override
    public void delete() {
        /*Delete: staff.remove(index);*/


        int deleteChoice ;
        String deleteConfirmation = "";

        showAllStaff();

        System.out.print("Please select the employee(s) you want to delete : ");
        deleteChoice = scanner.nextInt();

        //Rewind
        scanner.nextLine();

        if(staff.size() >= deleteChoice || staff.size() <= 0)
        {
            System.out.print("Do you want to make this changes? (Y or N) : ");
            deleteConfirmation = scanner.next();

            if(deleteConfirmation.equals("Y") || deleteConfirmation.equals("y") ){


                staff.remove(deleteChoice-1);
                System.out.println("Employee deleted");

            }
            else{

                System.out.println("Delete Employee cancelled! ");
            }
            //Rewind
            scanner.nextLine();
        }
        else
        {
            System.out.println("Unknown employee!!!");
        }


        backOrExit();
    }

    public void showAllStaff(){
        for (int i = 0; i < staff.size(); i++){
            System.out.println("\n Employee " +(i+1));
            System.out.println("==========================================\n");
            System.out.println("StaffID :   " + staff.get(i).getStaffID());
            System.out.println("Name :   " + staff.get(i).getName());
            System.out.println("Email :   " + staff.get(i).getEmail());
            System.out.println("Phone :   " + staff.get(i).getPhone());
            System.out.println("Gender :   " + staff.get(i).getGender());
            System.out.println("BirthDate :   " + staff.get(i).getBirthDate());

        }

    }
    
    public static boolean isName(String in)
    {
        return Pattern.matches("[a-zA-z]+",in);
    }
    
    public static boolean isEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    
    public static boolean isPhone(String phone){
        return Pattern.matches("0\\d+",phone);
    }
    
    public static boolean isGender(String gender){
        return Pattern.matches("[mMfF]", gender);
    }
    
    public static boolean isBirthDate(String birthDate){
        return Pattern.matches("\\d{2}-\\d{2}-\\d{4}", birthDate);
    }
    
}


