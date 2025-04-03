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
            ResultSet rs = stmt.executeQuery("select * from Customer");
            while (rs.next()){
                System.out.print("Customer Id: " + rs.getInt("CustomerId")+"\t");
                System.out.print("Contact Number: " + rs.getString("ContactNumber")+"\t");
                System.out.println("Shipping Address: " + rs.getString("ShippingAddress"));
            }
            //try, no need connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

