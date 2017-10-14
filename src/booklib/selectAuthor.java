package booklib;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts2.interceptor.ServletRequestAware;
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

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class selectAuthor extends ActionSupport{
	private ArrayList<Object> items;
	private HashMap<String,Object> entry;
	private String length;
	private String page;
	private int total_count;
	private String q;
	
	public ArrayList<Object> querydata(int pageSize, int startRecord, String searchValue) throws Exception  {
		ArrayList<Object> datalist=new ArrayList<>();
		StringBuilder sql= new StringBuilder();
		StringBuilder sqlCnt=new StringBuilder();
		sql.append("select * from author ");
		sqlCnt.append("select count(*) from author ");
		//searching
		boolean searchAble=false;
		if(searchValue!=null && !"".equals(searchValue.trim())) {
			searchValue=searchValue.trim();
			searchAble=true;
			sql.append(" where Name like ? ");
			sqlCnt.append(" where Name like ? ");
		}
				
		//pagination
		sql.append(" limit ?,? ");
		
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
			
			PreparedStatement prstmt=connect.prepareStatement(sql.toString());
			PreparedStatement prstmtcnt=connect.prepareStatement(sqlCnt.toString());
			String quercnt=prstmtcnt.toString();
			System.out.println("cnt "+quercnt);
			int pos=1;
			if(searchAble) {
				prstmt.setString(pos, "%"+searchValue+"%");
				prstmtcnt.setString(pos, "%"+searchValue+"%");
				pos++;
			}
			prstmt.setInt(pos, startRecord);pos++;
			prstmt.setInt(pos, pageSize);pos++;
			String quer=prstmt.toString();
			System.out.println(quer);
			//add author
			ResultSet rs=prstmt.executeQuery();
			while(rs.next()) {
				entry=new HashMap<>();
				entry.put("id", rs.getInt("AuthorID"));
				entry.put("text", rs.getObject("Name"));
				entry.put("Age", rs.getObject("Age"));
				entry.put("Country", rs.getObject("Country"));
				datalist.add(entry);				
				
				//System.out.println("book: "+rs.getString("ISBN"));
			}
			
			//count total entry
			ResultSet rs2=prstmtcnt.executeQuery();
			if(rs2.next()) {
		        total_count=rs2.getInt("count(*)");
			}
			else {
				total_count=datalist.size();
			}
			connect.close();
		}
		catch (Exception e) {
			System.out.println("SQL get Author data error!");
			e.printStackTrace();
		}
		return datalist;
	}
	
	public String execute() throws Exception {
		try {
			//pagination
			int pageSize=20;
			int startRecord=0;
			if(length!=null && !"".equals(length)) {
				pageSize=Integer.parseInt(length);
			}

			if(page!=null && !"".equals(page)) {
				startRecord = (Integer.parseInt(page)-1)*pageSize;
			}
			System.out.println("pagesize "+pageSize);
			System.out.println("startRecord "+startRecord);
	        
	        //query
			System.out.println("search Author string is "+q);
	        items=querydata(pageSize,startRecord,q);
	        
		}catch(Exception e) {
			System.out.println("get Author data error!");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@JSON(name="items")
	public ArrayList<Object> getitems(){
		return items;
	}
	
	public void setitems(ArrayList<Object> items) {
		this.items=items;
	}
	
	@JSON(serialize=false)
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
	
	@JSON(serialize=false)
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	
	@JSON(serialize=false)
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
}
