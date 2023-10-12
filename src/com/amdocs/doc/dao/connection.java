package com.amdocs.doc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class connection {
	public Connection connect() {
		Connection con=null;
		try {
			//for load driver 
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			// for create a connection registration 
			con=DriverManager.getConnection("Jdbc:Oracle:thin:@172.25.54.249:1521:XE","system","Yadav123#"); //connection
			
		}
		catch(Exception e){e.printStackTrace();}
		return con;
		
	}
}

