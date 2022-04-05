/**
*Author: Ryan Jones
*Instructor: Hakam Alomari, Ph.D.
*CSE 201, Section D
* Connects to the database to execute queries or updates on the Users table
*
* All implementation was completed by by Ryan Jones. 
*/

package UtilityConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class UserConnection {
	
	private ResultSet result;
	private Connection con;
	private PreparedStatement statement;
	
	/**
	* Constructor for the UserConnection which sets the connection to the database.  
	*/
	public UserConnection() {
		try {
			con = new ConnectionDriver().getConnection();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	* Verifies a user exists with a specified userID and password in the database
	* 
	* @param userID
	* 			the specified identifier for the user. 
	* 
	* @param password
	* 			the specified password for the user
	* 
	* @return a boolean value indicating if the userID and password are correct.  		
	*/
	public boolean confirmUser(String userID, String password) {
		try {
			statement = con.prepareStatement
					(
					"SELECT COUNT(userID)"
					+ " FROM Users"
					+ " WHERE userID = ? AND password = ?"							
					);
			statement.setString(1, userID);
			statement.setString(2, password);
			
			result = statement.executeQuery();
			
			result.next();
			if (result.getInt("COUNT(userID)") != 0) {return true;}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	/**
	* Determines if an entered string is already present in the Users table. 
	* 
	* @param userID
	* 			the identifier for the user. 
	* 
	* @return a boolean value indicating if the string was present. 		
	*/
	public boolean unique(String userID) {
		try {
			statement = con.prepareStatement
					(
					"SELECT COUNT(userID)"
					+ " FROM Users"
					+ " WHERE userID = ?"
					);
					
			statement.setString(1, userID);
			result = statement.executeQuery();
					
			if (result.getInt("COUNT(userID)") != 0) {
				return false;
			}
		} catch (Exception e){
			System.out.println(e);
		}
		return true;
	}
	
	/**
	* Inserts entered information into the Users table, allows for updates to information
	* 
	* @param map
	* 			information necessary for attributes in the Users table		
	*/
	public void insert(HashMap<String, String> map) {
		try {
			statement = con.prepareStatement
					(
					"INSERT INTO Users (userID, password, name, email, date)"
					+ " VALUE(?, ?, ?, ?, CURDATE())"
					+ " ON DUPLICATE KEY UPDATE password = ?, email = ?;"
					);
			
			statement.setString(1, map.get("userID"));
			statement.setString(2, map.get("password"));
			statement.setString(3, map.get("name"));
			statement.setString(4, map.get("email"));
			
			statement.setString(5, map.get("password"));
			statement.setString(6, map.get("email"));
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	* Deletes a tuple from the Users table
	* 
	* @param userID
	* 			the identifier for the user.	
	*/
	public void delete(String userID) {
		try {
			statement = con.prepareStatement
					(
					"DELETE"
					+ " FROM Users"
					+ " WHERE userID = ?"
					);
			statement.setString(1, userID);
			statement.executeUpdate();
		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	/**
	* Retrieves information stored in the Users table for a specified tuple.
	* 
	* @param userID
	* 			the identifier for the user.	
	* 
	* @return the information stored in the Users table.
	*/
	public ResultSet getInformation(String userID) {
		try {
			statement = con.prepareStatement
					(
					"SELECT *"
					+ " FROM Users"
					+ " WHERE userID = ?"
					);
			
			statement.setString(1, userID);
			result = statement.executeQuery();			
			return result;
		} catch (Exception e){
			System.out.println(e);
		}			
		return null;
	}
	
	public String getUserStatus(String userID) {
		try {
			statement = con.prepareStatement
					(
					"SELECT status"
					+ " FROM UserStatus"
					+ " WHERE userID = ?"
					);
			statement.setString(1, userID);
			result = statement.executeQuery();
			result.next();
			return result.getString(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
