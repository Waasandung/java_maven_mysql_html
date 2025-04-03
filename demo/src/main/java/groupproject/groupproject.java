package groupproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class groupproject {
    
    public static final String url = "jdbc:mysql://localhost:3306/groupproject";
    private final static String username = "root";
    private final static String password = "201561";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database driver loading failed!");
        }
        try {
            Connection connect = DriverManager.getConnection(url, username, password);

            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customer_profile");
            while (rs.next()){
                System.out.print("Customer Id: " + rs.getInt("customer_id")+"\t");
                System.out.print("Contact Number: " + rs.getInt("contact_number")+"\t");
                System.out.println("Shipping Details: " + rs.getString("shipping_details"));
            }
            //try, no need connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

