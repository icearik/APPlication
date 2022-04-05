package UtilityConnections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilterConnection extends AppConnection{

	private PreparedStatement statement;
	private ResultSet result;
	
	public ArrayList<String> sortByTop() {
		try {
			statement = con.prepareStatement
					(
					"SELECT appID"
					+ " FROM Downloads"
					+ " ORDER BY downloads DESC"
					);
			
			result = statement.executeQuery();
			result.next();
			return convertToArrayList();
		} catch (Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	public ArrayList<String> sortByRating() { //check this query. 
		try {
			statement = con.prepareStatement
					(
					"SELECT appID"
					+ " FROM Rating"
					+ " GROUP BY appID"
					+ " ORDER BY AVG(rating) DESC;" 
					);
			
			result = statement.executeQuery();	
			result.next();
			return convertToArrayList();
		} catch (Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	public ArrayList<String> search(String search) { //this is sort of broken, come back. 
		try {
			statement = con.prepareStatement
					(
					"SELECT appID, MATCH(appID, bDesc, dDesc) AGAINST(?) AS Relevance"
					+ " FROM Apps"
					+ " WHERE MATCH(appID, bDesc, dDesc) AGAINST(? WITH QUERY EXPANSION)"
					+ " ORDER BY Relevance DESC"
					);
			
			statement.setString(1, search);
			statement.setString(2, search);
			result = statement.executeQuery(); 
			result.next();
			return convertToArrayList();
		} catch (Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	public ArrayList<String> sortByDate() {
		try {
			statement = con.prepareStatement
					(
					"SELECT Apps.appID"
					+ " FROM Apps"
					+ " ORDER BY date DESC;" 
					);
			
			result = statement.executeQuery();
			result.next();
			return convertToArrayList();
		} catch (Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	public ArrayList<String> sortSubmissions() throws SQLException {
		try {
			statement = con.prepareStatement
					(
					"SELECT appID"
					+ " FROM AppSubmission"
					);
			
			result = statement.executeQuery();	
			result.next();
			return convertToArrayList();
		} catch (Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	private ArrayList<String> convertToArrayList() throws SQLException {
		ArrayList<String> resultArray = new ArrayList<String>();
		do {
			resultArray.add(result.getString(1));
		} while (result.next());
		return resultArray;
	}
}
