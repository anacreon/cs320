package cs320.wiki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

public class WikiDB {
	public static List<PageListEntry> updateEntries() throws ServletException
	{
		List<PageListEntry> entries = getEntriesFromDB();
		
		for(int i = 0; i < entries.size(); i++)
		{
			entries.get(i).setRevisions(getRevisionsFromDB(i + 1));
		}
		return  entries;
	}
	public static String userToName(String user) throws ServletException {
        String name = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu13";
            String username = "cs320stu13";
            String password = "P8*InnQ1";

            Connection c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select name from users where username = '"+ user +"'" );

            while( rs.next() )
            {
            	name = rs.getString( "name" );
            }

            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        return name;
	}
	public static List<PageListEntry> getEntriesFromDB() throws ServletException {
		// TODO Auto-generated method stub
	     List<PageListEntry> entries = new ArrayList<PageListEntry>();
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu13";
	            String username = "cs320stu13";
	            String password = "P8*InnQ1";

	            Connection c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from pages" );

	            while( rs.next() )
	            {
	            	int id = rs.getInt("id");
	                String name = rs.getString( "name" );
	                String content = rs.getString( "content" );
	                String date = rs.getString("date");
	                String path = rs.getString("path");

	                entries.add(new PageListEntry(id, path, name, date, content));
	            }

	            c.close();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }

	        return entries;
		
	}
	public static List<Revision> getRevisionsFromDB(int index) throws ServletException
	{
		List<Revision> changes = new ArrayList<Revision>();
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu13";
            String username = "cs320stu13";
            String password = "P8*InnQ1";

            Connection c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select p.path, r.content, r.author, r.date from revisions r" +
            		" left join pages p on (p.id = r.page) where(p.id ="+index+")" );

            while( rs.next() )
            {
                String author = rs.getString( "author" );
                String content = rs.getString( "content" );
                String date = rs.getString("date");
                String path = rs.getString("path");
                String name = userToName(author);
                changes.add(new Revision(path, name, date, content));
            }

            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }

        return changes;

		
	}
}
