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


public class addBook extends ActionSupport {
	private String ISBN,Title,Author,Publisher,PublishDate;
	private int AuthorID;
	private String Price;
	private String printresult;
	public String execute() throws Exception  {
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
			
			ISBN=ISBN.replaceAll("-", "");
			
			//System.out.println("input Author is "+Author);
			PreparedStatement prstmt2=connect.prepareStatement("select ISBN from book where ISBN=? ");
			prstmt2.setString(1, ISBN);
			ResultSet rs=prstmt2.executeQuery();
			if(rs.next()) {
				printresult="添加失败，系统中已有这本书！";
				return ERROR;
			}
			
			PreparedStatement prstmt=connect.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?,?)");
			prstmt.setString(1,ISBN);
			prstmt.setString(2,Title);
			prstmt.setInt(3,AuthorID);
			prstmt.setString(4,Publisher);
			prstmt.setString(5,PublishDate);
			prstmt.setString(6,Price);
			int res=prstmt.executeUpdate();
			connect.close();
			if(res==1) {
				printresult="添加成功!";
				return SUCCESS;
			}
			printresult="添加失败，请检查信息是否正确！";
			return ERROR;
		}
		catch (Exception e) {
			System.out.println("get data error!");
			e.printStackTrace();
			printresult="添加失败，请检查信息是否正确！";
			return ERROR;
		}
	}
	
	public String getISBN() { return ISBN; }
	public void setISBN(String ISBN) { this.ISBN=ISBN; }
	
	public String getTitle() { return Title; }
	public void setTitle(String Title) { this.Title=Title; }

	public int getAuthorID() { return AuthorID; }
	public void setAuthorID(int AuthorID) { this.AuthorID=AuthorID; }

	public String getPublisher() { return Publisher; }
	public void setPublisher(String Publisher) { this.Publisher=Publisher; }
	
	public String getPublishDate() { return PublishDate; }
	public void setPublishDate(String PublishDate) { this.PublishDate=PublishDate; }
	
	public String getPrice() { return Price; }
	public void setPrice(String Price) { this.Price=Price; }

	public String getPrintresult() {
		return printresult;
	}

	public void setPrintresult(String printresult) {
		this.printresult = printresult;
	}
	
}
