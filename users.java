import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class users {
	private String username;
	private String password;
	private String name;
	private String status;
	private String email;
	
	public users(String username, String password, String name, String status, String email) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.status = status;
		this.email = email;
	}

	public users(JSONObject user) {
		this.username = (String) user.get("username");
		this.password = (String) user.get("password");
		this.name = (String) user.get("name");
		this.status = (String) user.get("status");
		this.email = (String) user.get("email");
	}
	
	public String toString() {
		return "username: " + this.username 
				+ "\npassword: " + this.password
				+ "\nname: " + this.name
				+ "\nstatus: " + this.status
				+ "\nemail: " + this.email;
	}
	
	public static void writeToFile(JSONObject obj) throws IOException {
		FileWriter writer = new FileWriter("users.json");
		writer.write(obj.toJSONString());
		writer.flush();
		writer.close();
	}
	
	@SuppressWarnings("unchecked")
	public static boolean addUser(users user) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		//Parsing the contents of the JSON file
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("users.json"));
        JSONArray jsonArray = (JSONArray) jsonObject.get("Data");
        HashMap<String, users> map = getUsers();
        if (!map.containsKey(user.username)) {
        	jsonArray.add(user.toJSONString());
            JSONObject holder = new JSONObject();
    		holder.put("Data", jsonArray);
    		writeToFile(holder);
    		return true;
        }
        return false;
	}
	
	public String toJSONString() {
		String ret = "username: " + this.username
				+ ", password: " + this.password
				+ ", name: " + this.name
				+ ", status: " + this.status
				+ ", email: " + this.email;
		return ret;
	}
	
	public static HashMap<String, users> getUsers() throws FileNotFoundException, IOException, ParseException{
		HashMap<String, users> map = new HashMap<String, users>();
		
		JSONParser jsonParser = new JSONParser();
		//Parsing the contents of the JSON file
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("users.json"));
        JSONArray jsonArray = (JSONArray) jsonObject.get("Data");
        for(Object o : jsonArray) {
        	String obS = o.toString();
        	String[] wds  = obS.split(",");
        	String uname = wds[0].split(": ")[1];
        	String pass = wds[1].split(": ")[1];
        	String name = wds[2].split(": ")[1];
        	String status = wds[3].split(": ")[1];
        	String email = wds[4].split(": ")[1];
        	map.put(uname, new users(uname, pass, name, status, email));
        }
		
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
		// Showing how to get users map, check password, and adding a user
		HashMap<String, users> map = getUsers();
        
        System.out.println(map.get("cmccarthy"));
        System.out.println((map.get("cmccarthy").password).equals("CarS0nRoX!"));
        // adding a user
        users dummy = new users("professorDoll", "tennisTime123", "John Doll", "User", "dolljm@miamioh.edu");
        // adds to the JSON file each time so please delete it after running this if you do
        addUser(dummy);

        HashMap<String, users> map2 = getUsers();
        
        System.out.println(map2.get("professorDoll"));
	}
}
