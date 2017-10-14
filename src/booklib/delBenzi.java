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


public class delBenzi extends ActionSupport {
	private int Id;
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

			PreparedStatement prstmt=connect.prepareStatement("delete from benzi where Id=? ");
			prstmt.setInt(1,Id);
			System.out.println("DELETEBENZI "+prstmt.toString());
			int res=prstmt.executeUpdate();
			if(res==1) {
				printresult="删除成功!";
				return SUCCESS;
			}
			else if(res==0){
				printresult="删除失败，数据库中没有这本书！";
			}
			else {
				printresult="删除失败！";
			}
			connect.close();
			return ERROR;	
		}
		catch (Exception e) {
			System.out.println("del data error!");
			e.printStackTrace();
			printresult="删除失败！";
			return ERROR;
		}
	}
	
	public int getISBN() { 
		return Id;
	}
	public void setId(int Id) {
		this.Id=Id; 
	}

	public String getPrintresult() {
		return printresult;
	}

	public void setPrintresult(String printresult) {
		this.printresult = printresult;
	}
	
}
