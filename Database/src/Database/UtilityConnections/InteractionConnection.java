package UtilityConnections;

import java.sql.*;

public class InteractionConnection {

	private static Connection con;
	private PreparedStatement statement;
	
	public InteractionConnection() throws Exception {
		try {
			con = ConnectionDriver.getConnection();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	protected void createComment() throws Exception {
		try {
			statement = con.prepareStatement
					(
					""
					);
			
			statement.executeUpdate();
		} catch (Exception e){
			System.out.println(e);
		}	
	}
	
	protected void deleteComment(String commentID) throws Exception {
		try {
			statement = con.prepareStatement
					(
					""
					);
			
			statement.executeUpdate();
		} catch (Exception e){
			System.out.println(e);
		}	
	}
	
	protected void Download(String appID, int download) {
		try {
			statement = con.prepareStatement
					(
					"UPDATE Apps"
					+ "SET download = '"  + download+1 + "'"
					+ "WHERE appID = '" + appID + "';"
					);
			
			statement.executeUpdate();
		} catch (Exception e){
			System.out.println(e);
		}
	}

	protected void updateAccount(String appID, String userID, double price) {
		try {
			statement = con.prepareStatement
					(
					"UPDATE Users"
					+ "SET ;"
					);
			
			statement.executeUpdate();
		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	protected void createPurchase(String userID, String appID, String date, int download, double price) {
		try {
			statement = con.prepareStatement
					(
					"INSERT INTO PurchaseLibrary (appID, userID, date)"
					+ "VALUE ('" + appID + "','" + userID + "','" + date + "');"
					);
			
			statement.executeUpdate();
		} catch (Exception e){
			System.out.println(e);
		}
		
		if(price > 0) {updateAccount(appID, userID, price);}
		Download(appID, download);
	}
	
	protected void refundPurchase(String userID) {
		
	}
}
