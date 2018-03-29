package connector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ConnectionMySQL {
	public static FileReader fr;
	public static Properties p;
	public static Connection con = null;
	public PreparedStatement ps;
	public ResultSet rs;
	
	public static void dbConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			fr = new FileReader("jdbc.properties");
			p = new Properties();
			p.load(fr);
		} catch (ClassNotFoundException e) {
			System.out.print("No definition for the class with the specified name could be found: "); 
			System.out.println(e.getMessage());
			System.out.println("Terminating the program...");
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Terminating the program...");
			System.exit(1);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Terminating the program...");
			System.exit(1);
		}
		
		
		System.out.println("Connecting to the database...");
		try {
			con = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("\nTerminating the program...");
			System.exit(1);
		}
		System.out.println("Connection Successful...\n");
	}
}


