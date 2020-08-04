package com.vtiger.genericutils;

import java.sql.*;

/**
 * 
 * @author Karthik M
 * This class contains generic methods for executing queries and verifying the data
 * from the database 
 */

public class DataBaseLib {
	
	private Connection con; // Object of Connection from the Database
	private Statement stmt; // Object of Statement. It is used to create a Statement to execute the query
	private ResultSet rs; //Object of ResultSet => 'It maintains a cursor that points to the current row in the result set'
	private boolean flag;
	
	/**
	 * Method to connect to the Database using JDBC
	 * @param jdbcDriver
	 * @param dbUrl
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	public void connectDB(String jdbcDriver, String dbUrl, String username, String password) throws Exception
	{
		//Step 1: Load the driver class 
		Class.forName(jdbcDriver);
		//DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
				
		//Step 2: Create the Connection Object
		con=DriverManager.getConnection(dbUrl,username,password);
		
		//Step 3: Create the Statement Object
		if(con!=null)
		{
			stmt=con.createStatement();
		}
	}
	
	/**
	 * This method executes only DQL queries and returns the ResultSet
	 * @param sql
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet executeSelectQuery(String sql) throws Exception
	{
		
		//Step 1: Execute query
		if(stmt!=null) 
		{
			rs = stmt.executeQuery(sql);
		}
		return rs;
	}
	/**
	 * This method executes only DML queries and returns the status as integer
	 * @param sql
	 * @return Integer
	 * @throws Exception
	 */

	public int executeModifyQuery(String sql) throws Exception
	{
		int status=0;
		//Step 1: Execute query
		if(stmt!=null)
		{
			status = stmt.executeUpdate(sql);
		}
		return status;
	}
	
	/**
	 * This method executes DQL queries and verifies whether the given data is present
	 * in the particular column and returns the status as boolean
	 * @param sql
	 * @param data
	 * @param column_name
	 * @return boolean
	 * @throws Exception
	 */
	
	public boolean executeQueryVerifyData(String sql, Object data, String column_name) throws Exception
	{
		/*This method executes only DML queries and returns the status as integer*/
		
		//Step 1: Execute query
		flag=false;
		if(stmt!=null) 
		{
			rs = stmt.executeQuery(sql);
		}
		//Step 2: Verify whether the data is present in the given column
		while(rs.next())
		{
			if(rs.getString(column_name).equals(data))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public void closeDB() throws Exception 
	{
		//Step 1: Close the connection
		if(con!=null)
			con.close();
	}

}
