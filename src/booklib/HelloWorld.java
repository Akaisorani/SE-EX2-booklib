package booklib;
import java.sql.*;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorld extends ActionSupport {
	private String name;
	private String username,password;
	private String entries;
	private String printresult;
	
    public String execute() throws Exception {
    	printresult="abc";
        return SUCCESS;
    }

    public String getName() {
    	return name;
    }

    public void setName(String name) {
        this.name=name;
    }
    
    public void setUsername(String username) {
    	this.username=username;
    }
    
    public String getUsername() {
    	return username;
    }

    public void setPassword(String password) {
    	this.password=password;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public String getEntries() {
    	return entries;
    }
    
    public String addEntry() throws Exception{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!");
		}
		catch (Exception e){
			System.out.println("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","oursql");
			System.out.println("Success connect Mysql server!");
			PreparedStatement prstmt=connect.prepareStatement("INSERT INTO user VALUES(?,?)");
			prstmt.setString(1,"ygw");
			prstmt.setString(2,"878654");
			prstmt.executeUpdate();
			
			Statement stmt=connect.createStatement();
			ResultSet rs=stmt.executeQuery("select * from user");
			
			entries="";
			while(rs.next()) {
				entries+="name: "+rs.getString("name")+"    ";
				entries+="pw: "+rs.getString("password")+"<br>";
				System.out.println("name: "+rs.getString("name"));
				System.out.println("pw: "+rs.getString("password"));
			}
		}
		catch (Exception e) {
			System.out.println("get data error!");
			e.printStackTrace();
		}
		return SUCCESS;
    }

	public String getPrintresult() {
		return printresult;
	}

	public void setPrintresult(String printresult) {
		this.printresult = printresult;
	}
}