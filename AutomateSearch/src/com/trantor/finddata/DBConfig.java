package com.trantor.finddata;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConfig {

	
	public  Connection getConnection()
	{
		Connection con;
		try{  
			InputStream in = new FileInputStream("/home/neelesh/workspaceTrys/AutomateSearch/search.properties"); 
			Properties properties = new Properties();
			properties.load(in);
			String driverName=properties.getProperty("driver_name");
			String url=properties.getProperty("url");
			String userName=properties.getProperty("user_name");
			String password=properties.getProperty("password");
			Class.forName(driverName);  
			con=DriverManager.getConnection(url,userName,password);
		//	Class.forName("com.mysql.jdbc.Driver");  
			  
			// con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbOrder","root","root");
		}
		catch(Exception e)
		{
			System.out.println("Connection not successfuly created"+" "+e );
			con=null;
		}
		
		
		return con;
	}
	
	public void closeConnection(Connection con) 
	{
		try
		{
		con.close();
		}catch(Exception e)
		{
			System.out.println("connection not successfully closed"+" "+e );
		}
	}
	
	
}
