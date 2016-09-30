package com.trantor.finddata;

import java.sql.ResultSet;

public class Searcher {
	
	
	public static ResultSet searchData(String oid,String listOfTables)
	{
		ResultSet rs;
		DatabaseMethods dbm=new DatabaseMethods();
		rs=dbm.getData(oid, listOfTables);
		
		return rs;
	}

}
