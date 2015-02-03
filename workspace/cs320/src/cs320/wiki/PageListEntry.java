package cs320.wiki;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

public class PageListEntry {
	
	final String PATH = "/cs320stu13/wiki/";
	int id;
	String name;
	String date;
	String path;
	String content;
	List<Revision> changes = new ArrayList<Revision>();
	PageListEntry()
	{
		
	}
	public PageListEntry(String name, String date,String content)
	{
		this.name = name;
		this.date = date;
		this.content = content;
	}
	public PageListEntry(int id, String path, String name, String date, String content) throws ServletException
	{
		this.id = id;
		this.name = name;
		this.date = date;
		this.path = path;
		this.content = content;
		changes = WikiDB.getRevisionsFromDB(id);
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
	public String getUripath()
	{
		return PATH + this.path;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	public void edit(String name, String content, String date)
	{
		changes.add(new Revision(this.path, name, date, content));
		setName(name);
		setContent(content);
		setDate(date);
		
	}
	public void setRevisions(List<Revision> changes)
	{
		this.changes = changes;

	}
	public List<Revision> getRevisions()
	{
		return this.changes;
	}
}
