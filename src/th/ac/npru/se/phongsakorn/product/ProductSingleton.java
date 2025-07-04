package th.ac.npru.se.phongsakorn.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Represents a singleton product with database interaction.
 * This class ensures that only one instance of a product can be created and provides
 * methods to interact with the product data in the database.
 *
 * @author phongsakorn
 */
public class ProductSingleton {
	private static ProductSingleton instance;
	/** The ID of the product. */
    private String product_id;
    /** The name of the product. */
    private String product_name;
    /** The price of the product. */
    private int product_price;
	
	/**
     * Private constructor to prevent instantiation from outside the class.
     */
	private ProductSingleton() {
		
	}
	
	/**
     * Returns the single instance of the ProductSingleton class.
     * If an instance does not exist, it creates a new one.
     *
     * @return The single instance of ProductSingleton.
     */
	public static ProductSingleton getInstance() {
		if(instance == null) {
			instance = new ProductSingleton();
		}
		return instance;
	}
	
	/**
     * Sets the product information.
     *
     * @param product_id the product ID
     * @param product_name the product name
     * @param product_price the product price
     */
    public void setProduct(String product_id, String product_name, int product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
    }
	
	/**
     * Inserts the current product into the database.
     * Connects to a local MySQL database using default credentials.
     */
    public void insertProduct() {
        Connection myConn = null;
        Statement myStmt = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");
            myStmt = myConn.createStatement();

            String sql = "INSERT INTO Product VALUES ('" + this.product_id + "', '" + this.product_name + "', " + this.product_price + ")";
            myStmt.executeUpdate(sql);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
    /**
     * Retrieves the product name from the database based on the product ID.
     *
     * @param pid the product ID
     * @return the product name, or empty string if not found
     */
    public String getProductName(String pid) {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        String productName = "";
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");
            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery("SELECT * FROM product WHERE p_id='" + pid + "'");

            while (myRs.next()) {
                productName = myRs.getString("p_name");
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return productName;
    }
}
