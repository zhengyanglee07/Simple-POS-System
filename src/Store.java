import java.lang.reflect.Array;
import java.util.ArrayList;

public class Store {
    private static ArrayList<Admin> adminList = new ArrayList<>(); // Store Admin Information
    private static ArrayList<Staff> staffList = new ArrayList<>(); // Store Staff Information
    private static ArrayList<Category> categoryList = new ArrayList<>(); // Store Category Information
    private static ArrayList<Product> menuList = new ArrayList<>(); // Store Product Information
    private static ArrayList<OrderInfo> orderList = new ArrayList<>(); // Store Order Information
    private static boolean alreadyExecute;


    public static void store() {
        /*To make these record only store one time*/
        if(!alreadyExecute){
            adminList.add(new Admin("TeikKeen","teikkeen@gmail.com","012-343-6546","Male","15-03-2001" ,"teikkeen"));
            staffList.add(new Staff("John", "john1234@gmail.com", "012-324-4543", "Male", "21-05-1998", "john1234"));
            staffList.add(new Staff("Mary", "mary1234@gmail.com", "011-753-5654", "Female", "09-11-2001", "mary1234"));
            staffList.add(new Staff("Sam", "sam1234@gmail.com", "015-234-7533", "Male", "25-09-2000", "sam1234"));

            menuList.add(new Product("MS001","M00001","Fried Chicken",12.0));
            menuList.add(new Product("MS001","M00002","Hamburger",13.50));
            menuList.add(new Product("MS001","M00003","Grilled Fish",22.00));
            menuList.add(new Product("PP002","P00001","French Fries",7.60));
            menuList.add(new Product("BV003","B00001","Beer",11.88));
            menuList.add(new Product("BV003","B00002","Milk Tea",12.0));
            menuList.add(new Product("DS004","D00001","Ice Cream",8.20));
            menuList.add(new Product("DS004","D00002","Chocolate Cake",13.90));

            categoryList.add(new Category("MS001","Meat and Seafood",12.0,45.0));
            categoryList.add(new Category("PP002","Party Platters",2.00,12.0));
            categoryList.add(new Category("BV003","Beverage",1.50,12.0));
            categoryList.add(new Category("DS004","Dessert",7.50,15.0));
            alreadyExecute = true;
        }
    }

    /*-------------------------------------------------------------------------*/
    /*------------ Getter and Setter for attribute in Store class--------------*/
    /*-------------------------------------------------------------------------*/
    public static ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public static void setAdminList(ArrayList<Admin> adminList) {
        Store.adminList = adminList;
    }

    public static ArrayList<Staff> getStaffList() {
        return staffList;
    }

    public static void setStaffList(ArrayList<Staff> staffList) {
        Store.staffList = staffList;
    }

    public static ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public static void setCategoryList(ArrayList<Category> categoryList) {
        Store.categoryList = categoryList;
    }

    public static ArrayList<Product> getMenuList() {
        return menuList;
    }

    public static void setMenuList(ArrayList<Product> menuList) {
        Store.menuList = menuList;
    }

    public static ArrayList<OrderInfo> getOrderList() {
        return orderList;
    }

    public static void setOrderList(ArrayList<OrderInfo> orderList) {
        Store.orderList = orderList;
    }

    public static boolean isAlreadyExecute() {
        return alreadyExecute;
    }

    public static void setAlreadyExecute(boolean alreadyExecute) {
        Store.alreadyExecute = alreadyExecute;
    }
}
