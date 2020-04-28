package Image;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBDriver {
	public static void main(String args[]){  
		
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://72.197.231.122:3306/apcs_db?serverTimezone=UTC","apcs_db","apcs_db");  
		String query = "insert into ImagesPOC (Caption, Image) values (?, ?)";
		
		PreparedStatement pStmt = con.prepareStatement(query);
		pStmt.setString(1, "Dog Photo");
		pStmt.setBlob(2, Driver.BinaryToBlob());
		pStmt.execute();
		System.out.println("Added to DB");
		con.close();  
		}catch(Exception e){ System.out.println(e);}   
	
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://72.197.231.122:3306/apcs_db?serverTimezone=UTC","apcs_db","apcs_db");  
			String query = "SELECT Image FROM ImagesPOC";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				int i = 1;
				Blob blob = rs.getBlob("Image");
				int myblobLength = (int) blob.length();  
				byte[] myblobAsBytes = blob.getBytes(1, myblobLength);
				blob.free();
				
				Driver.bytes = myblobAsBytes;
				Driver.ByteArrayToImage("test" + i);
				i++;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
