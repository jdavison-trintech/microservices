package com.trintech.msw;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class LocalDatabaseConnectionTest {

	@Test
	public void test() {
		String url ="jdbc:sqlserver://10.30.21.230\\SQL14:1437;databaseName=QA82_Console_280;integratedSecurity=true";
		
		
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		conn = DriverManager.getConnection(url);
		
		queryCert(conn);
		queryClose(conn);
		
		} catch (ClassNotFoundException e) {
			fail(e.getMessage());
			
		} catch (SQLException e) {
			fail(e.getMessage());
		}finally{
			try {
				if(conn != null)conn.close();
			} catch (SQLException e) {
				//not much we can do here.
			}
		}
	}

	private void queryClose(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	private void queryCert(Connection conn) throws SQLException {
		PreparedStatement userPs = conn.prepareStatement("select * from users';");
		PreparedStatement userRolesPs = conn.prepareStatement("select * from usersroles';");
	
		ResultSet userRS = userPs.executeQuery();
		ResultSet userRoleRs= userRolesPs.executeQuery();
		
		while (userRS.next()) {

	        userRS.getObject(1);

	           }  
		
		
		
	}

	private void query(Connection conn) {
		
		try{           

		    PreparedStatement ps = conn.prepareStatement("select * from PROPS where NAME='adminName';");
		    /* if UDF, and need to pass params, can do something like:
		    ...prepareStatement("select * from UDF('" + UDFinputVal + "')" */

		    ResultSet rs = ps.executeQuery();
		    String keyType =null;
		    String key1 = null;
		    String key2 = null;
//		    List<Props> resultList = new ArrayList<Props>();
		    while (rs.next()) {

		        keyType = rs.getString(1);
		         key1 = rs.getString(2);
		         key2 = rs.getString(3);

		           }  
		    assertTrue(keyType != null);
		    assertTrue(key1 != null);
		    assertTrue(key2 != null);
		    
		    rs.close();
		    ps.close();            
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
		
	}

}
