package booklib;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts2.json.annotations.JSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;


public class updBook extends ActionSupport {
	private String ISBN,Title,Author,Publisher,PublishDate;
	private String Price;
	private int AuthorID;
	private String printresult;
	private String Age,Country;
	
	public String updBook() throws Exception  {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!");
		}
		catch (Exception e){
			System.out.println("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/booklib","lab2","oursql");
			System.out.println("Success connect Mysql server!");
			
			PreparedStatement prstmt=connect.prepareStatement("update book set "
					+ "AuthorID=?, Publisher=?, PublishDate=?, Price=? "
					+ "where ISBN=? ");
			
			System.out.println("AuthorID is "+AuthorID);
			prstmt.setInt(1,AuthorID);
			prstmt.setString(2,Publisher);
			prstmt.setString(3,PublishDate.equals("")?null:PublishDate);
			prstmt.setString(4,Price);
			prstmt.setString(5,ISBN);
			int res=prstmt.executeUpdate();
			if(res==1) {
				printresult="修改成功!";
				return SUCCESS;
			}
			printresult="修改失败，请检查信息是否正确！";
			return ERROR;
		}
		catch (Exception e) {
			System.out.println("update data error!");
			e.printStackTrace();
			printresult="修改失败，请检查信息是否正确！";
			return ERROR;
		}
	}
	
	public String loadBookInfo() throws Exception  {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!");
		}
		catch (Exception e){
			System.out.println("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/booklib","lab2","oursql");
			System.out.println("Success connect Mysql server!");

			PreparedStatement prstmt=connect.prepareStatement("select * from book b left join author a on b.AuthorID=a.AuthorID where ISBN=? ");
			prstmt.setString(1,ISBN);
			ResultSet rs=prstmt.executeQuery();
			if(rs.next()) {
				printresult="载入信息成功!";
				ISBN=rs.getString("ISBN");
				Title=rs.getString("Title");
				Author=rs.getString("Name");
				AuthorID=rs.getInt("AuthorID");
				Publisher=rs.getString("Publisher");
				PublishDate=rs.getString("PublishDate");
				Price=rs.getString("Price");
				setAge(rs.getString("Age"));
				setCountry(rs.getString("Country"));
				return SUCCESS;
			}
			else{
				printresult="载入信息失败！";
			}
			connect.close();
			return ERROR;	
		}
		catch (Exception e) {
			System.out.println("load data for update error!");
			e.printStackTrace();
			printresult="载入信息失败！";
			return ERROR;
		}
	}
	
	public String getISBN() { return ISBN; }
	public void setISBN(String ISBN) { this.ISBN=ISBN; }
	
	public String getTitle() { return Title; }
	public void setTitle(String Title) { this.Title=Title; }

	public String getAuthor() { return Author; }
	public void setAuthor(String Author) { this.Author=Author; }

	public int getAuthorID() { return AuthorID; }
	public void setAuthorID(int AuthorID) { this.AuthorID=AuthorID; }

	public String getPublisher() { return Publisher; }
	public void setPublisher(String Publisher) { this.Publisher=Publisher; }
	
	public String getPublishDate() { return PublishDate; }
	public void setPublishDate(String PublishDate) { this.PublishDate=PublishDate; }
	
	public String getPrice() { return Price; }
	public void setPrice(String Price) { this.Price=Price; }
	
	public String getAge() {return Age;}
	public void setAge(String age) {this.Age = age;}

	public String getCountry() {return Country;}
	public void setCountry(String country) {this.Country = country;}
	
	public String getPrintresult() {
		return printresult;
	}

	public void setPrintresult(String printresult) {
		this.printresult = printresult;
	}
	
}
