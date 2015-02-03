package cs320.wiki;

public class Revision extends PageListEntry{
	String path;
	String name;
	String date;
	String content;
	Revision(String path, String name, String date, String content)
	{
		this.name = name;
		this.date = date;
		this.path = path;
		this.content = content;
	}
	public String getPath()
	{
		return this.path;
	}
	public String getName()
	{
		return this.name;
	}
	public String getDate()
	{
		return this.date;
	}
	public String getContent()
	{
		return this.content;
	}
}
