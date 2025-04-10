package groupproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;

public class Groupproject {
    
    public static final String url = "jdbc:mysql://localhost:3306/groupproject";
    private final static String username = "root";
    private final static String password = "201561";
    
    static String Customer[][] = new String[3][10];
    static String Vendor[][] = new String[4][10];
    static String Product[][] = new String[7][10];
    static String Order[][] = new String[3][10];
    static  String OrderVendor[][] = new String[3][10];
    static String OrderItem[][] = new String[5][10];
    
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database driver loading failed!");
        }

        try {
            Connection connect = DriverManager.getConnection(url, username, password);
            
            try (PreparedStatement ps = connect.prepareStatement("select * from Customer")) {
                /*ps.setObject(parameterIndex, ps);*/
                try (ResultSet rs = ps.executeQuery()) {
                    int i = 0;
            
                    while (rs.next()){
                        System.out.print("Customer ID: " + rs.getInt("CustomerID")+"\t");
                        System.out.print("Contact Number: " + rs.getString("ContactNumber")+"\t");
                        System.out.println("Shipping Address: " + rs.getString("ShippingAddress"));
                        Customer[0][i] = String.format("%d",rs.getInt("CustomerId"));
                        Customer[1][i] = rs.getString("ContactNumber");
                        Customer[2][i] = rs.getString("ShippingAddress");     
                        i += 1;
                    }
                }
            }    
        }
            catch (Exception e) {
                e.printStackTrace();
        }
        
        try {
            Connection connect = DriverManager.getConnection(url, username, password);

            try (PreparedStatement ps = connect.prepareStatement("select * from Vendor")) {
                /*ps.setObject(parameterIndex, ps);*/
                try (ResultSet rs = ps.executeQuery()) {
                    int i = 0;
            
                    while (rs.next()){
                        System.out.print("Vendor ID: " + rs.getInt("VendorID")+"\t");
                        System.out.print("Business Name: " + rs.getString("BusinessName")+"\t");
                        System.out.print("Feedback Score: " + rs.getInt("FeedbackScore")+"\t");
                        System.out.println("Geography: " + rs.getString("Geography"));
                        Vendor[0][i] = String.format("%d", rs.getInt("VendorID"));
                        Vendor[1][i] = rs.getString("BusinessName");
                        Vendor[2][i] = String.format("%d", rs.getInt("FeedbackScore"));
                        Vendor[3][i] = rs.getString("Geography");;     
                        i += 1;
                    }
                }
            }    
        }
            //try, no need connect.close();
        
            catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection connect = DriverManager.getConnection(url, username, password);

            try (PreparedStatement ps = connect.prepareStatement("select * from Product")) {
                /*ps.setObject(parameterIndex, ps);*/
                try (ResultSet rs = ps.executeQuery()) {
                    int i = 0;
            
                    while (rs.next()){
                        System.out.print("Product ID: " + rs.getInt("ProductID")+"\t");
                        System.out.print("Vendor ID: " + rs.getInt("VendorID")+"\t");
                        System.out.print("Product Name: " + rs.getString("ProductName")+"\t");
                        System.out.print("List Price: " + rs.getInt("ListPrice")+"\t");
                        System.out.println("Tag 1: " + rs.getString("Tag1")+"\t");
                        System.out.println("Tag 2: " + rs.getString("Tag2")+"\t");
                        System.out.println("Tag 3: " + rs.getString("Tag3"));
                        Product[0][i] = String.format("%d", rs.getInt("ProductID"));
                        Product[1][i] = String.format("%d", rs.getInt("VendorID"));
                        Product[2][i] = rs.getString("ProductName");
                        Product[3][i] = String.format("%d", rs.getInt("ListPrice"));
                        Product[4][i] = rs.getString("Tag1");
                        Product[5][i] = rs.getString("Tag2");
                        Product[6][i] = rs.getString("Tag3");     
                        i += 1;
                    }
                }
            }    
        }
            //try, no need connect.close();
        
            catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection connect = DriverManager.getConnection(url, username, password);

            try (PreparedStatement ps = connect.prepareStatement("select * from `Order`")) {
                /*ps.setObject(parameterIndex, ps);*/
                try (ResultSet rs = ps.executeQuery()) {
                    int i = 0;
            
                    while (rs.next()){
                        System.out.print("Order ID: " + rs.getInt("OrderID")+"\t");
                        System.out.print("Customer ID: " + rs.getInt("CustomerID")+"\t");
                        System.out.print("Total Amount: " + rs.getInt("TotalAmount")+" \t");
                        Order[0][i] = String.format("%d", rs.getInt("OrderID"));
                        Order[1][i] = String.format("%d", rs.getInt("CustomerID"));
                        Order[2][i] = String.format("%d", rs.getInt("TotalAmount"));     
                        i += 1;
                    }
                }
            }    
        }            
            //try, no need connect.close();
        
            catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection connect = DriverManager.getConnection(url, username, password);

            try (PreparedStatement ps = connect.prepareStatement("select * from OrderVendor")) {
                /*ps.setObject(parameterIndex, ps);*/
                try (ResultSet rs = ps.executeQuery()) {
                    int i = 0;
            
                    while (rs.next()){
                        System.out.print("Order Vendor ID: " + rs.getInt("OrderVendorID")+"\t");
                        System.out.print("Order ID: " + rs.getInt("OrderID")+"\t");
                        System.out.print("Vendor ID: " + rs.getInt("VendorID")+" \t");
                        OrderVendor[0][i] = String.format("%d", rs.getInt("OrderVendorID"));
                        OrderVendor[1][i] = String.format("%d", rs.getInt("OrderID"));
                        OrderVendor[2][i] = String.format("%d", rs.getInt("VendorID"));   
                        i += 1;
                    }
                }
            }    
        }
            //try, no need connect.close();
        
            catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection connect = DriverManager.getConnection(url, username, password);

            try (PreparedStatement ps = connect.prepareStatement("select * from OrderItem")) {
                /*ps.setObject(parameterIndex, ps);*/
                try (ResultSet rs = ps.executeQuery()) {
                    int i = 0;
            
                    while (rs.next()){
                        System.out.print("Order Item ID: " + rs.getInt("OrderItemID")+"\t");
                        System.out.print("Order Vendor ID: " + rs.getInt("OrderVendorID")+"\t");
                        System.out.print("Product ID: " + rs.getInt("ProductID")+"\t");
                        System.out.print("Quantity: " + rs.getInt("Quantity")+"\t");
                        System.out.print("Unit Price: " + rs.getInt("UnitPrice"));
                        OrderItem[0][i] = String.format("%d", rs.getInt("OrderItemID"));
                        OrderItem[1][i] = String.format("%d", rs.getInt("OrderVendorID"));
                        OrderItem[2][i] = String.format("%d", rs.getInt("ProductID"));
                        OrderItem[3][i] = String.format("%d", rs.getInt("Quantity"));
                        OrderItem[4][i] = String.format("%d", rs.getInt("UnitPrice"));  
                        i += 1;
                    }
                }
            }    
        }            
            //try, no need connect.close();
        
            catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String JCustomer = gson.toJson(Customer);
        String JVendor = gson.toJson(Vendor);
        String JProduct = gson.toJson(Product);
        String JOrder = gson.toJson(Order);
        String JOrderVendor = gson.toJson(OrderVendor);
        String JOrderItem = gson.toJson(OrderItem);
        CreateFileUtil.createJsonFile(JCustomer, "C:/Users/WST/maventest/js/file", "JCustomer");
        CreateFileUtil.createJsonFile(JVendor, "C:/Users/WST/maventest/js/file", "JVendor");
        CreateFileUtil.createJsonFile(JProduct, "C:/Users/WST/maventest/js/file", "JProduct");
        CreateFileUtil.createJsonFile(JOrder, "C:/Users/WST/maventest/js/file", "JOrder");
        CreateFileUtil.createJsonFile(JOrderVendor, "C:/Users/WST/maventest/js/file", "JOrderVendor");        
        CreateFileUtil.createJsonFile(JOrderItem, "C:/Users/WST/maventest/js/file", "JOrderItem");    
    }
}

