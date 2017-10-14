package booklib;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.json.annotations.JSON;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
  
import org.apache.commons.io.IOUtils;  
import org.apache.struts2.ServletActionContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;


public class showBenzi extends ActionSupport {
	private int Id;
	private String Title,Author,Publisher,Reference,Info;
    private File CoverFile;// 对应文件域的file，封装文件内容  
    private String CoverFileContentType;// 封装文件类型  
    private String CoverFileFileName;// 封装文件名 
    private String savePath;// 保存路径
	private String Source,CoverUrl;
	private String printresult;
	
	private String trans(String s) {
		if(s==null)return null;
		return s.replaceAll("\"", "&#34;").replaceAll("\'","&#39;");
	}
	
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

			PreparedStatement prstmt=connect.prepareStatement("select * from benzi where Id=? ");
			prstmt.setInt(1,Id);
			ResultSet rs=prstmt.executeQuery();
			if(rs.next()) {
				printresult="载入信息成功!";
				Id=rs.getInt("Id");
				Title=rs.getString("Title");
				Author=rs.getString("Author");
				CoverUrl=rs.getString("Cover");
				Publisher=rs.getString("Publisher");
				Reference=rs.getString("Reference");
				Source=rs.getString("Source");
				Info=rs.getString("Info")==null?"":rs.getString("Info").replaceAll("\\n", "<br>");
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
	
	public String getTitle() { return Title; }
	public void setTitle(String Title) { this.Title=Title; }

	public String getAuthor() { return Author; }
	public void setAuthor(String Author) { this.Author=Author; }

	public String getPublisher() { return Publisher; }
	public void setPublisher(String Publisher) { this.Publisher=Publisher; }
	
	public String getReference() { return Reference; }
	public void setReference(String Reference) { this.Reference=Reference; }
	
	public String getPrintresult() {
		return printresult;
	}

	public void setPrintresult(String printresult) {
		this.printresult = printresult;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String getCoverUrl() {
		return CoverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		CoverUrl = coverUrl;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getCoverFileFileName() {
		return CoverFileFileName;
	}

	public void setCoverFileFileName(String coverFileFileName) {
		CoverFileFileName = coverFileFileName;
	}

	public String getCoverFileContentType() {
		return CoverFileContentType;
	}

	public void setCoverFileContentType(String coverFileContentType) {
		CoverFileContentType = coverFileContentType;
	}

	public File getCoverFile() {
		return CoverFile;
	}

	public void setCoverFile(File coverFile) {
		CoverFile = coverFile;
	}

	public String getInfo() {
		return Info;
	}

	public void setInfo(String info) {
		Info = info;
	}
	
}
