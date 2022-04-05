/**
*Author: Ryan Jones
*Instructor: Hakam Alomari, Ph.D.
*CSE 201, Section D
* This class provides access to the different information needed for an App in the program.
*
* All implementation was completed by by Ryan Jones. 
*/

package DataObjects;

import java.sql.ResultSet;
import UtilityConnections.AppConnection;

public class AppInformation {
	
	private ResultSet result;
	private String appID;
	private String developer;
	private String imageID;
	private String bDesc;
	private String dDesc;
	private String uDesc;
	private String version;
	private String date; 
	private double price;
	private int downloads;
	private double ratingAvg;
	private int ratingCount;
 
	public AppInformation(String app, String company)  {
		setAppID(app);
		setDeveloper(company);
		setImageID("dummy");
		setBriefDesc("dummy");
		setDetailedDesc("dummy");
		setUpdateDesc("dummy");
		setDate("dummy");
		setPrice(0);
		setVersion("dummy");
		setRatingAvg(5);
		setRatingCount(1);
		setDownloads(0);
	}
	/**
	* Constructor for the AppInformation Object which populates the app information 
	* according to an identifier.  
	* 
	* @param appID
	* 			the identifier for the app  
	* 
	* @param tableID
	* 			the identifier for the table in the database (Apps or AppSubmission)
	*/
	public void getInformation(String appID) throws Exception {

		String query = "SELECT * FROM Apps WHERE appID = ?";
		
		result = new AppConnection().getInformation(appID, query);
		result.next();

		setAppID(result.getString("appID"));
		setDeveloper(result.getString("developer"));
		setImageID(result.getString("imageID"));
		setBriefDesc(result.getString("bDesc"));
		setDetailedDesc(result.getString("dDesc"));
		setUpdateDesc(result.getString("uDesc"));
		setDate(result.getString("date"));
		setPrice(result.getDouble("price"));
		setVersion(result.getString("version"));
		
		RatingFunctions funct = new RatingFunctions();
		funct.setRating(appID);
		setRatingAvg(funct.getAvg());
		setRatingCount(funct.getCount());
		setDownloads(new AppConnection().getDownloads(appID));
		
	}
	
	public void getSubmissionInformation(String appID) throws Exception {

		String query = "SELECT * FROM AppSubmission WHERE appID = ?";
		
		result = new AppConnection().getInformation(appID, query);
		result.next();

		setAppID(result.getString("appID"));
		setDeveloper(result.getString("developer"));
		setImageID(result.getString("imageID"));
		setBriefDesc(result.getString("bDesc"));
		setDetailedDesc(result.getString("dDesc"));
		setUpdateDesc(result.getString("uDesc"));
		setDate(result.getString("date"));
		setPrice(result.getDouble("price"));
		
	}
	
	/**
	* The following functions provide access to a field of information for an app
	* 
	* @return the information data
	*/
	public String getAppID() {return appID;}

	public String getDeveloper() {return developer;}
	
	public String getImageID() {return imageID;}
	
	public String getBriefDesc() {return bDesc;}
	
	public String getDetailedDesc() {return dDesc;}
	
	public String getUpdateDesc() {return uDesc;}
	
	public String getVersion() {return version;}
	
	public String getDate() {return date;}
	
	public double getPrice() {return price;}
	
	public int getDownloads() {return downloads;}
	
	public double getRatingAvg() {return ratingAvg;}
	
	public int getRatingCount() {return ratingCount;}
	
	
	
	/**
	* The following functions set a field of information for an app
	* 
	* @param ?
	* 			the information data
	*/
	protected void setAppID(String appID) {this.appID = appID;}
	
	protected void setDeveloper(String developer) {this.developer = developer;}
	
	protected void setImageID(String imageID) {this.imageID = imageID;}
	
	protected void setBriefDesc(String bDesc) {this.bDesc = bDesc;}
	
	protected void setDetailedDesc(String dDesc) {this.dDesc = dDesc;}
	
	protected void setUpdateDesc(String uDesc) {this.uDesc = uDesc;}
	
	protected void setVersion(String version) {this.version = version;}
	
	protected void setDate(String date) {this.date = date;}
	
	protected void setPrice(double price) {this.price = price;}
	
	protected void setDownloads(int downloads) {this.downloads = downloads;}
	
	protected void setRatingAvg(double ratingAvg) {this.ratingAvg = ratingAvg;}
	
	protected void setRatingCount(int ratingCount) {this.ratingCount = ratingCount;}
 
	
	//TO BE DELETED
	public String getAppString() {
		return "AppID: " + getAppID() + "\nCompanyID: " + getDeveloper() + "\nImageID: " + getImageID() + "\nBrief Description: " + getBriefDesc()
				+ "\nDetailed Desctiption: " + getDetailedDesc() + "\nUpdate Description: " + getUpdateDesc() + "\nVersion: " + getVersion() + "\nDate: " + getDate()
				+ "\nPrice: " + getPrice() + "\nNumber of Downloads: " + getDownloads() + "\nRatingAvg: " + getRatingAvg() + "\nRatingCount: " + getRatingCount();
	}

	
}
