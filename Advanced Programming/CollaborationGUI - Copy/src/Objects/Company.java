package Objects;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Company extends ParentObject {

	private static final long serialVersionUID = 7519158114731873077L;
	private String name;
	private String abn;
	private String url;
	private String address;
	
	public Company(String id, String name, String ABN, String URL, String address) {
		super(id);		
		this.setName(name);
		this.setABN(ABN);
		this.setURL(URL);
		this.setAddress(address);
	}



	public Company(ResultSet rs) throws SQLException {
		super("C" + rs.getInt("ID"));
		this.setName(rs.getString("Name"));
		this.setABN(rs.getString("ABN"));
		this.setURL(rs.getString("URL"));
		this.setAddress(rs.getString("Address"));
	}





	public String getABN() {
		return abn;
	}

	public void setABN(String abn) {
		this.abn = abn;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	//use hashmap, will be quicker to lookup and edit
	
	
	
	public String toString() {
		return getID() + ";" + getName() + ";" + getABN() + ";" + getURL() + ";" + getAddress();
	}
	public String getSQL() {
		return "('" + getID().replace("C", "") + "','" + getName() + "','" + getABN() + "','" + getURL() + "','" + getAddress() + "')";
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
}
