package cs320.wiki;

public class Userinfo {
	private String username;
	private String password;
	private String name;
	
	Userinfo(String username, String password, String name)
	{
		this.username = username;
		this.password = password;
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public String getUserName()
	{
		return this.username;
	}
	public String getPassword()
	{
		return this.password;
	}
	
}
