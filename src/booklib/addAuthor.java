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


public class addAuthor extends ActionSupport {
	private String Author,Age,Country;
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

			PreparedStatement prstmt=connect.prepareStatement("INSERT INTO author VALUES(null,?,?,?) ");
			prstmt.setString(1,Author);
			prstmt.setString(2,Age);
			prstmt.setString(3,Country);
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
			System.out.println("add Author error!");
			e.printStackTrace();
			printresult="添加失败，请检查信息是否正确！";
			return ERROR;
		}
	}
	
	public String getAuthor() { return Author; }
	public void setAuthor(String Author) { this.Author=Author; }

	public String getAge() {return Age;}
	public void setAge(String age) {Age = age;}

	public String getCountry() {return Country;}
	public void setCountry(String country) {Country = country;}

	public String getPrintresult() {
		return printresult;
	}

	public void setPrintresult(String printresult) {
		this.printresult = printresult;
	}


	
}
