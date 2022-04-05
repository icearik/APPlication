package UtilityConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RatingConnection extends AppConnection{

	private PreparedStatement statement;
	
	
	public double[] getInformation(String appID) { //maybe finished. 
		double[] resultArray = new double[2];
		
		try {
			statement = con.prepareStatement
					(
					"SELECT AVG(rating), COUNT(rating)"
					+ " FROM Rating"
					+ " WHERE appID = ?"
					);
			
			statement.setString(1, appID);
			
			ResultSet result = statement.executeQuery();
			result.next();
			resultArray[0] = result.getDouble(1);
			resultArray[1] = result.getInt(2);
			
			return resultArray;
		} catch (Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	public void insert(String userID, String appID, int rating) { //maybe finished
		try {
			statement = con.prepareStatement
					(
					"INSERT INTO Rating (appID, userID, rating)"
					+ " VALUE(?, ?, ?)"
					+ " ON DUPLICATE KEY UPDATE rating = ?;"
					);
			
			statement.setString(1, appID);
			statement.setString(2, userID);
			statement.setInt(3, rating);
			statement.setInt(4, rating);
			
			statement.executeUpdate();
		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	public void delete(String userID, String appID) { //maybe finished
		try {
			statement = con.prepareStatement
					(
					"DELETE"
					+ " FROM Rating"
					+ " WHERE appID = ? and userID = ?;"
					);
			
			statement.setString(1, appID);
			statement.setString(2, userID);
			
			statement.executeUpdate();
		} catch (Exception e){
			System.out.println(e);
		}
	}
}
