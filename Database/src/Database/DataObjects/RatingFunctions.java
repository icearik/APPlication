package DataObjects;

import UtilityConnections.RatingConnection;

public class RatingFunctions {

	private int ratingCount;
	private double ratingAvg;
	
	public void setRating(String appID) throws Exception {
		double[] rating = new RatingConnection().getInformation(appID);
		ratingCount = (int)rating[1];
		ratingAvg = rating[0];
	}
	
	public int getCount() {return ratingCount;}
	
	public double getAvg() {return ratingAvg;}
	
	public void insert(String appID, String userID, int rating) throws Exception {
		new RatingConnection().insert(userID, appID, rating);
	}
	
	public void delete(String appID, String userID) throws Exception {
		new RatingConnection().delete(userID, appID);
	}
}
