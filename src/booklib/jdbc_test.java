package booklib;
import java.sql.*;
public class jdbc_test {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!");
		}
		catch (Exception e){
			System.out.println("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","lab2","oursql");
			System.out.println("Success connect Mysql server!");
			Statement stmt=connect.createStatement();
			PreparedStatement prstmt=connect.prepareStatement("INSERT INTO user VALUES(?,?)");
			prstmt.setString(1,"ygw");
			prstmt.setString(2,"878654");
			prstmt.executeUpdate();
			ResultSet rs=stmt.executeQuery("select * from user");
			
			while(rs.next()) {
				System.out.println("name: "+rs.getString("name"));
				System.out.println("pw: "+rs.getString("password"));
			}
		}
		catch (Exception e) {
			System.out.println("get data error!");
			e.printStackTrace();
		}
	}
}
