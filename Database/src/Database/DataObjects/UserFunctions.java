/**
*Author: Ryan Jones
*Instructor: Hakam Alomari, Ph.D.
*CSE 201, Section D
* This class provides access to functions which will be applied to the Users table
*
* All implementation was completed by by Ryan Jones. 
*/

package DataObjects;

import java.util.HashMap;

import UtilityConnections.UserConnection;

public class UserFunctions {

	/**
	* Calls a method to delete a specific tuple from the Users table in the database.  
	* 
	* @param userID
	* 			the identifier for the tuple to be deleted.  
	*/
	public void delete(String userID) throws Exception {new UserConnection().delete(userID);}
	
	/**
	* Calls a method to insert information into the Users table in the database.  
	* 
	* @param map
	* 			map containing user information  
	*/
	public void insert(HashMap<String, String> map) throws Exception {new UserConnection().insert(map);}
	
	/**
	* Calls a method to confirm an entered userID and password exists in the database.   
	* 
	* @param userID
	* 			the identifier for user
	* 
	* @param password
	* 			user specified password
	* 
	* @return boolean value if user specified information is correct. 
	*/
	public boolean confirmUser(String userID, String password) throws Exception {return new UserConnection().confirmUser(userID, password);}
	
	/**
	* Calls a method to determine if a userID is in use
	* 
	* @param userID
	* 			the identifier for user
	* 
	* @return boolean value if userID is in use
	*/
	public boolean unique(String userID) throws Exception {return new UserConnection().unique(userID);}
	public String getStatus(String userID) {return new UserConnection().getUserStatus(userID);}
}
