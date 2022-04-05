/**
*Author: Ryan Jones
*Instructor: Hakam Alomari, Ph.D.
*CSE 201, Section D
* This class provides access to the different information needed for a User in the program.
*
* All implementation was completed by by Ryan Jones. 
*/

package DataObjects;

import java.sql.ResultSet;
import UtilityConnections.UserConnection;


public class UserInformation {
	
	private ResultSet result;
	private String userID;
	private String password;
	private String name;
	private String email;
	private String userStatus;
	private String date;
	
	/**
	* Constructor for the UserInformation Object which populates the user information 
	* as guest by default 
	*/
	public UserInformation() {
		userID = "guest";
		password = "guest";
		name = "guest";
		email = "guest";
		userStatus = "guest";	
		date = "guest";
	}
	
	/**
	* Constructor for the UserInformation Object which populates the user information 
	* with results from the database
	* 
	* @param userID
	* 			the identifier for the user
	*/
	public void getInformation(String userID) throws Exception {
		result = new UserConnection().getInformation(userID);
		result.next();
			
		setUserID(result.getString("userID"));
		setPassword(result.getString("password"));
		setName(result.getString("name"));
		setEmail(result.getString("email"));
		setUserStatus(new UserConnection().getUserStatus(userID));
		setDate(result.getString("date"));
	}
	
	
	/**
	* The following functions provide access to a field of information for a user
	* 
	* @return the information data
	*/
	public String getUserID() {return userID;}
	
	protected String getPassword() {return password;}
	
	public String getName() {return name;}
	
	public String getEmail() {return email;}
	
	protected String getUserStatus() {return userStatus;}
	
	public String getDate() {return date;}
	
	/**
	* The following functions set a field of information for a user
	* 
	* @param ?
	* 			the information data
	*/
	protected void setUserID(String userID) {this.userID = userID;}
	
	protected void setPassword(String password) {this.password = password;}
	
	protected void setName(String name) {this.name = name;}
	
	protected void setEmail(String email) {this.email = email;}
	
	protected void setUserStatus(String userStatus) {this.userStatus = userStatus;}
	
	protected void setDate(String date) {this.date = date;}
	
	
	//TO BE DELETED
	public String getUserString() {
		return "userID: " + getUserID() + "\npassword: " + getPassword() + "\nName: " + getName()
		 		+ "\nEmail: " + getEmail() + "\nDate: " + getDate()  + "\nUserStatus: " + getUserStatus();
				
	}
}
