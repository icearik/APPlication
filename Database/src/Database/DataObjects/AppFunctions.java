/**
*Author: Ryan Jones
*Instructor: Hakam Alomari, Ph.D.
*CSE 201, Section D
* This class provides access to functions which will be applied to the Apps and 
* AppSubmission tables in the database. 
*
* All implementation was completed by by Ryan Jones. 
*/

package DataObjects;

import java.util.HashMap;
import UtilityConnections.AppConnection;

public class AppFunctions {
	
	/**
	* Calls a method to delete a specific tuple from the Apps table in the database.  
	* 
	* @param appID
	* 			the identifier for the tuple to be deleted.  
	*/
	public void delete(String appID) throws Exception {new AppConnection().delete(appID);}
	
	/**
	* Calls a method to insert a specific tuple in the Apps table from the AppSubmission table.  
	* 
	* @param appID
	* 			the identifier for the tuple to be inserted 
	*/
	public void insert(String appID) throws Exception {new AppConnection().insert(appID);}
	
	/**
	* Calls a method to insert or update a specific tuple in the Apps table  
	* 
	* @param appID
	* 			the identifier for the tuple to be inserted 
	*/
	public void insert(HashMap<String, String> map) throws Exception {new AppConnection().insert(map);}
	
	/**
	* Calls a method to insert or update a specific tuple in the AppSubmissions table in the database.  
	* 
	* @param map
	* 			contains information necessary for the attributes in the AppSubmissions table.
	*/
	public void insertSubmission(HashMap<String, String> map) throws Exception {new AppConnection().insertSubmission(map);}
	
	/**
	* Calls a method to delete a specific tuple from the AppSubmission table in the database.  
	* 
	* @param appID
	* 			the identifier for the tuple to be deleted.  
	*/
	public void deleteSubmission(String appID) throws Exception {new AppConnection().deleteSubmission(appID);}
}
