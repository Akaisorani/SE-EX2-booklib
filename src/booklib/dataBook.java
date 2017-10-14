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

public class dataBook extends ActionSupport implements ServletRequestAware {
	private ArrayList<Object> dataBook;
	private HashMap<String,Object> entry;
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private HttpServletRequest request;
	private static String[] columnsName= {"ISBN","Title","Name","Publisher","PublishDate","Price"};
	
	public ArrayList<Object> querydata(int pageSize, int startRecord, String sortColumn, String sortDir, String searchValue) throws Exception  {
		ArrayList<Object> datalist=new ArrayList<>();
		StringBuilder sql= new StringBuilder();
		StringBuilder sqlCnt=new StringBuilder();
		sql.append("select b.ISBN,b.Title,a.Name,b.Publisher,b.PublishDate,b.Price "
				+ "from book b left join author a on b.AuthorID=a.AuthorID ");
		sqlCnt.append("select count(*) from book b left join author a on b.AuthorID=a.AuthorID ");
		//searching
		boolean searchAble=false;
		searchValue=searchValue.trim();
		if(searchValue!=null && !"".equals(searchValue)) {
			searchAble=true;
			sql.append(" where a.Name like ? ");
			sqlCnt.append(" where a.Name like ? ");
		}
		
		//order
		if(sortColumn!=null && !"".equals(sortColumn)) {
			if(!sortColumn.equals("Price"))sql.append(" order by convert(" + sortColumn + " using gbk) " + sortDir + " ");
			else sql.append(" order by " + sortColumn + " " + sortDir + " ");
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
			//add book
			ResultSet rs=prstmt.executeQuery();
			while(rs.next()) {
				entry=new HashMap<>();
				entry.put("ISBN", rs.getObject("ISBN"));
				entry.put("Title", rs.getObject("Title"));
				entry.put("Author", rs.getObject("Name"));
				entry.put("Publisher", rs.getObject("Publisher"));
				entry.put("PublishDate", (new SimpleDateFormat("yyyy-MM-dd")).format(rs.getObject("PublishDate")));
				entry.put("Price", rs.getObject("Price"));
				datalist.add(entry);				
				
				//System.out.println("book: "+rs.getString("ISBN"));
			}
			
			//count total entry
			ResultSet rs2=prstmtcnt.executeQuery();
			if(rs2.next()) {
		        recordsTotal=rs2.getInt("count(*)");
		        recordsFiltered=recordsTotal;
			}
			else {
				recordsTotal=datalist.size();
		        recordsFiltered=datalist.size();
			}
			connect.close();
		}
		catch (Exception e) {
			System.out.println("SQL get data error!");
			e.printStackTrace();
		}
		return datalist;
	}
	
	public String execute() throws Exception {
		try {
			//pagination
			int pageSize=10;
			int startRecord=0;
			String size=request.getParameter("length");
			if(size!=null && !"".equals(size)) {
				pageSize=Integer.parseInt(size);
			}
			String currentRecord=request.getParameter("start");
			if(currentRecord!=null && !"".equals(currentRecord)) {
				startRecord = Integer.parseInt(currentRecord);
			}
			System.out.println("pagesize "+pageSize);
			System.out.println("currentRecord "+currentRecord);
			//sort
			String sortOrder = request.getParameter("order[0][column]");
	        String sortDir = request.getParameter("order[0][dir]");
	        System.out.println("sortOrder: " + sortOrder);
	        System.out.println("sortDir: " + sortDir);
	        
	        //search
	        String searchValue = request.getParameter("search[value]");
	        
	        //query
	        dataBook=querydata(pageSize,startRecord,columnsName[Integer.parseInt(sortOrder)],sortDir,searchValue);
	        draw=Integer.parseInt(request.getParameter("draw") == null ? "0": request.getParameter("draw")) + 1;
	        
		}catch(Exception e) {
			System.out.println("get data error!");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@JSON(name="data")
	public ArrayList<Object> getDataBook(){
		return dataBook;
	}
	
	public void setDataBook(ArrayList<Object> dataBook) {
		this.dataBook=dataBook;
	}
	
	public int getdraw() {
		return draw;
	}
	
	public void setdraw(int draw) {
		this.draw=draw;
	}
	
	public int getrecordsTotal() {
		return recordsTotal;
	}
	
	public void setrecordsTotal(int recordsTotal) {
		this.recordsTotal=recordsTotal;
	}
	
	public int getrecordsFiltered() {
		return recordsFiltered;
	}
	
	public void setrecordsFiltered(int recordsFiltered) {
		this.recordsFiltered=recordsFiltered;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
}
