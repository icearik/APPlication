/**
*Author: Ryan Jones
*Instructor: Hakam Alomari, Ph.D.
*CSE 201, Section D
* This class connects the database to execute queries and updates on the Apps and AppSubmission tables
*
* All implementation was completed by by Ryan Jones. 
*/

package UtilityConnections;

import java.sql.*;
import java.util.HashMap;

public class AppConnection {

	protected Connection con;
	private PreparedStatement statement;
	
	/**
	* Constructor for the AppConnection which sets the connection to the database.  
	*/
	public AppConnection() {
		try {
			con = new ConnectionDriver().getConnection();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	* Determines if an entered string is already present in the Apps table. 
	* 
	* @param appID
	* 			the identifier for the app. 
	* 
	* @return a boolean value indicating if the string was present. 		
	*/
	public boolean unique(String appID) {
		try {
			statement = con.prepareStatement
					(
					"SELECT COUNT(appID)"
					+ " FROM Apps"
					+ " WHERE appID = ?"
					);
			
			statement.setString(1, appID);
			ResultSet result = statement.executeQuery();
			result.next();
			if (result.getInt("COUNT(appID)") != 0) {
				return false;
			}
		} catch (Exception e){
			System.out.println(e);
		}
		return true;
	}
	
	/**
	* Inserts entered information into the AppSubmission table, allows for updates to information
	* 
	* @param map
	* 			information necessary for attributes in the AppSubmission table		
	*/
	public void insertSubmission(HashMap<String, String> map) { 
		try {
			
			statement = con.prepareStatement
					(
					"INSERT INTO AppSubmission (appID, developer, imageID, bDesc, dDesc, uDesc, version, price, date)"
					+ " VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?)"
					+ " ON DUPLICATE KEY UPDATE imageID = ?, bDesc = ?, dDesc = ?, price = ?;"
					);
			
			statement.setString(1, map.get("appID"));
			statement.setString(2, map.get("developer"));
			statement.setString(3, map.get("imageID"));
			statement.setString(4, map.get("bDesc"));
			statement.setString(7, map.get("dDesc"));
			statement.setString(8, map.get("uDesc"));
			statement.setString(6, map.get("version"));
			statement.setDouble(5, Double.parseDouble(map.get("price")));
			statement.setString(9, "CURDATE()");
			
			statement.setString(10, map.get("imageID"));
			statement.setString(11, map.get("bDesc"));
			statement.setString(12, map.get("dDesc"));
			statement.setDouble(13, Double.parseDouble(map.get("price")));
			
			statement.executeUpdate();
			
		} catch (Exception e){
			System.out.println(e);
		}
	}

	/**
	* Deletes a tuple from the AppSubmission table
	* 
	* @param appID
	* 			the identifier for the app.	
	*/
	public void deleteSubmission(String appID) {
		try {
			
			statement = con.prepareStatement
					(
					"DELETE FROM AppSubmission"
					+ " WHERE appID = ?"
					);
			statement.setString(1, appID);
			statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	* Inserts entered information into the Apps table, allows for updates to information
	* 
	* @param map
	* 			information necessary for attributes in the Apps table		
	*/
	public void insert(HashMap<String, String> map) { 
		
		try {
			
			if(unique(map.get("appID"))) {
				statement = con.prepareStatement
						(
						"INSERT INTO Downloads (appID, downloads)"
						+ "VALUE(?, 0)"
						);
				
				statement.setString(1, map.get("appID"));
				statement.executeUpdate();
			}
			
			statement = con.prepareStatement
					(
					"INSERT INTO Apps (appID, developer, imageID, bDesc, dDesc, uDesc, version, price, date)"
					+ " VALUE(?, ?, ?, ?, ?, ?, ?, ?, CURDATE())"
					+ " ON DUPLICATE KEY UPDATE imageID = ?, developer = ?, bDesc = ?, dDesc = ?, uDesc = ?, version = ?, price = ?;"
					);
			
			statement.setString(1, map.get("appID"));
			statement.setString(2, map.get("developer"));
			statement.setString(3, map.get("imageID"));
			statement.setString(4, map.get("bDesc"));
			statement.setString(5, map.get("dDesc"));
			statement.setString(6, map.get("uDesc"));
			statement.setString(7, map.get("version"));
			statement.setDouble(8, Double.parseDouble(map.get("price")));
			
			statement.setString(9, map.get("imageID"));
			statement.setString(10, map.get("developer"));
			statement.setString(11, map.get("bDesc"));
			statement.setString(12, map.get("dDesc"));
			statement.setString(13, map.get("uDesc"));
			statement.setString(14, map.get("version"));
			statement.setDouble(15, Double.parseDouble(map.get("price")));
			
			statement.executeUpdate();
			
		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	/**
	* Inserts an app from the AppSubmission table into the Apps table when approved. 
	* 
	* @param appID
	* 			the identifier for the app.
	*/
	public void insert(String appID) {
		try {
			
			statement = con.prepareStatement
					(
					"INSERT INTO Apps"
					+ " SELECT * FROM AppSubmission"
					+ " WHERE appID = ?;"	
					);
			
			statement.setString(1, appID);
			statement.executeUpdate();
			deleteSubmission(appID);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	* Deletes a tuple from the Apps table
	* 
	* @param appID
	* 			the identifier for the app.	
	*/
	public void delete(String appID) { 
		try {
			statement = con.prepareStatement
					(
					"DELETE FROM Apps"
					+ " WHERE appID = ?"
					);
			
			statement.setString(1, appID);
			statement.executeUpdate();
			
		} catch (Exception e){
			System.out.println(e);
		}	
	}
	
	/**
	* Retrieves information stored in the Apps table for a specified tuple.
	* 
	* @param appID
	* 			the identifier for the app.	
	* @param tableID
	* 			the identifier for the table in the database (Apps or AppSubmission)
	* 
	* @return the information stored in the Apps table.
	*/
	public ResultSet getInformation(String appID, String query) { 
		try {
			statement = con.prepareStatement
					(query);
			statement.setString(1, appID);
			
			ResultSet result = statement.executeQuery();			
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public int getDownloads(String appID) {
		try {
			statement = con.prepareStatement
					(
					"SELECT downloads"
					+ " FROM Downloads"
					+ " WHERE appID = ?"	
					);
			
			statement.setString(1, appID);
			ResultSet result = statement.executeQuery();
			result.next();
			return result.getInt(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return 0;
		
	}

}
