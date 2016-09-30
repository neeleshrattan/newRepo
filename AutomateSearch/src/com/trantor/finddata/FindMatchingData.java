package com.trantor.finddata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Servlet implementation class FindMatchingData
 */
@WebServlet("/FindMatchingData")
public class FindMatchingData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindMatchingData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello tool keise ho");
		
		String order_id=request.getParameter("order_id");
		try
		{
			InputStream in = new FileInputStream("/home/neelesh/workspaceTrys/AutomateSearch/search.properties"); 
			Properties properties = new Properties();
			properties.load(in);
			String tableNames=properties.getProperty("table_names");
			String[] listOfTables=tableNames.split(",");
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook();
			//ArrayList al= new ArrayList();
			for(int i=0;i<listOfTables.length;i++)
			{
				ResultSet rs=Searcher.searchData(order_id,listOfTables[i]);
				 ResultSetMetaData metadata = rs.getMetaData();
				 int columnCount = metadata.getColumnCount();
				   
				 ArrayList<String> columns = new ArrayList<String>();
				    for (int j = 1; j < columnCount; j++) {
					String columnName = metadata.getColumnName(j);
					columns.add(columnName);
					
				    }
				 // System.out.println(columns);
				    XSSFSheet spreadsheet = workbook.createSheet(listOfTables[i]);
				    XSSFRow row=spreadsheet.createRow(1);
				    XSSFCell cell;
				    for(int j=0;j<columns.size();j++)
				    {
				    	
				    	 cell=row.createCell(j+1);
					      cell.setCellValue(columns.get(j));
					      
				    }
				   
				    row=spreadsheet.createRow(2);
				    while(rs.next())
				    {
				    	System.out.println("hello");
				    	for(int j=1;j<=columns.size();j++)
				    	{
				    	cell=row.createCell(j);
				    	cell.setCellValue(rs.getString(j));
				    	//System.out.println(rs.getString(j)+" "+j);
				    	
				    	}	
				    }
				    
				    FileOutputStream out = new FileOutputStream(
						      new File("/home/neelesh/frysOrderDetail.xlsx"));
						      workbook.write(out);
						      out.close();
						      System.out.println(
						      "exceldatabase.xlsx written successfully");
				   
			}
			
		}
		
		/*try
		{
		ResultSet rs=Searcher.searchData(order_id);
	 
		 while(rs.next())
		 {
			 System.out.println(rs.getString(1));
			 System.out.println(rs.getString(2));
			 System.out.println(rs.getString(3));
			 System.out.println(rs.getString(4)); 
			 System.out.println(rs.getString(5)); 
			 System.out.println(rs.getString(6)); 
			 //System.out.println(rs.getString("EXT_ORDER_NUMBER"));
			 //System.out.println(rs.getString("OFFLINE_ORDER_PUSH"));
			 System.out.println("");
			 
		XSSFWorkbook workbook = new XSSFWorkbook(); 
	      XSSFSheet spreadsheet = workbook
	      .createSheet("employe db");
	      XSSFRow row=spreadsheet.createRow(1);
	      XSSFCell cell;
	      cell=row.createCell(1);
	      cell.setCellValue("ORDER ID");
	      cell=row.createCell(2);
	      cell.setCellValue("STORE_ID");
	      cell=row.createCell(3);
	      cell.setCellValue("USER_ID");
	      cell=row.createCell(4);
	      cell.setCellValue("ORDER_DATE");
	      cell=row.createCell(5);
	      cell.setCellValue("IS_MULTISHIP");
	      cell=row.createCell(6);
	      cell.setCellValue("ORDER_ADDRESS_ID");
	      cell=row.createCell(7);
	      cell.setCellValue("ORDER_STATUS");
	      cell=row.createCell(8);
	      cell.setCellValue("EXT_ORDER_NUMBER");
	      cell=row.createCell(9);
	      cell.setCellValue("DATETIME");
	      cell=row.createCell(10);
	      cell.setCellValue(" DELETEFLAG");
	      cell=row.createCell(11);
	      cell.setCellValue("OFFLINE_ORDER_PUSH");
			 
		      System.out.println(rs.getFetchSize());
		      int i=2;
		      while(rs.next())
		      {
		    	  System.out.println("row no"+" "+i++);
		         row=spreadsheet.createRow(i);
		         cell=row.createCell(1);
		         cell.setCellValue(rs.getInt("ORDER_ID"));
		         cell=row.createCell(2);
		         cell.setCellValue(rs.getInt("STORE_ID"));
		         cell=row.createCell(3);
		         cell.setCellValue(rs.getInt("USER_ID"));
		         cell=row.createCell(4);
		         cell.setCellValue(rs.getDate("ORDER_DATE"));
		         cell=row.createCell(5);
		         cell.setCellValue(rs.getInt("IS_MULTISHIP"));
		         cell=row.createCell(6);
		         cell.setCellValue(rs.getInt("ORDER_ADDRESS_ID"));
		         cell=row.createCell(7);
		         cell.setCellValue(rs.getString("ORDER_STATUS"));
		         cell=row.createCell(8);
		         cell.setCellValue(rs.getString("EXT_ORDER_NUMBER"));
		         cell=row.createCell(9);
		         cell.setCellValue(rs.getDate("DATETIME"));
		         cell=row.createCell(10);
		         cell.setCellValue(rs.getString("DELETEFLAG"));
		         cell=row.createCell(11);
		         cell.setCellValue(rs.getString("OFFLINE_ORDER_PUSH"));
		   
		         i++;
		      }
		      FileOutputStream out = new FileOutputStream(
		      new File("/home/amit/exceldatabase.xlsx"));
		      workbook.write(out);
		      out.close();
		      System.out.println(
		      "exceldatabase.xlsx written successfully");
		 
		}*/
		catch(Exception w)
		{
			System.out.println("exception occur while printing data"+" "+w);
			
		}
		
		
	}

}
