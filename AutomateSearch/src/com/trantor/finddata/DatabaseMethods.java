package com.trantor.finddata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseMethods {
	
	
	DBConfig db=new DBConfig();
	ResultSet rs;
	public ResultSet getData(String oid,String tableName)
	{
		try
		{
		//String sql="select J1_ORDER.STORE_ID,J1_ORDER.USER_ID,J1_ORDER_COUPON.COUPON_ID,J1_ORDER_COUPON.IS_PERCENTAGE,J1_ORDER_DEL_CHARGE.PROD_ID,J1_ORDER_DEL_CHARGE.PLU_DESC_SHORT from J1_ORDER join J1_ORDER_COUPON ON J1_ORDER.ORDER_ID=J1_ORDER_COUPON.ORDER_ID JOIN J1_ORDER_DEL_CHARGE ON J1_ORDER_COUPON.ORDER_ID=J1_ORDER_DEL_CHARGE.ORDER_ID";	
		String sql= "select * from " + tableName + " where ORDER_ID ="+oid;
			Connection con=db.getConnection();
		//PreparedStatement stmt=con.prepareStatement("select * from J1_ORDER");
		PreparedStatement stmt=con.prepareStatement(sql);
	    rs=stmt.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println("Error occur while fetching data.."+" "+e);
		}
		return rs;
	}
	
	

}
